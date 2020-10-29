package com.jiqunar.light.serviceimpl.moonlight;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.common.WechatUtils;
import com.jiqunar.light.dao.moonlight.VisitRecordMapper;
import com.jiqunar.light.model.entity.moonlight.VisitRecordEntity;
import com.jiqunar.light.model.entity.moonlight.WxUserEntity;
import com.jiqunar.light.dao.moonlight.WxUserMapper;
import com.jiqunar.light.model.request.BaseWxRequest;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.VisitRequest;
import com.jiqunar.light.model.request.moonlight.WxLoginRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.moonlight.CodeSessionResponse;
import com.jiqunar.light.model.response.moonlight.WechatUserDecrypt;
import com.jiqunar.light.model.response.moonlight.WxLoginResponse;
import com.jiqunar.light.service.moonlight.WxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.UUID;

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
    @Autowired
    private RedistUtils redistUtils;

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
            WechatUserDecrypt wechatUserDecrypt = WechatUtils.decryptData(request.getEncryptedData(), request.getIv(), codeSession.getSession_key());
            if (wechatUserDecrypt != null) {
                // sessionKey是否已经过期
                boolean sessionKeyIsExpire = false;
                WxLoginResponse wxLoginResponse = new WxLoginResponse();
                WxUserEntity wxUser = getOne(new LambdaQueryWrapper<WxUserEntity>().eq(WxUserEntity::getOpenId, codeSession.getOpenid()));
                if (wxUser == null) {
                    wxUser = new WxUserEntity();
                } else {
                    sessionKeyIsExpire = !wxUser.getSessionKey().equals(codeSession.getSession_key());
                }
                wxUser.setOpenId(codeSession.getOpenid());
                wxUser.setLastVisitDate(LocalDateTime.now());
                wxUser.setSessionKey(codeSession.getSession_key());
                wxUser.setAvatarUrl(wechatUserDecrypt.getAvatarUrl());
                wxUser.setNickName(wechatUserDecrypt.getNickName());
                wxUser.setCity(wechatUserDecrypt.getCity());
                wxUser.setCountry(wechatUserDecrypt.getCountry());
                wxUser.setGender(wechatUserDecrypt.getGender());
                wxUser.setLanguage(wechatUserDecrypt.getLanguage());
                wxUser.setProvince(wechatUserDecrypt.getProvince());
                if (wxUser.getId() == null) {
                    wxUser.setCreateDate(LocalDateTime.now());
                    save(wxUser);
                } else {
                    wxUser.setUpdateDate(LocalDateTime.now());
                    updateById(wxUser);
                }

                String userKey = wxUser.getId() + "_" + wxUser.getOpenId();
                // 如果过期，删除老的token
                if (sessionKeyIsExpire) {
                    String oldToken = redistUtils.get(userKey);
                    if (!StringUtils.isBlank(oldToken)) {
                        redistUtils.delete(oldToken);
                    }
                }

                String token = UUID.randomUUID().toString().replace("-", "");
                redistUtils.put(token, userKey);
                redistUtils.put(userKey, token);

                wxLoginResponse.setToken(token);
                wxLoginResponse.setUserInfo(new WxLoginResponse.WechatUserInfo(wechatUserDecrypt.getNickName(), wechatUserDecrypt.getAvatarUrl()));
                return BaseResponse.success(wxLoginResponse);
            } else {
                return BaseResponse.fail("微信用户信息解密失败");
            }
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
    public BaseResponse addVisitRecord(VisitRequest request) {
        WxUserEntity wxUser = getOne(new LambdaQueryWrapper<WxUserEntity>()
                .eq(WxUserEntity::getOpenId, request.getOpenId()));
        if (wxUser != null) {
            wxUser.setUpdateDate(LocalDateTime.now());
            wxUser.setLastVisitDate(LocalDateTime.now());
            wxUser.setAvatarUrl(request.getAvatarUrl());
            wxUser.setNickName(request.getNickName());
            wxUser.setCity(request.getCity());
            wxUser.setCountry(request.getCountry());
            wxUser.setGender(request.getGender());
            wxUser.setLanguage(request.getLanguage());
            wxUser.setProvince(request.getProvince());

            VisitRecordEntity visitrecordEntity = new VisitRecordEntity();
            visitrecordEntity.setCreateDate(LocalDateTime.now());
            visitrecordEntity.setOpenId(wxUser.getOpenId());
            visitrecordEntity.setUpdateDate(LocalDateTime.now());
            return BaseResponse.success(updateById(wxUser) && visitRecordMapper.insert(visitrecordEntity) > 0);
        }
        return BaseResponse.fail("用户不存在");
    }
}
