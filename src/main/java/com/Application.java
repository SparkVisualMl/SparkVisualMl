package com;

import com.spark.model.CustomMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartResolver;


@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})

@Configuration
@EntityScan(basePackages = {"com.spark"})
@ComponentScan(basePackages = {"com.spark"})
@ServletComponentScan(basePackages = {"com.spark"})
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class);
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver customMultipartResolver(){
        CustomMultipartResolver customMultipartResolver  = new CustomMultipartResolver();
        return customMultipartResolver;
    }

}

