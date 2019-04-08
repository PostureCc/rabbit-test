package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.RabbitDirectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectConsume {

    @RabbitListener(queues = RabbitDirectConfig.TEST_QUEUE1)
    public void RabbitListener1(JSONObject msg) {
        log.info("DirectConsume Consume1：" + msg);
    }

    @RabbitListener(queues = RabbitDirectConfig.TEST_QUEUE1)
    public void RabbitListener2(JSONObject msg) {
        log.info("DirectConsume Consume2：" + msg);
    }

}
