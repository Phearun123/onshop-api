package com.kosign.onshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.kosign.onshopapi.properties")
public class Onshop {

    public static void main(String[] args) {
        SpringApplication.run(Onshop.class, args);
    }

}
