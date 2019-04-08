package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.RabbitAckConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectAckProduction {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(JSONObject params) {
        log.info("send init...");
        rabbitTemplate.convertAndSend(RabbitAckConfig.TEST_ACK_EXCHANGE1, RabbitAckConfig.TEST_ACK_ROUTING_KEY1, params
                , message -> {
                    message.getMessageProperties().setExpiration(1000 * 6 + "");
                    return message;
                });
    }

}
