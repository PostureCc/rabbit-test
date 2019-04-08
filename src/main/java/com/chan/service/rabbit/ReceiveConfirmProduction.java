//package com.chan.service.rabbit;
//
//import com.alibaba.fastjson.JSONObject;
//import com.chan.config.ReceiveConfirmConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class ReceiveConfirmProduction {
//
//    @Autowired
//    private ReceiveConfirmConfig receiveConfirmConfig;
//
//    public void sendMsg(String queueName, JSONObject msg) {
//        receiveConfirmConfig.sendMessage(queueName, msg.toJSONString());
//    }
//
//}
