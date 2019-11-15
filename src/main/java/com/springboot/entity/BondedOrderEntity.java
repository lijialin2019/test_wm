package com.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 	保税备货订单
 *  @author ljl
 *  @date 2019-11-06 10:22:35
 */

@Data
@NoArgsConstructor
public class BondedOrderEntity implements Serializable {
    private static final long serialVersionUID = -8937313661531457791L;
    /**
     *  商户编号
     */
    private String merchId;
    /**
     *商户名称
     */
    private String merchName;
    /**
     *第三方商户代码
     */
    private String thirdPartyMerchCode;
    /**
     *第三方商户名称
     */
    private String thirdPartyMerchName;
    /**
     *商户订单编号
     */
    private String merchOrderId;
    /**
     *订购人证件类型
     */
    private String buyerIdType;
    /**
     *订购人证件号码
     */
    private String buyerIdCode;
    /**
     *订购人姓名
     */
    private String buyerName;
    /**
     *订购人电话
     */
    private String buyerTel;
    /**
     *支付人证件类型
     */
    private String payerIdType;
    /**
     *支付人证件号码
     */
    private String payerIdCode;
    /**
     *支付人姓名
     */
    private String payerName;
    /**
     *支付人电话
     */
    private String payerMob;
    /**
     *收件人证件类型
     */
    private String consigneeIdType;
    /**
     *收件人证件号码
     */
    private String consigneeIdCode;
    /**
     *收件人姓名
     */
    private String consigneeName;
    /**
     *收件人手机
     */
    private String consigneeMob;
    /**
     *收件人电话
     */
    private String consigneeTel;
    /**
     *收件人国家代码
     */
    private String consigneeCountryCode;
    /**
     *收件人省区划代码
     */
    private String consigneeProvinceCode;
    /**
     *收件人省名
     */
    private String consigneeProvince;
    /**
     *收件人市区划代码
     */
    private String consigneeCityCode;
    /**
     *收件人市名
     */
    private String consigneeCity;
    /**
     *收件人区县区划代码
     */
    private String consigneeDistrictCode;
    /**
     *收件人区县
     */
    private String consigneeDistrict;
    /**
     *收件人地址
     */
    private String consigneeAddress;
    /**
     *收件人邮政编码
     */
    private String consigneeZipCode;
    /**
     *支付企业海关登记号
     */
    private String payEntCusCode;
    /**
     *支付单号
     */
    private String payNo;
    /**
     *实际支付金额
     */
    private Double acturalPaid;
    /**
     *支付时间
     */
    private String payTime;
    /**
     *快递账号主体类型
     */
    private String exprAgreementType;
    /**
     *快递类型
     */
    private String exprType;
    /**
     *快递公司编号
     */
    private String exprCompId;
    /**
     *运输方式
     */
    private String transMode;
    /**
     *快递编号
     */
    private String exprNo;
    /**
     *大头笔
     */
    private String shortAddress;
    /**
     *集包地名称
     */
    private String packageCenterName;
    /**
     *订购人下单时间
     */
    private String buyerBillTime;
    /**
     *海关代码
     */
    private String cusCode;
    /**
     *保税区代码
     */
    private String bondedCode;
    /**
     *仓库代码
     */
    private String wareCode;
    /**
     *申报用物流费
     */
    private Double declExprFee;
    /**
     *申报用行邮税
     */
    private Double declPostTax;
    /**
     *商户字段
     */
    private String extraTag;
    /**
     *平台编码
     */
    private String platSn;
    /**
     *海关支付推送成功
     */
    private String cusPaymentSucceeded;
    /**
     *海关订单推送成功
     */
    private String cusOrderSucceeded;
    /**
     *支付成功
     */
    private String paySucceeded;
    /**
     *订购人支付人核验
     */
    private String buyerPayerCheck;
    /**
     *备注
     */
    private String remark;
    /**
     *商品详情
     */
    private List<OrderItemEntity> item;


}
