package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.DelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DelayProduction {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void delaySend(JSONObject params) {
        rabbitTemplate.convertAndSend(DelayConfig.TEST_ORDER_DELAY_EXCHANGE1,
                DelayConfig.TEST_ORDER_DELAY_ROUTING_KEY1, params,
                message -> {
                    //TODO 延时处理
                    message.getMessageProperties().setExpiration(params.getString("expiration"));
                    return message;
                });
        log.info("delaySend 订单处理中...");
    }


}
