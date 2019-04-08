package com.chan;

import com.alibaba.fastjson.JSONObject;
import com.chan.config.RabbitDirectConfig;
import com.chan.service.rabbit.DelayProduction;
import com.chan.service.rabbit.DirectProduction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTestApplicationTests {

    @Autowired
    private DirectProduction directProduction;

    @Autowired
    private DelayProduction delayProduction;

    @Test
    public void delayProductionTest() {
        JSONObject params = new JSONObject();
        params.put("status", 1);
        params.put("expiration", 1000 * 5);
        delayProduction.delaySend(params);
    }


    @Test
    public void directProductionTest() {
        for (int i = 1; i <= 10; i++) {
            JSONObject info = new JSONObject();
            info.put("key" + i, "value" + i);
            directProduction.sendMsg(RabbitDirectConfig.TEST_QUEUE1, info);
        }
    }


    //    @Autowired
//    private ReceiveConfirmProduction receiveConfirmProduction;
//
//    @Test
//    public void receiveConfirmTest() {
//        for (int i = 1; i <= 10; i++) {
//            JSONObject info = new JSONObject();
//            info.put("key" + i, "value" + i);
//            receiveConfirmProduction.sendMsg(ReceiveConfirmConfig.RECEIVE_CONFIRM_QUEUE, info);
//        }
//
//    }


    @Test
    public void contextLoads() {
    }

}
