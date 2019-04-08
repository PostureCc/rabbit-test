package com.chan.service.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.DelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DelayConsume {


    @RabbitListener(queues = {DelayConfig.TEST_ORDER_QUEUE_NAME1})
    public void orderConsume1(JSONObject params) {
        if (null != params && !params.isEmpty()) {
            log.info("orderConsume1 获取订单信息: {}", params);

            switch (params.getInteger("status")) {
                case 0:
                    log.info("订单尚未付款...");
                    break;
                case 1:
                    log.info("订单支付成功...");
                    break;
                case 2:
                    log.info("订单未支付 取消订单中...");
                    break;
                case 3:
                    log.info("订单退款中...");
                    break;
                default:
                    log.info("订单状态异常...");
                    break;
            }

        }
    }

}
