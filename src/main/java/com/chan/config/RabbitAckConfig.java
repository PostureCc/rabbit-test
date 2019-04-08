package com.chan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitAckConfig {

    public static final String TEST_ACK_QUEUE1 = "test_ack_queue1";
    public static final String TEST_ACK_EXCHANGE1 = "test_ack_exchange1";
    public static final String TEST_ACK_ROUTING_KEY1 = "test_ack_routing_key1";

    @Bean
    public Queue testAckQueue() {
        log.info("testAckQueue init...");
        Map<String, Object> params = new HashMap<>(5);
        params.put("x-dead-letter-exchange", TEST_ACK_EXCHANGE1);
        params.put("x-dead-letter-routing-key", TEST_ACK_ROUTING_KEY1);
        return new Queue(TEST_ACK_QUEUE1, true, false, false, params);
    }

    @Bean
    public DirectExchange testAckExchange() {
        log.info("testAckExchange init...");
        return new DirectExchange(TEST_ACK_EXCHANGE1);
    }

    @Bean
    public Binding testAckBinding() {
        log.info("testAckBinding init...");
        return BindingBuilder.bind(this.testAckQueue()).to(this.testAckExchange()).with(TEST_ACK_ROUTING_KEY1);
    }

}
