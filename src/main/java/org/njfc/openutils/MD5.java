package org.njfc.openutils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

/**
 * MD5签名处理核心文件
 */
public class MD5 {

    // 默认编码类型
    public static String charset = "UTF-8";

    /**
     * 内容转换为字节数组
     *
     * @param content 内容
     * @param charset 编码
     * @return byte[]
     */
    private static byte[] getBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出错,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 签名字节数组
     *
     * @param bytes 要签名的字节数组
     *
     * @return 签名结果
     */
    public static String sign(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    /**
     * 签名字符串
     *
     * @param text 要签名的字符串
     *
     * @return 签名结果
     */
    public static String sign(String text) {
        return DigestUtils.md5Hex(getBytes(text, charset));
    }

    /**
     * 签名字符串
     *
     * @param text 要签名的字符串
     * @param key 密钥
     *
     * @return 签名结果
     */
    public static String sign(String text, String key) {
        return sign(text + key);
    }

    /**
     * 签名字符串
     *
     * @param text 要签名的字符串
     * @param key 密钥
     * @param charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String charset) {
        text = text + key;
        return DigestUtils.md5Hex(getBytes(text, charset));
    }

    /**
     * 使用 key 签名map数据
     *
     * @param datas map数据
     *
     * @return
     */
    public static String signMap(Map<String, String> datas, String key) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>(datas);

        StringBuilder strBuilder = new StringBuilder();

        Iterator<Map.Entry<String, String>> it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entrySet = it.next();
            if(entrySet.getKey().startsWith("_")){
                continue;
            }
            strBuilder.append(entrySet.getKey()).append('=').append(entrySet.getValue()).append('&');
        }

        String signString = strBuilder.substring(0, strBuilder.length() - 1);

        return sign(signString, key);
    }

    /**
     * 签名字符串验签
     *
     * @param text 要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String charset) {
        return DigestUtils.md5Hex(getBytes(text + (key == null?"":key), charset)).equals(sign);
    }

    /**
     * 签名字符串验签
     *
     * @param text 要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     *
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key) {
        return verify(text, sign, key, charset);
    }

    /**
     * MD5签名
     *
     * @param data 数据
     * @return
     */
    public static String md5(String data) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(data.getBytes("UTF-8"));
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){i += 256;}
                if (i < 16){buf.append("0");}
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
