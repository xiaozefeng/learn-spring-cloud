package com.immoc.order.controller;

import com.immoc.product.client.ProductClient;
import com.immoc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaozefeng
 */
@RestController
@RefreshScope
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("listProductForOrder")
    public String listProductForOrder() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response => {}", productInfoList);
        return "ok";
    }

    @Value("${env}")
    private String env;

    @GetMapping("env/print")
    public String env(){
        return env;
    }

}
