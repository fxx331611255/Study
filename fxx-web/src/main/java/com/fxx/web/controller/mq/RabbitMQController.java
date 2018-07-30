package com.fxx.web.controller.mq;

import com.fxx.service.SendMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by fengxiaoxi on 2018/7/11
 * 消息队列  rabbitMQ
 */
@RestController
@Slf4j
@RequestMapping("rabbit")
public class RabbitMQController {
    @Resource
    private SendMQService sendMQService;

    @PostMapping("test")
    public void test() {
        for (int i = 0; i < 5; i++) {
            sendMQService.send();
        }
    }
}
