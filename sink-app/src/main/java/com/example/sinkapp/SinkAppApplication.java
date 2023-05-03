package com.example.sinkapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinkAppApplication.class, args);
    }

    @Bean
	public Consumer<Message<String>> consumer() {
		return message -> {
			System.out.println("Received message with partition key: " + message.getHeaders().get("partition_key"));
		};
	}
}
