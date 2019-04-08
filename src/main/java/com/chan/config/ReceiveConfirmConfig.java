//package com.chan.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.UUID;
//
//@Slf4j
//@Configuration
//public class ReceiveConfirmConfig {
//
//    public static final String RECEIVE_CONFIRM_QUEUE = "receive_confirm_queue";
//
//    @Autowired
//    private RabbitAdmin rabbitAdmin;
//
//    @Bean
//    public Queue receiveConfirmQueue() {
//        return new Queue(RECEIVE_CONFIRM_QUEUE, true, false, false, null);
//    }
//
//
//    /**
//     * 方式一：动态声明exchange和queue它们的绑定关系  rabbitAdmin
//     *
//     * @param exchangeName
//     * @param queueName
//     */
//    protected void declareBinding(String exchangeName, String queueName) {
//        if (rabbitAdmin.getQueueProperties(queueName) == null) {
//            /*  queue 队列声明
//            durable=true,交换机持久化,rabbitmq服务重启交换机依然存在,保证不丢失; durable=false,相反
//            auto-delete=true:无消费者时，队列自动删除; auto-delete=false：无消费者时，队列不会自动删除
//            排他性，exclusive=true:首次申明的connection连接下可见; exclusive=false：所有connection连接下*/
//            Queue queue = new Queue(queueName, true, false, false, null);
//            rabbitAdmin.declareQueue(queue);
//            TopicExchange directExchange = new TopicExchange(exchangeName);
//            rabbitAdmin.declareExchange(directExchange);//声明exchange
//            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(queueName);    //将queue绑定到exchange
//            rabbitAdmin.declareBinding(binding);      //声明绑定关系
//            rabbitAdmin.getRabbitTemplate().setMandatory(true);
//            rabbitAdmin.getRabbitTemplate().setConfirmCallback(new MsgSendConfirmCallBack());//消息确认
//            rabbitAdmin.getRabbitTemplate().setReturnCallback(new MsgSendReturnCallback());//确认后回调
//            log.debug("queueName is null...");
//        } else {
//            rabbitAdmin.getRabbitTemplate().setQueue(queueName);
//            rabbitAdmin.getRabbitTemplate().setExchange(queueName);
//            rabbitAdmin.getRabbitTemplate().setRoutingKey(queueName);
//            log.debug("queueName not is null...");
//        }
//    }
//
//    /**
//     * 发送消息
//     *
//     * @param queueName
//     * @param message
//     * @throws Exception
//     */
//    public void sendMessage(String queueName, String message) {
//        declareBinding(queueName, queueName);
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        rabbitAdmin.getRabbitTemplate().convertAndSend(queueName, queueName, message, correlationId);
//        log.debug("[rabbitmq-sendMessage]queueName:{} ,uuid:{},msg:{}", queueName, correlationId.getId(), message);
//    }
//
//}
