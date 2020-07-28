package com.jiqunar.light.config;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.jiqunar.light.controller.BaseController;
import com.jiqunar.light.model.entity.BaseEntity;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class CodeGenerator {
    /**
     * 读取控制台内容
     *
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!ipt.isEmpty()) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath);
        gc.setAuthor("auto generator");
        gc.setOpen(false);
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 覆盖生成的文件
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        Map<String, DataSourceConfig> dataSourceMap = new HashMap<>();
        Map<String, String> prefixMap = new HashMap<>();
        Map<String, Class<?>> baseEntityMap = new HashMap<>();
        // 数据源配置
        DataSourceConfig dscupms = new DataSourceConfig();
        dscupms.setUrl("jdbc:mysql://localhost:3306/light_upms?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dscupms.setDriverName("com.mysql.cj.jdbc.Driver");
        dscupms.setUsername("root");
        dscupms.setPassword("Wjg50058");
        dataSourceMap.put("upms", dscupms);
        prefixMap.put("upms", "lu_");
        baseEntityMap.put("upms", BaseEntity.class);

        // 数据源配置
        DataSourceConfig dscLog = new DataSourceConfig();
        dscLog.setUrl("jdbc:mysql://localhost:3306/light_log?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dscLog.setDriverName("com.mysql.cj.jdbc.Driver");
        dscLog.setUsername("root");
        dscLog.setPassword("Wjg50058");
        dataSourceMap.put("log", dscLog);
        prefixMap.put("log", "ll_");
        baseEntityMap.put("log", null);

        // 数据源配置
        DataSourceConfig dscMoonlight = new DataSourceConfig();
        dscMoonlight.setUrl("jdbc:mysql://localhost:3306/moonlight?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dscMoonlight.setDriverName("com.mysql.cj.jdbc.Driver");
        dscMoonlight.setUsername("root");
        dscMoonlight.setPassword("Wjg50058");
        dataSourceMap.put("moonlight", dscMoonlight);
        prefixMap.put("moonlight", "");
        baseEntityMap.put("moonlight", BaseEntity.class);

        // 包配置
        PackageConfig pc = new PackageConfig();
        String dbname = scanner("数据库：" + dataSourceMap.entrySet().stream().map(m -> m.getKey()).collect(Collectors.joining("，")));
        pc.setModuleName(null);
        pc.setParent("codegenerator");
        pc.setMapper("dao." + dbname);
        pc.setEntity("model.entity." + dbname);
        pc.setService("service." + dbname);
        pc.setServiceImpl("serviceimpl." + dbname);
        pc.setController("controller." + dbname);
        mpg.setPackageInfo(pc);

        mpg.setDataSource(dataSourceMap.get(dbname));
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/codegenerator/" + dbname
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                File file = new File(filePath);
                boolean exist = file.exists();
//                if (exist) {
//                    if (filePath.endsWith("Mapper.xml") || FileType.ENTITY == fileType) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
                //不存在的文件都需要创建
                return true;
            }
        });
        mpg.setCfg(cfg);

        // 模板配置
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);//不覆盖传null
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setRestControllerStyle(true);
        if (baseEntityMap.get(dbname) != null) {
            strategy.setSuperEntityClass(baseEntityMap.get(dbname));
        }
        strategy.setSuperControllerClass(BaseController.class);
        strategy.setTablePrefix(prefixMap.get(dbname));
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setLogicDeleteFieldName("is_deleted");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
