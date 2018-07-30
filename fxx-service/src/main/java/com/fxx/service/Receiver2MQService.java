package com.fxx.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * created by fengxiaoxi on 2018/7/11
 */
@Service
@RabbitListener(queues = "rec")
public class Receiver2MQService {

    @RabbitHandler
    public void receiver(String hello) {
        System.out.println("Receiver2, " + hello);
    }
}
