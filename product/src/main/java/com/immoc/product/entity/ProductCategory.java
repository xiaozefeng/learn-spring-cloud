package com.immoc.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author xiaozefeng
 */
@Setter
@Getter
@Entity
public class ProductCategory {

    /**
     * 主键自增
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}
