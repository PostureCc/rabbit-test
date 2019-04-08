//package com.chan.service.rabbit;
//
//import com.chan.config.RabbitConfig;
//import com.chan.config.ReceiveConfirmConfig;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class ReceiveConfirmConsume {
//
//    @Autowired
//    private RabbitConfig rabbitConfig;
//
//    @Autowired
//    private ReceiveConfirmConfig receiveConfirmConfig;
//
//    @RabbitListener(queues = ReceiveConfirmConfig.RECEIVE_CONFIRM_QUEUE)
//    public SimpleMessageListenerContainer getMsg(/*Message message, Channel channel*/) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConfig.getConnection());
//        container.setQueues(receiveConfirmConfig.receiveConfirmQueue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) {
//
//                byte[] body = message.getBody();
//                try {
//                    //业务逻辑
//                    log.info("消费 receive msg : " + new String(body));
//                    // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
//                    //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //手动确认确认消息成功消费
//                } catch (Exception e) {
//                    log.info("消费失败: " + new String(body));
//                    log.info("e:{}", e);
//                    // ack返回false，并重新回到队列，api里面解释得很清楚
//                    try {
//                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//                    } catch (Exception e1) {
//                        log.info("e1:{}", e1);
//                    }
//                }
//
//            }
//        });
//        return container;
//    }
//
//}
