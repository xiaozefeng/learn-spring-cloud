package com.immoc.product.repository;

import com.immoc.product.entity.ProductCategory;
import com.immoc.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaozefeng
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList);
}
