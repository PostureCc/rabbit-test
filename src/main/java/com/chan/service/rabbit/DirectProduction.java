package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectProduction {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String queueName, JSONObject msg) {
        rabbitTemplate.convertAndSend(queueName, msg);
        log.info("DirectProduction Send Success");
    }

}
