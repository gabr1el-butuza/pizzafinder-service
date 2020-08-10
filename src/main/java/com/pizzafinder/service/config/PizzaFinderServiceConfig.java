package com.pizzafinder.service.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "com.pizzafinder.service.api",
        "com.pizzafinder.service.controller",
        "com.pizzafinder.service.service",
        "com.pizzafinder.service.repository"
})
@Import({DbConfig.class, WebSecurityConfig.class})
public class PizzaFinderServiceConfig {
}
