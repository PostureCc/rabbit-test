package com.chan.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String address;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtual;

//    @Value("${spring.rabbitmq.publisher-confirms}")
//    private Boolean confirms;

    @Bean
    public CachingConnectionFactory getConnection() {
        log.info("getConnection init...");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(virtual);
//        connectionFactory.setSimplePublisherConfirms(confirms);
        return connectionFactory;
    }

    @Bean("rabbitAdmin")
    public AmqpAdmin rabbitAdmin() {
        log.info("rabbitAdmin init...");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(getConnection());
//        rabbitAdmin.getRabbitTemplate().setConfirmCallback(new MsgSendConfirmCallBack());
//        rabbitAdmin.getRabbitTemplate().setReturnCallback(new MsgSendReturnCallback());
        return rabbitAdmin;
//        return new RabbitAdmin(getConnection());
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        log.info("rabbitTemplate init...");
//        return new RabbitTemplate(getConnection());
//    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        log.info("rabbitTemplateByAck init...");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.getConnection());
        // 设置回调方法
        rabbitTemplate.setConfirmCallback(new TestConfirmCallback());
        // 失败后return回调
        rabbitTemplate.setReturnCallback(new TestReturnCallback());
        // return 回调需要设置,不然不会生效
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


}
