package com.quandev.springbootrestcurdpostgress;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootRestCurdPostgressApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestCurdPostgressApplication.class, args);
        log.info("Info");
        log.error("error");
        log.debug("debug");
        log.warn("warn");
    }

}
