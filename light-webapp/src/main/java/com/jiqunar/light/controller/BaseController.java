package com.jiqunar.light.controller;

import com.jiqunar.light.model.request.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 */
@RestController
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public BaseRequest baseRequest() {
        BaseRequest baseRequest = new BaseRequest();
        if (request != null) {
            baseRequest.setOperateId((Long) request.getAttribute("userid"));
            baseRequest.setOperateName((String) request.getAttribute("username"));
        }
        return baseRequest;
    }

    public String getClientIp() {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
