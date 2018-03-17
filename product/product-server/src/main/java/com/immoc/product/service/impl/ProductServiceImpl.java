package com.immoc.product.service.impl;

import com.google.gson.Gson;
import com.immoc.product.common.ProductInfoOutput;
import com.immoc.product.dto.CartDTO;
import com.immoc.product.entity.ProductInfo;
import com.immoc.product.enums.ProductStatusEnum;
import com.immoc.product.enums.ResultEnum;
import com.immoc.product.exception.ProductException;
import com.immoc.product.repository.ProductInfoRepository;
import com.immoc.product.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaozefeng
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Gson gson;


    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findAllByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);

        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
        // 发送mq消息
        amqpTemplate.convertAndSend("productInfo", gson.toJson(productInfoList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
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

            productInfoList.add(productInfo);
        });

        return productInfoList;
    }

}
