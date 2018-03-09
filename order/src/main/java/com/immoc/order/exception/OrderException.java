package com.immoc.order.exception;

import com.immoc.order.enums.ResultEnum; /**
 * @author xiaozefeng
 */
public class OrderException extends RuntimeException{
    public OrderException() {
    }

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum paramError) {
        super(paramError.getMsg());
        this.code = paramError.getCode();
    }
}
