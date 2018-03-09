package com.immoc.order.repository;

import com.immoc.order.OrderApplicationTests;
import com.immoc.order.entity.OrderMaster;
import com.immoc.order.enums.OrderStatusEnum;
import com.immoc.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerName("肖师弟");
        orderMaster.setBuyerPhone("18816899195");
        orderMaster.setBuyerOpenid("100100100");
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}