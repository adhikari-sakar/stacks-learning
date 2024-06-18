package com.example.stacks.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class StacksLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(StacksLearningApplication.class, args);
    }

}
