package com.immoc.user.vo;

import com.immoc.user.enums.ResultEnum;
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

    private ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    @SuppressWarnings("unchecked")
    public static <T> ResultVO ok(T data) {
        return new ResultVO(0, "success", data);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO ok() {
        return new ResultVO(0, "success", null);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO error(ResultEnum resultEnum) {
        return new ResultVO(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

}
