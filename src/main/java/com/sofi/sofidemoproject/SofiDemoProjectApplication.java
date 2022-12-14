package com.sofi.sofidemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SofiDemoConfigProperties.class)
public class SofiDemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiDemoProjectApplication.class, args);
    }

}
