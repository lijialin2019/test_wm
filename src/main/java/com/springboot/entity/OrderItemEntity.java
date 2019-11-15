package com.springboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @program: hxyFrame
 * @description: 商品详情
 * @author: LJL
 * @create: 2019-11-06 10:29
 **/
@Data
@NoArgsConstructor
public class OrderItemEntity implements Serializable {
    private static final long serialVersionUID = 1874670963908449371L;

    /**
     *  库存量
     */
    private String sku;
    /**
     *  销售单价
     */
    private Double sellUnitPrice;
    /**
     *  销售数量
     */
    private Integer sellQty;
}
