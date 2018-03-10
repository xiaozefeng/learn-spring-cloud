package com.immoc.order.client;

import com.immoc.order.dto.CartDTO;
import com.immoc.order.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xiaozefeng
 */
@FeignClient(name = "product")
@Component
public interface ProductClient {

    /**
     * 商品列表
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣减库存
     * @param cartDTOList
     */
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
