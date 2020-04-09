package org.njfc.openutils;

public class MD5Tester {

    public static void main(String[] args) {
        System.out.println(MD5.md5("123456"));
        System.out.println(MD5.sign("123456"));
        System.out.println(MD5.sign("123456", MD5.md5("secret")));
        System.out.println(MD5.md5("accountpwd20181109231202"));
        System.out.println(MD5.sign("accountpwd20181109231202"));
    }

}
