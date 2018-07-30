package com.fxx.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * created by fengxiaoxi on 2018/7/11
 */
@Service
public class SendMQService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello,at " + new Date();
        System.out.println("Sender1 : " + sendMsg);
        rabbitTemplate.convertAndSend("rec", sendMsg);
    }

}
