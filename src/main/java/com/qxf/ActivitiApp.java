package com.qxf;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ActivitiApp
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/5 23:09
 **/
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = {"com.qxf.mapper"})
public class ActivitiApp {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiApp.class,args);
    }
}
