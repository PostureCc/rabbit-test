package com.chan.controller;

import com.alibaba.fastjson.JSONObject;
import com.chan.service.rabbit.DelayProduction;
import com.chan.service.rabbit.DirectAckProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelayController {

    @Autowired
    private DelayProduction delayProduction;

    @Autowired
    private DirectAckProduction directAckProduction;

    @RequestMapping("/test1")
    public void delayProductionTest() {
        JSONObject params = new JSONObject();
        //这里的支付状态是未付款 在MQ中处理成功后把状态改为付款成功
        params.put("status", 1);
        params.put("expiration", 1000 * 6);
        delayProduction.delaySend(params);
    }

    @RequestMapping("/test2")
    public void ackTest() {
        JSONObject params = new JSONObject();
        params.put("key", "value");
        directAckProduction.send(params);
    }

}
