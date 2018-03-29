package com.immoc.user.enums;

/**
 * @author xiaozefeng
 */
public enum UserRoleEnum {
    SELLER("卖家"),
    BUYER("买家");

    private String msg;

    UserRoleEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
