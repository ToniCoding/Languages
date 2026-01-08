package com.ad02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ad02Application {
    public static void main(String[] args) {
        SpringApplication.run(Ad02Application.class, args);
    }
}
