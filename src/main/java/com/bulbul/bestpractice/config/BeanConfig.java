package com.bulbul.bestpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * BeanConfig
 * @author  BulbulAhmed;
 */

@Configuration
public class BeanConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
