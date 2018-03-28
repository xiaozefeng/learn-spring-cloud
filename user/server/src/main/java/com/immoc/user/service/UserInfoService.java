package com.immoc.user.service;

import com.immoc.user.entity.UserInfo;

/**
 * @author xiaozefeng
 */
public interface UserInfoService {
    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    UserInfo getUserInfoByOpenid(String openid);
}
