package com.example.basicwebshop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Configuration
public class ShopItemServiceConfig {

    //tell spring what to do with the decimalformat --
    //in case of autowire spring will use this method
    @Bean
    public DecimalFormat averageFormatProvide(){
        return new DecimalFormat(".##", new DecimalFormatSymbols(Locale.ROOT));
    }
}
