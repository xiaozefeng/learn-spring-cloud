package com.immoc.user.enums;

import lombok.Getter;

/**
 * @author xiaozefeng
 */
@Getter
public enum ResultEnum {
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(1, "角色权限有误"),
    ;

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
