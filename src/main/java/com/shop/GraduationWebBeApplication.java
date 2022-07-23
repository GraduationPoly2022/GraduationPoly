package com.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraduationWebBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GraduationWebBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start...");
    }
}
