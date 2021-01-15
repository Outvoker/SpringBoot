package com.dave.admin.config;

import com.dave.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xu Rui
 * @date 2021/1/13 15:20
 */

/**
 * 1. 编写一个拦截器实现HandlerInterceptor接口
 * 2. 拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 * 3. 指定拦截规则[如果是拦截所有，静态资源也会被拦截]
 * 4.
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //所有请求都会被拦截，包括静态资源
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/city");    //放行的请求
    }
}
