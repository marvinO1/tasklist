package org.rib.tasklist.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TasklListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasklListApplication.class, args);
    }

} 
