package com.jsonde.samples;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class Application1 {

    public static void main(String... arguments) {
        method1();
    }

    public static void method1() {
        System.out.println("method1() " + Application1.class.getClassLoader());
        method2();
    }

    private static void method2() {
        System.out.println("method2()");
    }

}
