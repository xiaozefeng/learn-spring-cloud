package com.immoc.user.service.impl;

import com.immoc.user.entity.UserInfo;
import com.immoc.user.repository.UserInfoRepository;
import com.immoc.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaozefeng
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getUserInfoByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
