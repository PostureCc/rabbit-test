package com.chan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 确认后的回调
 */
@Slf4j
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息确认成功cause" + cause);
        } else {
            //处理丢失的消息
            log.info("消息确认失败:" + correlationData.getId() + "#cause" + cause);
        }
    }

}
