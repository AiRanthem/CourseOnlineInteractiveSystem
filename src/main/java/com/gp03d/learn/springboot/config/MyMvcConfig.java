package com.gp03d.learn.springboot.config;

import com.gp03d.learn.springboot.component.LoginHandlerInterceptor;
import com.gp03d.learn.springboot.component.MyLocaleResorver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/user/login","/", "/login.html","/register","/setInfo","/admin");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/sss").setViewName("success");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResorver();
    }
}
