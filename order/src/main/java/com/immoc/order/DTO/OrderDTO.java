package com.immoc.order.dto;

import com.immoc.order.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaozefeng
 */
@Data
public class OrderDTO {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;


    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    List<OrderDetail> orderDetailList;

}
