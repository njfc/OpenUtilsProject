package org.njfc.openutils;

public class Base64Tester {
    public static void main(String[] args) {
        String str = "Aladdin:openSesame";
        String preResult = "QWxhZGRpbjpvcGVuU2VzYW1l";
        System.out.println(Base64.encode(str));
        System.out.println(preResult.equals(Base64.encode("Aladdin:openSesame")));
        System.out.println(Base64.decode("QWxhZGRpbjpvcGVuU2VzYW1l"));
        System.out.println(Boolean.valueOf("true"));
        System.out.println(Boolean.valueOf("false"));
    }
}
