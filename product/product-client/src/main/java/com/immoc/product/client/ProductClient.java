package com.immoc.product.client;

import com.immoc.product.common.DecreaseStockInput;
import com.immoc.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xiaozefeng
 */
@FeignClient("product")
public interface ProductClient {

    /**
     * 根据订单查询商品信息
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 减库存
     * @param decreaseStockInputList
     */
    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
