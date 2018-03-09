package com.immoc.product.service;

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
}
