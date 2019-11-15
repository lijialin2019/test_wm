package com.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @package: com.springboot.entity
 * @description:
 * @author: LiJiaLin
 * @date: Created in 2019/11/6 22:09
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified:
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BondedOrderDto implements Serializable {
    private static final long serialVersionUID = -7639098598310320739L;

    /**
     * 业务数据
     */
    private String data;


    /**
     * 签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     *  商户编号
     */
    private String merchId;

}
