package com.immoc.user.repository;

import com.immoc.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaozefeng
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

    UserInfo findByOpenid(String openid);
}
