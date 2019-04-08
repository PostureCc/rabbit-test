package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.RabbitAckConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DirectAckConsume {

    @RabbitListener(queues = {RabbitAckConfig.TEST_ACK_QUEUE1})
    public void DirectAckListener1(JSONObject params, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("DirectAckListener1-->{}", params);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("e:{}", e);
            channel.basicReject(deliveryTag, false);
        }
    }

}
