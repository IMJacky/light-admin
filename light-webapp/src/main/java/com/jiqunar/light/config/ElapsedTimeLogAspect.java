package com.jiqunar.light.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiqunar.light.common.RedistUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 请求响应，耗时的日志记录
 *
 * @author jieguang.wang
 * @date 2020/5/7 19:41
 */
@Aspect
@Configuration
public class ElapsedTimeLogAspect {
    private Logger logger = LoggerFactory.getLogger(ElapsedTimeLogAspect.class);

    //环绕切入点，第一个*表示任何返回值，第二个表示任何方法，最后表示任何参数
    @Around("execution(public * com.jiqunar.light.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //执行目标方法内容，获取执行结果
        Object result = joinPoint.proceed();

        String queryString = request.getQueryString();
        StringBuilder requestString = new StringBuilder();
        ObjectMapper mapperRequest = new ObjectMapper();
        for (Object arg : joinPoint.getArgs()) {
            try {
                requestString.append(mapperRequest.writeValueAsString(arg));
            } catch (Exception ex) {

            }
        }
        //打印请求路径和请求参数
        logger.info("Method：{}；RequestURI：{}；ElapsedTime：{}ms；Request：{}；Response：{}",
                request.getMethod(),
                request.getRequestURI() + (StringUtils.isBlank(queryString) ? "" : "?" + queryString),
                System.currentTimeMillis() - startTime,
                requestString,
                new ObjectMapper().writeValueAsString(result));

        return result;
    }
}
