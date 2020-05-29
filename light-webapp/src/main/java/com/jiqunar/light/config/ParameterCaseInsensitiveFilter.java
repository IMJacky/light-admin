package com.jiqunar.light.config;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * 大小写的过滤器
 *
 * @author jieguang.wang
 * @date 2020/5/8 18:36
 */
@Order(1)
@WebFilter(filterName = "caseInsensitiveFilter", urlPatterns = "/*")
public class ParameterCaseInsensitiveFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new CaseInsensitiveParameterNameHttpServletRequest(httpServletRequest), httpServletResponse);
    }

    public static class CaseInsensitiveParameterNameHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String[]> map = new CaseInsensitiveMap();//LinkedCaseInsensitiveMap有序

        public CaseInsensitiveParameterNameHttpServletRequest(HttpServletRequest request) {
            super(request);
            map.putAll(request.getParameterMap());
        }

        @Override
        public String getParameter(String name) {
            String[] array = this.map.get(name);
            if (array != null && array.length > 0) {
                return array[0];
            }
            return null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return Collections.unmodifiableMap(this.map);
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(this.map.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return this.map.get(name);
        }
    }
}
