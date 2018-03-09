package com.immoc.order.service.impl;

import com.immoc.order.DTO.OrderDTO;
import com.immoc.order.entity.OrderMaster;
import com.immoc.order.enums.OrderStatusEnum;
import com.immoc.order.enums.PayStatusEnum;
import com.immoc.order.repository.OrderDetailRepository;
import com.immoc.order.repository.OrderMasterRepository;
import com.immoc.order.service.OrderService;
import com.immoc.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xiaozefeng
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //  TODO 查询商品信息 (调用商品服务)
        // 3. 计算总价 (可用库存计算)
        // 4. 扣取库存 (调用服务)
        // 5. 订单入库

        OrderMaster orderMaster = new OrderMaster();
        String orderId = KeyUtil.genUniqueKey();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }
}
