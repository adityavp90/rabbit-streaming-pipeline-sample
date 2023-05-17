package com.example.sinkapp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class BatchMessageManualAckConsumer {

    @Bean
    @Profile({"classicq-batch-partitioned-manack"})
    public Consumer<Message<List<String>>> consumer() {
        return message -> {
            Channel channel = message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
            Long deliveryTag = message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
            System.out.println("Batch Delivery Tag = " + deliveryTag);
            try {
                List<Map<String, Object>> headers = (List<Map<String, Object>>) message.getHeaders().get(AmqpInboundChannelAdapter.CONSOLIDATED_HEADERS);
                System.out.println("Consolidated batch headers size: "+ headers.size());
                System.out.println("Header Partition Keys");
                headers.stream().map(stringObjectMap -> stringObjectMap.get("partition_key")).forEach(System.out::println);
                System.out.println("Header Delivery Tag Keys");
                headers.stream().map(stringObjectMap -> stringObjectMap.get(AmqpHeaders.DELIVERY_TAG)).forEach(System.out::println);
                channel.basicAck(deliveryTag, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
