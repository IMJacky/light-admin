package com.jiqunar.light.controller.moonlight;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.controller.BaseController;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.model.mq.MQConfig;
import com.jiqunar.light.model.request.BaseRequest;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.BillEditRequest;
import com.jiqunar.light.model.request.moonlight.BillListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.moonlight.AlipayBillCsvInfo;
import com.jiqunar.light.model.response.moonlight.AlipayBillExportInfo;
import com.jiqunar.light.model.response.moonlight.WepayBillCsvInfo;
import com.jiqunar.light.service.moonlight.BillService;
import com.jiqunar.light.serviceimpl.moonlight.AlipayBillExcelListener;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账单信息 前端控制器
 *
 * @author auto generator
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/BillEntity")
@Api(tags = "账单信息相关接口")
public class BillController extends BaseController {
    @Autowired
    private BillService billService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MQConfig mqConfig;

    /**
     * 新增账单信息
     *
     * @param billEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增账单信息")
    public BaseResponse save(@RequestBody BillEntity billEntity) {
        return BaseResponse.success(billService.save(billEntity));
    }

    /**
     * 删除账单信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除账单信息")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(billService.removeById(id));
    }

    /**
     * 修改账单信息
     *
     * @param billEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改账单信息")
    public BaseResponse update(@RequestBody BillEntity billEntity) {
        return BaseResponse.success(billService.updateById(billEntity));
    }

    /**
     * 查看所有账单信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有账单信息")
    public BaseResponse list() {
        return BaseResponse.success(billService.list());
    }

    /**
     * 查看单个账单信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个账单信息")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(billService.getById(id));
    }

    /**
     * 查看单个账单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个账单信息")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(billService.getById(id));
    }

    /**
     * 分页查看账单信息
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看账单信息")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(billService.page(request));
    }

    /**
     * 编辑账单信息
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑账单信息")
    public BaseResponse save(@RequestBody BillEditRequest request) {
        return billService.editBill(request);
    }

    /**
     * 根据主键Id+用户Id查看账单信息
     *
     * @param request
     * @return
     */
    @PostMapping("/getById")
    @ApiOperation("根据主键Id+用户Id查看账单信息")
    public BaseResponse getById(@RequestBody BillEditGetRequest request) {
        return BaseResponse.success(billService.getById(request));
    }

    /**
     * 按日期范围查看账单信息
     *
     * @return
     */
    @PostMapping("/getbydate")
    @ApiOperation("按日期范围查看账单信息")
    public BaseResponse getByDate(@RequestBody BillListRequest request) {
        request.setOpenId(baseRequest().getOperateName());
        return BaseResponse.success(billService.getByDate(request));
    }

    /**
     * 支付宝账单导入excel
     *
     * @param serviceFile
     */
    @SneakyThrows
    @PostMapping("/import")
    @ApiOperation("按日期范围查看账单信息")
    public void excelImport(@RequestParam(value = "file") MultipartFile serviceFile) {
        ExcelReader excelReader = null;
        InputStream in = null;
        try {
            in = serviceFile.getInputStream();
            excelReader = EasyExcel.read(in, AlipayBillExportInfo.class,
                    new AlipayBillExcelListener(billService)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(6).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
        } finally {
            in.close();
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    /**
     * 支付宝账单导入csv
     *
     * @param serviceFile
     */
    @SneakyThrows
    @PostMapping("/importCsv")
    @ApiOperation("支付宝账单导入csv")
    public BaseResponse importCsv(@RequestParam(value = "file") MultipartFile serviceFile) {
        if (serviceFile.getOriginalFilename().indexOf(".csv") < 0) {
            return BaseResponse.invalidParams("仅支持文件为csv格式的账单");
        }
        InputStream in = null;
        try {
            in = serviceFile.getInputStream();
            InputStreamReader is = new InputStreamReader(in, "GBK");
            ColumnPositionMappingStrategy<AlipayBillCsvInfo> mappingStrategy = new ColumnPositionMappingStrategy<>();
            mappingStrategy.setType(AlipayBillCsvInfo.class);
            CsvToBean<AlipayBillCsvInfo> build = new CsvToBeanBuilder<AlipayBillCsvInfo>(is)
                    .withMappingStrategy(mappingStrategy).withIgnoreQuotations(true).build();
            List<AlipayBillCsvInfo> alipayBillCsvInfoList = build.parse();
            if (alipayBillCsvInfoList.stream().noneMatch(m -> StringUtils.isNotBlank(m.getCreateDate()) &&
                    "交易创建时间".equals(m.getCreateDate().trim()) && StringUtils.isNotBlank(m.getIncomeExpend()))) {
                return BaseResponse.fail("请选择正确的支付宝账单");
            }
            alipayBillCsvInfoList = alipayBillCsvInfoList.stream().filter(m -> StringUtils.isNotBlank(m.getCreateDate()) &&
                    !"交易创建时间".equals(m.getCreateDate().trim()) && StringUtils.isNotBlank(m.getIncomeExpend())).collect(Collectors.toList());
            for (AlipayBillCsvInfo m : alipayBillCsvInfoList) {
                m.setOpenId(baseRequest().getOperateName());
                rabbitTemplate.convertAndSend(mqConfig.getAlipayBillExchange(), mqConfig.getAlipayBillRouteKey(),
                        JSON.toJSONString(m));
            }
        } catch (IOException ex) {
        } finally {
            in.close();
        }
        return BaseResponse.success(true);
    }

    /**
     * 微信账单导入csv
     *
     * @param serviceFile
     */
    @SneakyThrows
    @PostMapping("/importCsvWepay")
    @ApiOperation("微信账单导入csv")
    public BaseResponse importCsvWepay(@RequestParam(value = "file") MultipartFile serviceFile) {
        if (serviceFile.getOriginalFilename().indexOf(".csv") < 0) {
            return BaseResponse.invalidParams("仅支持文件为csv格式的账单");
        }
        InputStream in = null;
        try {
            in = serviceFile.getInputStream();
            InputStreamReader is = new InputStreamReader(in, "UTF-8");
            ColumnPositionMappingStrategy<WepayBillCsvInfo> mappingStrategy = new ColumnPositionMappingStrategy<>();
            mappingStrategy.setType(WepayBillCsvInfo.class);
            CsvToBean<WepayBillCsvInfo> build = new CsvToBeanBuilder<WepayBillCsvInfo>(is)
                    .withMappingStrategy(mappingStrategy).withIgnoreQuotations(true).build();
            List<WepayBillCsvInfo> wepayBillCsvInfoList = build.parse();
            if (wepayBillCsvInfoList.stream().noneMatch(m -> StringUtils.isNotBlank(m.getCreateDate()) &&
                    "交易时间".equals(m.getCreateDate().trim()) && StringUtils.isNotBlank(m.getIncomeExpend()))) {
                return BaseResponse.fail("请选择正确的微信账单");
            }
            wepayBillCsvInfoList = wepayBillCsvInfoList.stream().filter(m -> StringUtils.isNotBlank(m.getCreateDate()) &&
                    !"交易时间".equals(m.getCreateDate().trim()) && StringUtils.isNotBlank(m.getIncomeExpend())).collect(Collectors.toList());
            for (WepayBillCsvInfo m : wepayBillCsvInfoList) {
                m.setOpenId(baseRequest().getOperateName());
                rabbitTemplate.convertAndSend(mqConfig.getWepayBillExchange(), mqConfig.getWepayBillRouteKey(),
                        JSON.toJSONString(m));
            }
        } catch (IOException ex) {
        } finally {
            in.close();
        }
        return BaseResponse.success(true);
    }
}