
package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Spring3Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring3Application.class, args);
    }

}
