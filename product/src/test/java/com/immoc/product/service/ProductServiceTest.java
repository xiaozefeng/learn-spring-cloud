package com.immoc.product.service;

import com.immoc.product.dto.CartDTO;
import com.immoc.product.ProductApplicationTests;
import com.immoc.product.entity.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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

    @Test
    public void findList() {
        List<ProductInfo> list = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
        assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock(){
        productService.decreaseStock(Arrays.asList(new CartDTO("157875196366160022", 2)));
    }
}