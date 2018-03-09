package com.immoc.product.controller;

import com.immoc.product.VO.ProductInfoVO;
import com.immoc.product.VO.ProductVO;
import com.immoc.product.VO.ResultVO;
import com.immoc.product.entity.ProductCategory;
import com.immoc.product.entity.ProductInfo;
import com.immoc.product.service.CategoryService;
import com.immoc.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaozefeng
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     *
     */
    @GetMapping("/list")
    public ResultVO list() {

        List<ProductInfo> productInfoList = productService.findUpAll();
        // 获取categoryTypeList
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findAllByCategoryTypeIn(categoryTypeList);

        // 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        categoryList.forEach(category -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            productInfoList.stream()
                    .filter(p -> p.getCategoryType().equals(category.getCategoryType()))
                    .forEach(productInfo -> {
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(productInfo, productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    });
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        });
        return ResultVO.ok(productVOList);
    }
}
