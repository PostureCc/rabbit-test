package com.chan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("confirm init...");
        log.info("correlationData-->{}", correlationData);
        if (ack) {
            log.info("confirm msg send success...");
        } else {
            log.info("confirm msg send failed... \n{}", cause);
        }
    }

}
