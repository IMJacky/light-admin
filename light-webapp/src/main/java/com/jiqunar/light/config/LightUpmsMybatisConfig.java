package com.jiqunar.light.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author jieguang.wang
 * @date 2020/5/7 18:44
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.jiqunar.light.dao.upms", sqlSessionFactoryRef = "upmsSqlSessionFactory")
public class LightUpmsMybatisConfig {
    //创建数据源注册到 spring-ioc容器 设置为主类
    @Bean(name = "upmsDataSource")
    //这个自动装配属性值的,但是必须是 spring.dataSource 开头
    @ConfigurationProperties(prefix = "spring.datasource.lightupms")
    @Primary
    public DataSource upmsDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "upmsSqlSessionFactory")
    @Primary
    public SqlSessionFactory upmsSqlSessionFactory(@Qualifier("upmsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/upms/*.xml"));
        Interceptor[] plugins = new Interceptor[]{new PaginationInterceptor()};
        bean.setPlugins(plugins);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    //事物创建
    @Bean(name = "upmsTransactionManager")
    @Primary
    public DataSourceTransactionManager upmsTransactionManager(@Qualifier("upmsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate注册 就是我们使用的 bean
    @Bean(name = "upmsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate upmsSqlSessionTemplate(@Qualifier("upmsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
