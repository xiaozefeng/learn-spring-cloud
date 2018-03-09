package com.immoc.product.repository;

import com.immoc.product.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertTrue(results.size()==2);
    }
}