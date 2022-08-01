package com.crud.minerals.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/adminPanel").setViewName("adminPanel");
        registry.addViewController("/mineralSearch").setViewName("mineralSearch");
        registry.addViewController("/login").setViewName("login");
    }
}