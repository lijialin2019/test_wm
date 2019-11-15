package com.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class DemoWmApplicationTests {

    @Test
    void contextLoads() {
        try {
            String name=java.net.URLEncoder.encode("测试", "UTF-8");
            System.out.println(name);
            name=java.net.URLEncoder.encode(name,"UTF-8");
            System.out.println(name);
            name=java.net.URLDecoder.decode(name, "UTF-8");
            System.out.println(name);
            System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMd5() {
        System.out.println("======"+encrypt("1234567"));
    }

    @Test
    public void testlogin() {
        String password = encrypt("123456adfaf");
        if(encrypt("123456adfaf").equals(password)) {
            System.out.println("密码正确");
        } else {
            System.out.println("密码错误");
        }
    }

    private String encrypt(String password) {
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

}
