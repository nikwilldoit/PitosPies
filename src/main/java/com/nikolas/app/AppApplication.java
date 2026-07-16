package com.nikolas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        basePackages = "com.nikolas.app",
        excludeFilters = @ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern =  "com.nikolas.app.excluded.*"
        )
)
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
