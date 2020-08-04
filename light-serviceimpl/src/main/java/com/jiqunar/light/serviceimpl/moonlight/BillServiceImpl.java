package com.jiqunar.light.serviceimpl.moonlight;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.dao.moonlight.WxUserMapper;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.dao.moonlight.BillMapper;
import com.jiqunar.light.model.entity.moonlight.WxUserEntity;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.BillEditRequest;
import com.jiqunar.light.model.request.moonlight.BillListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.moonlight.BillAggregation;
import com.jiqunar.light.model.response.moonlight.BillDetail;
import com.jiqunar.light.model.response.moonlight.BillEditResponse;
import com.jiqunar.light.model.response.moonlight.BillListResponse;
import com.jiqunar.light.service.moonlight.BillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账单信息 服务实现类
 *
 * @author auto generator
 * @since 2020-07-28
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, BillEntity> implements BillService {
    @Autowired
    private WxUserMapper wxUserMapper;

    /**
     * 分页获取账单信息
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
     * 按日期范围查看账单信息
     *
     * @param request
     * @return
     */
    @Override
    public BillListResponse getByDate(BillListRequest request) {
        BillListResponse response = new BillListResponse();
        List<BillAggregation> billAggregationList = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper<BillEntity>()
                .eq("open_id", request.getOpenId());
        queryWrapper.orderByDesc("bill_date");
        if (request.getStartDate() != null) {
            queryWrapper.gt("bill_date", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            queryWrapper.lt("bill_date", request.getEndDate().plusDays(1));
        }
        List<BillEntity> billEntityList = list(queryWrapper);
        Map<LocalDate, List<BillEntity>> billGroup = billEntityList.stream().collect(Collectors.groupingBy(m ->
                m.getBillDate().toLocalDate()));
        billGroup.entrySet().forEach(m -> {
            BillAggregation billAggregation = new BillAggregation();
            billAggregation.setBillDate(m.getKey());
            billAggregation.setBillWeek(DateUtils.getDayOfTheWeek(m.getKey()));
            billAggregation.setEarningAmount(m.getValue().stream().filter(e -> e.getBillType() == 1)
                    .map(e -> e.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
            billAggregation.setExpenseAmount(m.getValue().stream().filter(e -> e.getBillType() == 0)
                    .map(e -> e.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
            billAggregation.setBillDetailList(m.getValue().stream().map(e -> BillDetail.builder()
                    .amount(e.getAmount()).billTime(DateUtils.getTime(e.getBillDate()))
                    .tag(e.getTag()).subTag(e.getSubTag()).id(e.getId()).billType(e.getBillType()).build())
                    .collect(Collectors.toList()));
            billAggregationList.add(billAggregation);
        });
        response.setBillAggregationList(billAggregationList);
        return response;
    }

    /**
     * 查看账单信息
     *
     * @param request
     * @return
     */
    @Override
    public BillEditResponse getById(BillEditGetRequest request) {
        BillEditResponse response = new BillEditResponse();
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<BillEntity>()
                .eq(BillEntity::getOpenId, request.getOpenId());
        List<BillEntity> billEntityList = list(queryWrapper);
        Map<String, List<BillEntity>> billGroupTag = billEntityList.stream().filter(m -> !StringUtils.isBlank(m.getTag())).collect(Collectors.groupingBy(m -> m.getTag()));
        Map<String, List<String>> tagMap = new HashMap<>();
        billGroupTag.entrySet().forEach(m -> {
            tagMap.put(m.getKey(), m.getValue().stream().map(BillEntity::getSubTag).distinct().collect(Collectors.toList()));
        });
        response.setTagMap(tagMap);
        if (request.getBillId() != null && request.getBillId() > 0) {
            BillEntity billEntity = getById(request.getBillId());
            response.setTag(billEntity.getTag());
            response.setSubTag(billEntity.getSubTag());
            response.setAmount(billEntity.getAmount());
            response.setBillTime(DateUtils.getTime(billEntity.getCreateDate()));
            response.setBillType(billEntity.getBillType());
            response.setDescription(billEntity.getDescription());
            response.setId(billEntity.getId());
            response.setBillDate(billEntity.getBillDate());
        } else {
            response.setBillDate(LocalDateTime.now());
        }
        return response;
    }

    /**
     * 编辑账单信息
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse editBill(BillEditRequest request) {
        WxUserEntity wxuserEntity = wxUserMapper.selectOne(new LambdaQueryWrapper<WxUserEntity>()
                .eq(WxUserEntity::getOpenId, request.getOpenId()));
        if (wxuserEntity == null) {
            return BaseResponse.fail("用户不存在！");
        }
        BillEntity billEntity = new BillEntity();
        if (request.getBillId() != null && request.getBillId() > 0) {
            billEntity = getById(request.getBillId());
            if (billEntity == null) {
                return BaseResponse.fail("账单不存在！");
            }
        }
        billEntity.setAmount(request.getAmount());
        billEntity.setBillType(request.getBillType());
        billEntity.setDescription(request.getDescription());
        billEntity.setOpenId(wxuserEntity.getOpenId());
        billEntity.setBillDate(request.getBillDate());
        billEntity.setTag(request.getTag());
        billEntity.setSubTag(request.getSubTag());
        billEntity.setUpdateDate(LocalDateTime.now());
        if (request.getBillId() != null && request.getBillId() > 0) {
            return BaseResponse.success(updateById(billEntity));
        } else {
            billEntity.setCreateDate(LocalDateTime.now());
            return BaseResponse.success(save(billEntity));
        }
    }
}
