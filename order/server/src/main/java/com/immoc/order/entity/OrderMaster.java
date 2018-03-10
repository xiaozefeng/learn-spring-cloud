package com.immoc.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaozefeng
 */
@Entity
@Data
public class OrderMaster {
    /**
     * 订单id
     */
    @Id
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


    private Date createTime;

    private Date updateTime;


}
