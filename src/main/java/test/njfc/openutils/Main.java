package test.njfc.openutils;

import org.njfc.openutils.MD5;

public class Main {

    public static void main(String[] args) {
        System.out.println(MD5.md5("123456"));
        System.out.println(MD5.sign("123456"));
        System.out.println(MD5.sign("123456", MD5.md5("secret")));
    }

}
