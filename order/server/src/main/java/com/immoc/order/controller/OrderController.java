package com.immoc.order.controller;

import com.immoc.order.convert.OrderForm2OrderDTOConverter;
import com.immoc.order.dto.OrderDTO;
import com.immoc.order.enums.ResultEnum;
import com.immoc.order.exception.OrderException;
import com.immoc.order.form.OrderForm;
import com.immoc.order.service.OrderService;
import com.immoc.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozefeng
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数校验
     * 2. 查询商品信息 (调用商品服务)
     * 3. 计算总价 (可用库存计算)
     * 4. 扣取库存 (调用服务)
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult) {
        // 么有经过验证
        if (bindingResult.hasErrors()) {
            log.error("创建订单参数不正确 : orderForm ={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // orderForm -> OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVO.ok(map);
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVO finishOrder(@RequestParam String orderId) {
        OrderDTO orderDTO = orderService.finish(orderId);
        return ResultVO.ok(orderDTO);
    }
}

