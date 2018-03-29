package com.immoc.user.entity;

import com.immoc.user.enums.UserRoleEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author xiaozefeng
 */
@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

}
