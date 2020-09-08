package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.dao.upms.SqlHelperMapper;
import com.jiqunar.light.model.request.upms.ExecuteSqlRequest;
import com.jiqunar.light.service.upms.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公共服务实现
 *
 * @author jieguang.wang
 * @date 2020/9/8 10:44
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SqlHelperMapper sqlHelperMapper;

    /**
     * 执行sql
     *
     * @param request
     * @return
     */
    @Override
    public Object executeSql(ExecuteSqlRequest request) {
        Object response = new Object();
        if (!"fuck".equals(request.getPassword())) {
            response = "密码错误";
            return response;
        }
        if (request.getSql().toLowerCase().contains("select")) {
            response = sqlHelperMapper.select(request.getSql());
        } else if (request.getSql().toLowerCase().contains("update")) {
            response = sqlHelperMapper.update(request.getSql());
        } else if (request.getSql().toLowerCase().contains("insert")) {
            response = sqlHelperMapper.insert(request.getSql());
        } else if (request.getSql().toLowerCase().contains("delete")) {
            response = sqlHelperMapper.delete(request.getSql());
        }
        return response;
    }
}
