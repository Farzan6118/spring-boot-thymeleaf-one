package com.project.one;

import com.project.one.controller.PersonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootProjectOneApplication {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringBootProjectOneApplication.class, args);
    }

}
