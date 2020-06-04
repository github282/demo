package com.cloudmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class LanguageResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取参数
        String l = httpServletRequest.getParameter("l");
        String header = httpServletRequest.getHeader("Accept-Language");
        //如果没有传入，就返回默认的
        Locale locale  = null;
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale= new Locale(split[0],split[1]);
        }else {
            // Accept-language: en-US, en;q=0.9, zh-CN;q=0.8, zh;q=0.7
            String[] splits = header.split(",");
            String[] split = splits[0].split("-");
            locale= new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest,
                          @Nullable HttpServletResponse httpServletResponse,
                          @Nullable Locale locale) {

    }

    @Bean
    public LocaleResolver localeResolver(){
        return new com.cloudmusic.config.LanguageResolver();
    }
}
