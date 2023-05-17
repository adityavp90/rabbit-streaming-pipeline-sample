package com.example.sinkapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class BatchMessageConsumer {

    @Bean
    @Profile({"classicq-batch", "classicq-batch-partitioned"})
    public Consumer<Message<List<String>>> consumer() {
        return message -> {
            List<Map<String, Object>> headers = (List<Map<String, Object>>) message.getHeaders().get(AmqpInboundChannelAdapter.CONSOLIDATED_HEADERS);
            System.out.println("Consolidated batch headers size: "+ headers.size());
            headers.stream().map(stringObjectMap -> stringObjectMap.get("partition_key")).forEach(System.out::println);
        };
    }
}
