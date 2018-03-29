package com.immoc.user.controller;

import com.immoc.user.constant.CookieConstant;
import com.immoc.user.entity.UserInfo;
import com.immoc.user.enums.UserRoleEnum;
import com.immoc.user.service.UserInfoService;
import com.immoc.user.utils.CookieUtil;
import com.immoc.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.immoc.user.constant.RedisConstant.TOKEN_TEMPLATE;
import static com.immoc.user.enums.ResultEnum.LOGIN_FAIL;
import static com.immoc.user.enums.ResultEnum.ROLE_ERROR;

/**
 * @author xiaozefeng
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyerLogin(String openid, HttpServletResponse response) {
        // 根据 openid查询数据
        UserInfo userInfo = userInfoService.getUserInfoByOpenid(openid);
        if (userInfo == null) {
            return ResultVO.error(LOGIN_FAIL);
        }
        // 判断角色
        if (userInfo.getRole() != UserRoleEnum.BUYER) {
            return ResultVO.error(ROLE_ERROR);
        }
        // cookie里设置openid的值
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVO.ok();
    }

    /**
     * 卖家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO sellerLogin(String openid, HttpServletResponse response,
                                @CookieValue(value = CookieConstant.TOKEN, required = false) String cookieToken) {
        if (!StringUtils.isEmpty(cookieToken) && !StringUtils.isEmpty(
                stringRedisTemplate.opsForValue().get(String.format(TOKEN_TEMPLATE, cookieToken)))) {
            return ResultVO.ok();
        }

        // 根据 openid查询数据
        UserInfo userInfo = userInfoService.getUserInfoByOpenid(openid);
        if (userInfo == null) {
            return ResultVO.error(LOGIN_FAIL);
        }
        // 判断角色
        if (userInfo.getRole() != UserRoleEnum.SELLER) {
            return ResultVO.error(ROLE_ERROR);
        }
        // 设置redis
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        // cookie里设置openid的值
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return ResultVO.ok();
    }


}
