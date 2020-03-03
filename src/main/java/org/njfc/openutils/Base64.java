package org.njfc.openutils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class Base64 {

    // 默认编码类型
    public static String UTF_8 = "UTF-8";

    static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    /**
     * Base64编码
     * @param param
     * @return
     */
    public static String encode(String param){
        if(StringUtils.isBlank(param)) return null;
        try {
            return encoder.encodeToString(param.getBytes(UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Base64解码
     * @param param
     * @return
     */
    public static String decode(String param){
        if(StringUtils.isBlank(param)) return null;
        try {
            return new String(decoder.decode(param), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
