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
}
