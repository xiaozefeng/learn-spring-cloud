package com.immoc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaozefeng
 */
@RestController
@RequestMapping("hystrix")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "fallbackMethod")
    @HystrixCommand
    @GetMapping("/getProductInfo")
    public String getProductInfo(){
        ResponseEntity<String> result = restTemplate.getForEntity("http://127.0.0.1:8080/product/list",
                String.class);
        return result.getBody();
    }

    public String fallbackMethod(){
        return "太拥挤了，请稍后再试";
    }

    public String defaultFallbackMethod(){
        return "默认提示: 太拥挤了，请稍后再试";
    }
}
