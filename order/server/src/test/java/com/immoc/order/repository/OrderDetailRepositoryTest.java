package com.immoc.order.repository;

import com.immoc.order.OrderApplicationTests;
import com.immoc.order.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1235647");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductQuantity(2);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);

    }

}