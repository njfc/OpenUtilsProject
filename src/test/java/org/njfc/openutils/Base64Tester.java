package org.njfc.openutils;

public class Base64Tester {
    public static void main(String[] args) {
        System.out.println(Base64.encode("Aladdin:openSesame"));
        System.out.println("QWxhZGRpbjpvcGVuU2VzYW1l".equals(Base64.encode("Aladdin:openSesame")));
    }
}
