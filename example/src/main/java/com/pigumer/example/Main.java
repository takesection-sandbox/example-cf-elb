package com.pigumer.example;

import org.springframework.boot.SpringApplication;

public class Main {

    private static final String CLASS_NAME = "com.pigumer.example.controller.api.OpenAPI2SpringBoot";

    public static void main(String[] args) throws Exception {
        Class appClass = Class.forName(CLASS_NAME);
        SpringApplication.run(appClass);
    }
}
