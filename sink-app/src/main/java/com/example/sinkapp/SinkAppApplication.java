package com.example.sinkapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinkAppApplication.class, args);
    }

    @Bean
	public Consumer<String> consumer() {
		return message -> {
			System.out.println("Received message " + message);
		};
	}
}
