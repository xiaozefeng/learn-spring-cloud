package com.immoc.product.repository;

import com.immoc.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Test
    public void findAllByCategoryTypeIn() {
        List<ProductCategory> result = productCategoryRepository.findAllByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(result.size() >0);

    }
}