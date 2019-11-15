package com.springboot.ApiLogisticsController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.springboot.Result;
import com.springboot.entity.BondedOrderDto;
import com.springboot.entity.BondedOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @program: hxyFrame
 * @description: 海关接口
 * @author: LJL
 * @create: 2019-11-06 10:55
 **/
@Controller
@RequestMapping(value = "bondedOrder")
public class BondedOrderController {

    /**
     * 请求路径
     */
    private String requestUrl = "http://wstest.ds-bay.com/al/bds/order";

    private String secretKey = "VXVJRlVq3VZghjJ0FLYDfyGhwm1JcVlHkENM";

    private String merchId = "mhbs996643550862512128";
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @Description:
     * @Param: @param bondedOrder
     * @return:
     * @Author: LJL
     * @Date: 2019/11/6
     */
    @PostMapping(value = "order")
    @ResponseBody
    public Result order(@RequestBody String data) {
        BondedOrderDto dto = new BondedOrderDto();
        try {
            ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
            LocalDateTime localDateTime = LocalDateTime.now();
            long timeStamp = localDateTime.toEpochSecond(zoneOffset);
            String sign = secretKey + "data" + data + "merchId" + merchId + "timestamp" + timeStamp;

            String name=java.net.URLEncoder.encode(sign, "UTF-8");
            String name2 =java.net.URLEncoder.encode(name,"UTF-8");
            String name3 =java.net.URLDecoder.decode(name2, "UTF-8");
            String name4 = URLDecoder.decode(name3, "UTF-8");

            String md5Sign = encrypt(name4);

            testlogin(sign,md5Sign);

           // String md5Sign = BondedOrderController.MD5(name4);

            dto.setData(data);
            dto.setTimestamp(timeStamp);
            dto.setSign(md5Sign);
            dto.setMerchId(merchId);

           String string = objectMapper.writeValueAsString(dto);
            String rep = string.replace("\\\\", "???");
            String replace = rep.replace("???", "");

            String body = Unirest.post(requestUrl)
                    .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
                    .body(replace)
                    .asString().getBody();

            System.out.println(body);

            JSONObject json = JSONObject.parseObject(body);
            return Result.ok(json.toJavaObject(Map.class));
        } catch (UnirestException | JsonProcessingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }




    public void testMd5() {
        System.out.println("======"+encrypt("1234567"));
    }

    public static void testlogin(String str1,String str2) {
       /* String password = encrypt(str);*/
        if(encrypt(str1).equals(str2)) {
            System.out.println("签名正确");
        } else {
            System.out.println("签名错误");
        }
    }

    private static String encrypt(String password) {
        String passwordMd5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes("utf-8"));
            passwordMd5 = toHex(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordMd5;
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }








    /**
     * 对字符串进行MD5加密(小写+字母)
     * @param str 要进行加密的字符串
     * @return 返回MD5加密后的字符串
     */
    public static String getMD5(String str) {
        String md5Str = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes("UTF-8"));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            md5Str = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    public static String MD5(String string) {

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();

    }
}
