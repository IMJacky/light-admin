package com.jiqunar.light.serviceimpl.moonlight;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.model.response.moonlight.AlipayBillExportInfo;
import com.jiqunar.light.service.moonlight.BillService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付宝账单excel监听器
 *
 * @author jieguang.wang
 * @date 2020/10/13 19:30
 */
public class AlipayBillExcelListener extends AnalysisEventListener<AlipayBillExportInfo> {
    private BillService billService;
    public AlipayBillExcelListener(BillService billService) {
        this.billService = billService;
    }

    private static final int BATCH_COUNT = 5;
    private List<AlipayBillExportInfo> alipayBillExportInfoList = Lists.newArrayList();

    @Override
    public void invoke(AlipayBillExportInfo alipayBillExportInfo, AnalysisContext analysisContext) {
        alipayBillExportInfoList.add(alipayBillExportInfo);
        if (alipayBillExportInfoList.size() >= BATCH_COUNT) {
            saveData();
            alipayBillExportInfoList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    private void saveData() {
        if (CollectionUtils.isNotEmpty(alipayBillExportInfoList)) {
            List<BillEntity> billEntityList = Lists.newArrayList();
            alipayBillExportInfoList.stream().filter(m -> StringUtils.isNotBlank(m.getIncomeExpend())).forEach(m -> {
                BillEntity billEntity = new BillEntity();
                billEntity.setAmount(new BigDecimal(m.getAmount()));
                billEntity.setBillDate(DateUtils.getDateTime(m.getCreateDate()));
                billEntity.setBillType(m.getIncomeExpend().equals("收入") ? 1 : 0);
                billEntity.setDescription(m.getBoss() + "-" + m.getProductName());
                billEntity.setTag("支付宝");
                billEntityList.add(billEntity);
                //billService.save(billEntity);
            });
            billService.saveOrUpdateBatch(billEntityList);
        }
    }
}
