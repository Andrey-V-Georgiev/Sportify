package com.softuni.sportify.config;

import com.softuni.sportify.web.interceptors.FaviconInterceptor;
import com.softuni.sportify.web.interceptors.GreetingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppMvcWebConfig implements WebMvcConfigurer {

    private final GreetingInterceptor greetingInterceptor;
    private final FaviconInterceptor faviconInterceptor;

    @Autowired
    public AppMvcWebConfig(GreetingInterceptor greetingInterceptor,
                           FaviconInterceptor faviconInterceptor) {
        this.greetingInterceptor = greetingInterceptor;
        this.faviconInterceptor = faviconInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.greetingInterceptor);
        registry.addInterceptor(this.faviconInterceptor);
    }
}
