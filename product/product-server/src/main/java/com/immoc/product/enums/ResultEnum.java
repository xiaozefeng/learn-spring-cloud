package com.immoc.product.enums;

import lombok.Getter;

/**
 * @author xiaozefeng
 */
@Getter
public enum ResultEnum {
    PRODUCT_NO_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存不足")
    ;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;
}
