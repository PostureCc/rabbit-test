package com.chan.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: 配置默认的交换机模式
 * Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列。
 **/
@Slf4j
@Configuration
public class RabbitDirectConfig {

    public static final String TEST_QUEUE1 = "test_queue1";
    public static final String TEST_ROUTING_KEY1 = "test_routing_key1";
    public static final String TEST_EXCHANGE1 = "test_exchange1";


    @Bean
    DirectExchange directExchange() {
        log.info("directExchange init...");
        return new DirectExchange(TEST_EXCHANGE1);
    }

    @Bean
    Binding directExchangeBinding1() {
        log.info("directExchangeBinding1 init...");
        return BindingBuilder.bind(this.testQueue1()).to(this.directExchange()).with(TEST_ROUTING_KEY1);
    }

    @Bean
    Binding directExchangeBinding2() {
        log.info("directExchangeBinding2 init...");
        return BindingBuilder.bind(this.testQueue2()).to(this.directExchange()).with(TEST_ROUTING_KEY1);
    }

    @Bean
    public Queue testQueue1() {
        log.info("testQueue1 init...");
        return new Queue(TEST_QUEUE1);
    }


    public static final String TEST_QUEUE2 = "test_queue2";

    @Bean
    public Queue testQueue2() {
        log.info("testQueue2 init...");
        return new Queue(TEST_QUEUE2);
    }

}
