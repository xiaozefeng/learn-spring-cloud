package com.immoc.order.enums;

import lombok.Getter;

/**
 * @author xiaozefeng
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数错误"),

    CART_EMPTY(2, "购物车为空"),

    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态错误"),
    ORDER_DETAIL_NOT_EXISTS(5, "订单详情不存在"),
    ;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
