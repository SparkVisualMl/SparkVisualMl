package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})

@Configuration
@ComponentScan(basePackages = {"com.spark"})
@ServletComponentScan(basePackages = {"com.spark"})
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }
}
