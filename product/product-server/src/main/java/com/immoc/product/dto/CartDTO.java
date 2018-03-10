package com.immoc.product.dto;

import lombok.Data;

/**
 * @author xiaozefeng
 */
@Data
public class CartDTO {

    /**
     * 商品id
     *
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
