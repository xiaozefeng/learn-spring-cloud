package com.immoc.order.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试mq发送
 *
 * @author xiaozefeng
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private AmqpTemplate amqpTemplate;


    @GetMapping("amqp/send")
    public void amqpSend() {
        amqpTemplate.convertAndSend("myQueue", "now >> " + new Date());
    }


}
