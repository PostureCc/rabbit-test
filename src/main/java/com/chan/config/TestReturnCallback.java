package com.chan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("returnedMessage init...");
        log.info("message:{} " +
                "\nreplyCode:{} " +
                "\nreplyText:{}" +
                "\nexchange:{}" +
                "\nroutingKey:{}"
                , new String(message.getBody()), replyCode, replyText, exchange, routingKey);
    }
}
