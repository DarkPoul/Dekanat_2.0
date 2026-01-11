package com.esvar.dekanat.app;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.esvar.dekanat")
@EnableVaadin("com.esvar.dekanat")
public class DekanatApplication {
    public static void main(String[] args) {
        SpringApplication.run(DekanatApplication.class, args);
    }
}
