package com.immoc.order.mq;

import com.immoc.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiaozefeng
 */
@Component
public class MqSenderTest extends OrderApplicationTests{
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }
}
