package com.study.tx.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * 全局配置类
 *
 * @author doveylovey
 * @version v1.0.0
 * @email 1135782208@qq.com
 * @date 2021年02月02日
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.study.tx")
public class SystemConfig {
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?allowMultiQueries=true&useAffectedRows=true&autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf8&nullNamePatternMatchesAll=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        //sqlSessionTemplate.getMapper();
        return sqlSessionTemplate;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());

        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        // 设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        // 设置 MyBatis 配置文件所在的路径
        //sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(""));

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String mapperXmlPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "com/study/tx/*Mapper.xml";
        Resource[] resources = resolver.getResources(mapperXmlPath);
        // 设置 mapper.xml 文件的路径
        sqlSessionFactoryBean.setMapperLocations(resources);
        // 设置类型别名所在的包路径
        sqlSessionFactoryBean.setTypeAliasesPackage("com.study.tx.entity");
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 也可以是在配置类中使用 @MapperScan("com.study.tx.mapper") 注解
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.study.tx.mapper");
        return mapperScannerConfigurer;
    }
}
