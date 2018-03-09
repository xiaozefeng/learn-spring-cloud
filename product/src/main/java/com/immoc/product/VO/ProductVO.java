package com.immoc.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

/**
 * @author xiaozefeng
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
