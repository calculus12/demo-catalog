package com.example.democatalog;

import com.example.democatalog.config.PolarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DemoCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCatalogApplication.class, args);
    }

}
