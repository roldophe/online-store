package com.onlinestore.api.file.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileResourceHandlerCofig implements WebMvcConfigurer {

    @Value("${file.client-path}")
    private String fileResClientPath;
    @Value("${file.server-path}")
    private String fileResServerPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileResClientPath)
                .addResourceLocations("file:" +fileResServerPath);
    }
}
