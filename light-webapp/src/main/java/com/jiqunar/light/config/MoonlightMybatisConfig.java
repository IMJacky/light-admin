package com.jiqunar.light.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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
@MapperScan(basePackages = "com.jiqunar.light.dao.moonlight", sqlSessionFactoryRef = "moonlightSqlSessionFactory")
public class MoonlightMybatisConfig {
    //创建数据源注册到 spring-ioc容器 设置为主类
    @Bean(name = "moonlightDataSource")
    //这个自动装配属性值的,但是必须是 spring.dataSource 开头
    @ConfigurationProperties(prefix = "spring.datasource.moonlight")
    public DataSource moonlightDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "moonlightSqlSessionFactory")
    public SqlSessionFactory moonlightSqlSessionFactory(@Qualifier("moonlightDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/moonlight/*.xml"));
        Interceptor[] plugins = new Interceptor[]{new PaginationInterceptor()};
        bean.setPlugins(plugins);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        //configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);//sql输出到控制台
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    //事物创建
    @Bean(name = "moonlightTransactionManager")
    public DataSourceTransactionManager moonlightTransactionManager(@Qualifier("moonlightDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate注册 就是我们使用的 bean
    @Bean(name = "moonlightSqlSessionTemplate")
    public SqlSessionTemplate moonlightSqlSessionTemplate(@Qualifier("moonlightSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
