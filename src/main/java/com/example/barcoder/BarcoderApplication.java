package com.example.barcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BarcoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcoderApplication.class, args);
    }

}
