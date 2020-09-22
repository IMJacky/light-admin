package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.dao.log.LogMapper;
import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.dao.upms.JobMapper;
import com.jiqunar.light.dao.upms.UserMapper;
import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.model.entity.upms.UserEntity;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.request.log.LogListRequest;
import com.jiqunar.light.model.response.common.BarResponse;
import com.jiqunar.light.model.response.upms.WorkplaceResponse;
import com.jiqunar.light.service.upms.WorkplaceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 工作台服务实现
 *
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
    @Autowired
    private LogMapper logMapper;

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

        List<WorkplaceResponse.Function> functionList = new ArrayList<>();
        List<DeptEntity> deptEntityList = deptMapper.selectList(null);
        List<UserEntity> userEntityList = userMapper.selectList(null);
        Collections.shuffle(userEntityList);
        UserEntity userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function = new WorkplaceResponse.Function();
        function.setAvatarUrl(userEntity.getAvatarUrl());
        function.setCompleteDate(LocalDate.of(2020, 6, 20));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function.setDeveloper(userEntity.getNickName());
        function.setTitle("框架搭建");
        function.setDescription("前端：Ant Design Vue Pro；后端：Spring Boot，Redis，MySql，MyBatis Plus，Druid，Swagger，拦截器，过滤器，全局异常处理，日志，代码生成，多数据源");

        Collections.shuffle(userEntityList);
        userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function1 = new WorkplaceResponse.Function();
        function1.setAvatarUrl(userEntity.getAvatarUrl());
        function1.setCompleteDate(LocalDate.of(2020, 7, 15));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function1.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function1.setDeveloper(userEntity.getNickName());
        function1.setTitle("系统设计");
        function1.setDescription("对象以及数据库表：用户，角色，资源（菜单/按钮），用户所属角色（可一对多），角色所拥有的资源以及基础信息包括部门，岗位");

        Collections.shuffle(userEntityList);
        userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function2 = new WorkplaceResponse.Function();
        function2.setAvatarUrl(userEntity.getAvatarUrl());
        function2.setCompleteDate(LocalDate.of(2020, 8, 20));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function2.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function2.setDeveloper(userEntity.getNickName());
        function2.setTitle("权限管理");
        function2.setDescription("前期的前后端交互，针对后端所配置的用户权限动态来生成系统菜单，以及用户信息，登录");

        Collections.shuffle(userEntityList);
        userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function3 = new WorkplaceResponse.Function();
        function3.setAvatarUrl(userEntity.getAvatarUrl());
        function3.setCompleteDate(LocalDate.of(2020, 8, 28));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function3.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function3.setDeveloper(userEntity.getNickName());
        function3.setTitle("功能完善");
        function3.setDescription("新增系统操作日志，新增SQL执行功能");

        Collections.shuffle(userEntityList);
        userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function4 = new WorkplaceResponse.Function();
        function4.setAvatarUrl(userEntity.getAvatarUrl());
        function4.setCompleteDate(LocalDate.of(2020, 9, 15));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function4.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function4.setDeveloper(userEntity.getNickName());
        function4.setTitle("自动化构建/部署");
        function4.setDescription("纯Docker容器部署到腾讯云（1C 2G）上，包括数据库，Nginx，缓存等等，使用Jenkins进行CI/CD；可惜的是前端没弄自动化，因为服务器资源不够用，动不动就TM挂掉了（垃圾Java）");

        Collections.shuffle(userEntityList);
        userEntity = userEntityList.get(0);
        WorkplaceResponse.Function function5 = new WorkplaceResponse.Function();
        function5.setAvatarUrl(userEntity.getAvatarUrl());
        function5.setCompleteDate(LocalDate.of(2020, 12, 30));
        if (userEntity.getDeptId() != null && userEntity.getDeptId() > 0) {
            Long userId = userEntity.getDeptId();
            function5.setDeptName(deptEntityList.stream().filter(m -> m.getId().equals(userId)).findFirst().get().getDeptName());
        }
        function5.setDeveloper(userEntity.getNickName());
        function5.setTitle("其他");
        function5.setDescription("还有工作面板，个人页面等等，还有好多功能要完善，慢慢来吧~");

        functionList.add(function);
        functionList.add(function1);
        functionList.add(function2);
        functionList.add(function3);
        functionList.add(function4);
        functionList.add(function5);

        workplaceResponse.setFunctionList(functionList);

        List<WorkplaceResponse.QuickNav> quickNavList = new ArrayList<>();
        WorkplaceResponse.QuickNav quickNav = new WorkplaceResponse.QuickNav();
        quickNav.setTitle("前端Github");
        quickNav.setUrl("https://github.com/IMJacky/Light.Web");

        WorkplaceResponse.QuickNav quickNav1 = new WorkplaceResponse.QuickNav();
        quickNav1.setTitle("后端Github");
        quickNav1.setUrl("https://github.com/IMJacky/light-admin");

        WorkplaceResponse.QuickNav quickNav2 = new WorkplaceResponse.QuickNav();
        quickNav2.setTitle("系统预览");
        quickNav2.setUrl("https://jiqunar.com/dashboard/workplace");

        WorkplaceResponse.QuickNav quickNav3 = new WorkplaceResponse.QuickNav();
        quickNav3.setTitle("接口文档");
        quickNav3.setUrl("https://api.jiqunar.com/swagger-ui.html");

        WorkplaceResponse.QuickNav quickNav4 = new WorkplaceResponse.QuickNav();
        quickNav4.setTitle("自动化部署");
        quickNav4.setUrl("http://ci.jiqunar.com");

        WorkplaceResponse.QuickNav quickNav5 = new WorkplaceResponse.QuickNav();
        quickNav5.setTitle("数据库监控");
        quickNav5.setUrl("http://api.jiqunar.com/druid/login.html");

        quickNavList.add(quickNav);
        quickNavList.add(quickNav1);
        quickNavList.add(quickNav2);
        quickNavList.add(quickNav3);
        quickNavList.add(quickNav4);
        quickNavList.add(quickNav5);
        workplaceResponse.setQuickNavList(quickNavList);

        LogListRequest logListRequest = new LogListRequest();
        LocalDate beginDate = LocalDate.now().plusDays(-7);
        LocalDate endDate = LocalDate.now().plusDays(1);
        logListRequest.setBeginDate(beginDate);
        logListRequest.setEndDate(endDate);
        logListRequest.setLogType(LogTypeEnum.System.getCode());
        logListRequest.setLogSubType(LogSubTypeEnum.Login.getCode());
        List<BarResponse> barResponseList = new ArrayList<>();
        List<BarResponse> barResponseListExist = logMapper.logBar(logListRequest);
        Stream.iterate(beginDate, LocalDate -> LocalDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(beginDate, endDate))
                .map(LocalDate::getDayOfMonth).forEach(m -> {
            BarResponse barResponse = new BarResponse();
            barResponse.setX(m.toString() + "日");
            if (barResponseListExist.stream().anyMatch(n -> n.getX().equals(m.toString()))) {
                barResponse.setY(barResponseListExist.stream().filter(n -> n.getX().equals(m.toString())).findFirst().get().getY());
            } else {
                barResponse.setY(0L);
            }
            barResponseList.add(barResponse);
        });
        workplaceResponse.setBarLoginList(barResponseList);

        return workplaceResponse;
    }
}
