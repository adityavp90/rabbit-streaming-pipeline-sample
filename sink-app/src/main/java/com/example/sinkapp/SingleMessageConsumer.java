package com.example.sinkapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class SingleMessageConsumer {

    @Bean
    @Profile({"classicq", "streamq"})
    public Consumer<Message<String>> consumer() {
        return message -> {
            System.out.println("Received message with partition key: " + message.getHeaders().get("partition_key"));
        };
    }
}
