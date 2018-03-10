package com.immoc.order.dto;

import lombok.Data;

/**
 * @author xiaozefeng
 */
@Data
public class CartDTO {
    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }


    public CartDTO() {
    }
}
