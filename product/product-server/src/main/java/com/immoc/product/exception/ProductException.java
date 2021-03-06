package com.immoc.product.exception;

import com.immoc.product.enums.ResultEnum;

/**
 * @author xiaozefeng
 */
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
