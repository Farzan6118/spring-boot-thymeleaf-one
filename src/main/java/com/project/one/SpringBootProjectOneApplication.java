package com.project.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class SpringBootProjectOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectOneApplication.class, args);
    }

}
