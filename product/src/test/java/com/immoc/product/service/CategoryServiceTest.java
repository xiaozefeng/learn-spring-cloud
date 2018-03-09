package com.immoc.product.service;

import com.immoc.product.ProductApplicationTests;
import com.immoc.product.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findAllByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findAllByCategoryTypeIn(Arrays.asList(11, 22));
        assertTrue(list.size() > 0);
    }
}