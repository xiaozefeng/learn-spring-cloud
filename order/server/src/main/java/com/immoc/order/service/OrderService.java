package com.immoc.order.service;

import com.immoc.order.dto.OrderDTO;

/**
 * @author xiaozefeng
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完成订单 (只能卖家操作)
     *
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
