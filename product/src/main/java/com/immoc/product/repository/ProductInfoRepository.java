package com.immoc.product.repository;

import com.immoc.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xiaozefeng
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findAllByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
