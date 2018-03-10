package com.immoc.product.service;

import com.immoc.product.dto.CartDTO;
import com.immoc.product.entity.ProductInfo;

import java.util.List;

/**
 * @author xiaozefeng
 */
public interface ProductService {

    /**
     * 查询上架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);


    /**
     * 扣减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
