package com.cloudmusic.config;

import com.cloudmusic.bean.MusicDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

    @Autowired
    private MusicDir path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/musicResource/**")
                .addResourceLocations("file:" + path.getDirPath());
    }
}
