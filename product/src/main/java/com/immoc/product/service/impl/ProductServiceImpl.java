package com.immoc.product.service.impl;

import com.immoc.product.entity.ProductInfo;
import com.immoc.product.enums.ProductStatusEnum;
import com.immoc.product.repository.ProductInfoRepository;
import com.immoc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozefeng
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findAllByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
