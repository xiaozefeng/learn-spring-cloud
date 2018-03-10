package com.immoc.product.service;

import com.immoc.product.entity.ProductCategory;

import java.util.List;

/**
 * @author xiaozefeng
 */
public interface CategoryService {
    /**
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList);
}
