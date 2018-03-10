package com.immoc.product.service.impl;

import com.immoc.product.dto.CartDTO;
import com.immoc.product.entity.ProductInfo;
import com.immoc.product.enums.ProductStatusEnum;
import com.immoc.product.enums.ResultEnum;
import com.immoc.product.exception.ProductException;
import com.immoc.product.repository.ProductInfoRepository;
import com.immoc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NO_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            // 判断库存
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        });
    }

}
