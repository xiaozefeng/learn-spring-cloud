package com.immoc.product.service.impl;

import com.immoc.product.service.CategoryService;
import com.immoc.product.entity.ProductCategory;
import com.immoc.product.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozefeng
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findAllByCategoryTypeIn(categoryTypeList);
    }
}
