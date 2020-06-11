package com.cloudmusic.config;

import com.cloudmusic.bean.ResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

//    @Autowired
//    private ResourceProperties resource;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        for (int i = 0; i > resource.getResourceList().size(); i++){
//            System.out.println(resource.getResourceList().toString());
//            registry.addResourceHandler(resource.getResourceList().get(i).getHandler())
//                    .addResourceLocations(resource.getResourceList().get(i).getLocations());
//        }
        registry.addResourceHandler("/musicResource/**").addResourceLocations("file:D:/CloudMusicFile/");
    }
}
