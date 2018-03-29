package com.immoc.apigateway.filter;

import com.immoc.apigateway.constant.RedisConstant;
import com.immoc.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.immoc.apigateway.constant.CookieConstant.TOKEN;
import static com.immoc.apigateway.constant.OrderConstant.ORDER_FINISH_URL;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限拦截
 *
 * @author xiaozefeng
 */
@Component
public class AuthSellerFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        if (ORDER_FINISH_URL.equals(requestURI)) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        /**
         * order/finish 只能卖家访问 (cookie中有token,并且对应的redis值)
         */

        Optional<Cookie> cookieOptional = CookieUtil.get(request, TOKEN);
        Cookie cookie = cookieOptional.orElse(null);
        if (cookie == null ||
                StringUtils.isEmpty(cookie.getValue()) ||
                StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
        }
        return null;
    }
}
