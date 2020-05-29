package com.jiqunar.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LightApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightApplication.class, args);
    }
}
