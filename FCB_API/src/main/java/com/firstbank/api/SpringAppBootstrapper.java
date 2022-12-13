package com.firstbank.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.firstbank.api"}, exclude = {SecurityAutoConfiguration.class
        , DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EntityScan(basePackages = { "com.firstbank.api.base.entity" })
//@EnableJpaRepositories(basePackages = { "com.firstbank.api.base.dao" })
//@EnableTransactionManagement
public class SpringAppBootstrapper extends SpringBootServletInitializer {

    /**
     * @param application
     * @description Spring Boot 上到 Ap Server 時, 啟動與初始化設定的 method
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringAppBootstrapper.class);
    }

    /**
     * @param args
     * @description Spring Boot 直接使用 jar 啟動時, 啟動與初始化設定的 method
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringAppBootstrapper.class);
        springApplication.run(args);
    }

}
