package com.example.basicwebshop.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class ShopItemConfig implements WebMvcConfigurer {
    //resolve some locale standards---this will be called by thymeleaf. way how to present locale price in fragments
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("cz-CZ"));
        sessionLocaleResolver.setLocaleAttributeName("session.current.locale");
        return sessionLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("rate");
        return localeChangeInterceptor;
    }

//    @Bean
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(localeChangeInterceptor());
//    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("shopitem");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
