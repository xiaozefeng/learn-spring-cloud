package com.immoc.order.enums;

import lombok.Getter;

/**
 * @author xiaozefeng
 *
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINISHED(1, "已经完成"),
    CANCEL(1, "已经完成");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
