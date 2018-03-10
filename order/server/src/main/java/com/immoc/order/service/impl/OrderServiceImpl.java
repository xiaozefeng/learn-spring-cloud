package com.immoc.order.service.impl;

import com.immoc.order.dto.CartDTO;
import com.immoc.order.dto.OrderDTO;
import com.immoc.order.entity.OrderDetail;
import com.immoc.order.entity.OrderMaster;
import com.immoc.order.enums.OrderStatusEnum;
import com.immoc.order.enums.PayStatusEnum;
import com.immoc.order.repository.OrderDetailRepository;
import com.immoc.order.repository.OrderMasterRepository;
import com.immoc.order.service.OrderService;
import com.immoc.order.utils.KeyUtil;
import com.immoc.product.client.ProductClient;
import com.immoc.product.common.DecreaseStockInput;
import com.immoc.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author xiaozefeng
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 查询商品信息 (调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 3. 计算总价 (可用库存计算)
        AtomicReference<BigDecimal> orderAmount = new AtomicReference<>(BigDecimal.ZERO);
        orderDTO.getOrderDetailList().forEach(orderDetail -> {
            productInfoList.stream()
                    .filter(productInfo -> productInfo.getProductId().equals(orderDetail.getProductId()))
                    .forEach(productInfo -> {
                        orderAmount.set(productInfo.getProductPrice()
                                .multiply(BigDecimal.valueOf(orderDetail.getProductQuantity()))
                                .add(orderAmount.get()));
                        BeanUtils.copyProperties(productInfo, orderDetail);
                        orderDetail.setOrderId(orderId);
                        orderDetail.setDetailId(KeyUtil.genUniqueKey());

                        // 订单详情入库
                        orderDetailRepository.save(orderDetail);
                    });
        });

        // 4. 扣取库存 (调用服务)
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        // 5. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount.get());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }
}
