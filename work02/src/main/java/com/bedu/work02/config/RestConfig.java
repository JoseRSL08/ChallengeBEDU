package com.bedu.work02.config;

import com.bedu.work02.controllers.ContactController;
import com.bedu.work02.controllers.GreetingController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RestConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.addPathPrefix("/api/v1",HandlerTypePredicate.forBasePackage("controllers"));
    }

}
