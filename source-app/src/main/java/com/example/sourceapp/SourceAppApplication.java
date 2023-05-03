package com.example.sourceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@SpringBootApplication
public class SourceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SourceAppApplication.class, args);
	}
	private int count=0;

	@Bean
	public Supplier<Message<String>> supplier() {
		return () -> {
			int newCount = count++;
			String messageString = "message " + newCount;
			Message<String> message = MessageBuilder.withPayload(messageString)
				.setHeader("partition_key",newCount).build();
			System.out.println("Sending payload: " + message.getPayload());
			return message;
		};
	}
}
