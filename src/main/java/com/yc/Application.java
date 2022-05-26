package com.yc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.yc.config.MywebFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.text.resources.FormatData;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@MapperScan("com.yc.mapper")
@EnableTransactionManagement
@ServletComponentScan
public class Application implements WebMvcConfigurer{
    @Autowired
    MywebFilter mywebFilter;

    //laucher类
    public static void main(String[] args) {
        //                                            args:命令行参数
        SpringApplication.run(com.yc.Application.class, args);
    }
    //    @Bean
//    public TomcatServletWebServerFactory servletContainer(){
//        return new TomcatServletWebServerFactory(8081) ;
//
//    }
    @Bean
    public DataSourceTransactionManager jdbcTransactionManager(DataSource ds) {
        //事务管理器的类型与datasource的类型有关，相关的事务管理器有：hibernate，jdo，jpa等等
        DataSourceTransactionManager jdbcTransactionManager = new DataSourceTransactionManager();
        jdbcTransactionManager.setDataSource(ds);
        return jdbcTransactionManager;

    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mywebFilter)
                .addPathPatterns("/teacherweb.html")
                .addPathPatterns("/teacherui.html")
                .addPathPatterns("/teacheruiplus.html")
                .addPathPatterns("/teacherplusweb.html")
                .addPathPatterns("/student.html")
                .addPathPatterns("/studentweb.html");
    }
}