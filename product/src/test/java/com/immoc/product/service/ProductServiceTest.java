package com.immoc.product.service;

import com.immoc.product.ProductApplicationTests;
import com.immoc.product.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        assertTrue(list.size() > 0);
    }
}