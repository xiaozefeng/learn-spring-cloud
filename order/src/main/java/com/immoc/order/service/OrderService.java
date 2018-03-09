package com.immoc.order.service;

import com.immoc.order.DTO.OrderDTO;
import com.immoc.order.entity.OrderDetail;
import com.immoc.order.entity.OrderMaster;

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
