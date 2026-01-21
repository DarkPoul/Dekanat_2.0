package com.esvar.dekanat.app;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.esvar.dekanat")
@EnableVaadin("com.esvar.dekanat")
@Theme(value = "dekanat", variant = Lumo.LIGHT)
public class DekanatApplication {
    public static void main(String[] args) {
        SpringApplication.run(DekanatApplication.class, args);
    }
}
