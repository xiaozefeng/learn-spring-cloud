package com.immoc.order.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.immoc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiaozefeng
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    @Autowired
    private Gson gson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        List<ProductInfoOutput> productInfoOutputList = gson.fromJson(message, new TypeToken<List<ProductInfoOutput>>() {
        }.getType());
        log.info("receive productInfo ={} ", productInfoOutputList);
        productInfoOutputList.forEach(productInfoOutput -> {
            stringRedisTemplate.opsForValue()
                    .set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId())
                            , String.valueOf(productInfoOutput.getProductStock()));
        });


    }
}
