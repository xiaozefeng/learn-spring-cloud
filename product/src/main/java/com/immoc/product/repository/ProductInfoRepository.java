package com.immoc.product.repository;

import com.immoc.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaozefeng
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
}
