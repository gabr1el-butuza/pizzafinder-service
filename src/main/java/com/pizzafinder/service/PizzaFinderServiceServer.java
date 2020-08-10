package com.pizzafinder.service;

import com.pizzafinder.service.config.PizzaFinderServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({ PizzaFinderServiceConfig.class })
public class PizzaFinderServiceServer {

    public static void main(String[] args) {
        SpringApplication.run(PizzaFinderServiceServer.class, args);
    }
}
