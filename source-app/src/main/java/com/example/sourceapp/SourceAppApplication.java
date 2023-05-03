package com.example.sourceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
public class SourceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SourceAppApplication.class, args);
	}
	private int count=0;


	@Bean
	public Supplier<String> supplier() {
		return () -> {
			String message = "message "+count++;
			System.out.println("Sending value: " + message);
			return message;
		};
	}
}
