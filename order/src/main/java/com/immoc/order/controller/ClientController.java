package com.immoc.order.controller;

import com.immoc.order.client.ProductClient;
import com.immoc.order.entity.ProductInfo;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaozefeng
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("listProductForOrder")
    public String listProductForOrder() {
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response => {}", productInfoList);
        return "ok";
    }

}
