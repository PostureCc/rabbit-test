package com.chan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 失败后的回调`1
 */
@Slf4j
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        log.info("确认后回调return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:"
                + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }


}
