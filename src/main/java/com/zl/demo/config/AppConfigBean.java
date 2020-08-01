package com.zl.demo.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: kele
 * @Description:
 * @Date: create in 2020/7/31 15:35
 */
@Component("appConfigBean")
@ToString
@Getter
@PropertySource(value = "classpath:env/${spring.profiles.active}.properties", encoding = "UTF-8")
public class AppConfigBean {

    @Value("${student.jdbc.url}")
    private String studentJdbcUrl;
    @Value("${jdbc.student.name}")
    private String jdbcStudentName;
    @Value("${jdbc.student.password}")
    private String jdbcStudentPassword;


    @Value("${teacher.jdbc.url}")
    private String teacherJdbcUrl;
    @Value("${jdbc.teacher.name}")
    private String jdbcTeacherName;
    @Value("${jdbc.teacher.password}")
    private String jdbcTeacherPassword;


    @Value("${jdbc.driverClassName:com.mysql.jdbc.Driver}")
    private String jdbcDriverClassName;
    @Value("${jdbc.initialSize:10}")
    private Integer jdbcInitialSize;
    @Value("${jdbc.maxActive:30}")
    private Integer jdbcMaxActive;

}
