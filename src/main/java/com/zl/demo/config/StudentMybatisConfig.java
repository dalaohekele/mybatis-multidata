package com.zl.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: kele
 * @Description:
 * @Date:
 */
@Configuration
@MapperScan(basePackages = {"com.zl.demo.mapper.student"}, sqlSessionFactoryRef = "studentSqlSessionFactory")
public class StudentMybatisConfig {

    @Autowired
    private AppConfigBean appConfigBean;

    @Bean(name = "studentDataSource")
    DataSource mockDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(appConfigBean.getStudentJdbcUrl());
        dataSource.setDriverClassName(appConfigBean.getJdbcDriverClassName());
        dataSource.setUsername(appConfigBean.getJdbcStudentName());
        dataSource.setPassword(appConfigBean.getJdbcStudentPassword());
        dataSource.setFilters("stat");
        List<String> initSqls = new ArrayList<>();
        initSqls.add("set names utf8mb4");
        dataSource.setConnectionInitSqls(initSqls);
        dataSource.setMaxActive(appConfigBean.getJdbcMaxActive());
        dataSource.setInitialSize(appConfigBean.getJdbcInitialSize());
        return dataSource;
    }

    @Bean(name = "studentSqlSessionFactory")
    @Primary
    public SqlSessionFactory studentSqlSessionFactory() throws SQLException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mockDataSource());
        factoryBean.setTypeAliasesPackage("com.zl.deml.entity.student");
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        //properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "pageNum=start;count=countSql");
        pageHelper.setProperties(properties);
        // 添加插件
        factoryBean.setPlugins(new Interceptor[]{pageHelper});
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            factoryBean
                    .setMapperLocations(resolver.getResources("classpath*:/mybatis/student/*.xml"));
            return factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "studentTransactionManager")
    public DataSourceTransactionManager studentTransactionManager() {
        try {
            return new DataSourceTransactionManager(mockDataSource());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
