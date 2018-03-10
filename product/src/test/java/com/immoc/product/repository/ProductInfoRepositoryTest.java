package com.immoc.product.repository;

import com.immoc.product.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findAllByProductStatusTest() {
        List<ProductInfo> results = productInfoRepository.findAllByProductStatus(0);
        assertTrue(results.size() == 2);
    }

    @Test
    public void findByProductIdIn() {
        List<ProductInfo> productList = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022", "157875227953464068"));
        assertTrue(productList.size() > 0);
    }
}