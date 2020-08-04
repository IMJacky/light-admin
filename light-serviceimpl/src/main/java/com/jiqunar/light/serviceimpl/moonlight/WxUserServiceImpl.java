package com.jiqunar.light.serviceimpl.moonlight;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.common.WechatUtils;
import com.jiqunar.light.dao.moonlight.VisitRecordMapper;
import com.jiqunar.light.model.entity.moonlight.VisitRecordEntity;
import com.jiqunar.light.model.entity.moonlight.WxUserEntity;
import com.jiqunar.light.dao.moonlight.WxUserMapper;
import com.jiqunar.light.model.request.BaseWxRequest;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.WxLoginRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.moonlight.CodeSessionResponse;
import com.jiqunar.light.service.moonlight.WxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

/**
 * 微信用户信息 服务实现类
 *
 * @author auto generator
 * @since 2020-07-28
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUserEntity> implements WxUserService {
    @Autowired
    private VisitRecordMapper visitRecordMapper;

    /**
     * 分页获取微信用户信息
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(PageRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        page = this.page(page);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 微信登录
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse wxLogin(WxLoginRequest request) {
        CodeSessionResponse codeSession = WechatUtils.getCodeSession(request.getCode());
        if (codeSession != null && codeSession.getErrcode() == null) {
            WxUserEntity wxUser = getOne(new LambdaQueryWrapper<WxUserEntity>().eq(WxUserEntity::getOpenId, codeSession.getOpenid()));
            if (wxUser == null) {
                wxUser = new WxUserEntity();
            }
            wxUser.setOpenId(codeSession.getOpenid());
            wxUser.setUpdateDate(LocalDateTime.now());
            wxUser.setLastVisitDate(LocalDateTime.now());
            wxUser.setSessionKey(codeSession.getSession_key());
            wxUser.setAvatarUrl(request.getAvatarUrl());
            wxUser.setNickName(request.getNickName());
            if (wxUser.getId() == null) {
                wxUser.setCreateDate(LocalDateTime.now());
                save(wxUser);
            } else {
                updateById(wxUser);
            }
            return BaseResponse.success(wxUser.getOpenId());
        } else {
            return BaseResponse.fail(codeSession.getErrmsg());
        }
    }

    /**
     * 新增访问记录
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse addVisitRecord(BaseWxRequest request) {
        WxUserEntity wxUser = getOne(new LambdaQueryWrapper<WxUserEntity>()
                .eq(WxUserEntity::getOpenId, request.getOpenId()));
        if (wxUser != null) {
            wxUser.setUpdateDate(LocalDateTime.now());
            wxUser.setLastVisitDate(LocalDateTime.now());

            VisitRecordEntity visitrecordEntity = new VisitRecordEntity();
            visitrecordEntity.setCreateDate(LocalDateTime.now());
            visitrecordEntity.setOpenId(wxUser.getOpenId());
            visitrecordEntity.setUpdateDate(LocalDateTime.now());
            return BaseResponse.success(updateById(wxUser) && visitRecordMapper.insert(visitrecordEntity) > 0);
        }
        return BaseResponse.fail("用户不存在");
    }
}
