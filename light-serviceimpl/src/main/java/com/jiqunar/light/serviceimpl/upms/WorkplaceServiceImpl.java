package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.dao.upms.JobMapper;
import com.jiqunar.light.dao.upms.UserMapper;
import com.jiqunar.light.model.response.upms.WorkplaceResponse;
import com.jiqunar.light.service.upms.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工作台服务实现
 * @author jieguang.wang
 * @date 2020/9/7 14:40
 */
@Service
public class WorkplaceServiceImpl implements WorkplaceService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;

    /**
     * 工作台详情
     *
     * @return
     */
    @Override
    public WorkplaceResponse getWorkplace() {
        WorkplaceResponse workplaceResponse = new WorkplaceResponse();
        workplaceResponse.setDeptCount(deptMapper.selectCount(null));
        workplaceResponse.setJobCount(jobMapper.selectCount(null));
        workplaceResponse.setUserCount(userMapper.selectCount(null));
        return workplaceResponse;
    }
}
