package com.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GraduationWebBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GraduationWebBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start...");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*")
//                        .allowedOrigins("http//:localhost:4200/", "http//:localhost:8081/");
                        .allowedOrigins("*");
            }
        };
    }
}
