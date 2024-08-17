package com.tia102g3.adminlogin.model;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<AdminLoginFilter> adminLoginFilter() {
        FilterRegistrationBean<AdminLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminLoginFilter());
        registrationBean.addUrlPatterns("/admin/*"); // 适用于所有 /admin 路径的请求
        return registrationBean;
    }
}
