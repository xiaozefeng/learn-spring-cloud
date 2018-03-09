package com.immoc.product.VO;

import lombok.Data;

/**
 * @author xiaozefeng
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVO ok(T data){
        return new ResultVO(0, "success", data);
    }


}
