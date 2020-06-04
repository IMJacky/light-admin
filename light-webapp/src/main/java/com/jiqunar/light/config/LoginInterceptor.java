package com.jiqunar.light.config;

import com.google.gson.Gson;
import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author jieguang.wang
 * @date 2020/5/8 15:16
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedistUtils redistUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Access-Token");
        if (StringUtils.isBlank(token)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //response.getWriter().print("please login");
            response.getWriter().print(new Gson().toJson(BaseResponse.invalidToken("please login")));
            return false;
        }
        String redisToken = redistUtils.get(token);
        if (StringUtils.isBlank(redisToken)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //response.getWriter().print("token error");
            response.getWriter().print(new Gson().toJson(BaseResponse.invalidToken("token error")));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
