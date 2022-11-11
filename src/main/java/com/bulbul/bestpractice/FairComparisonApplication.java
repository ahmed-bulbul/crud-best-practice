package com.bulbul.bestpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class FairComparisonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FairComparisonApplication.class, args);
    }

}
