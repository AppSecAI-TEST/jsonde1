package com.jsonde.samples.legacy;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
class A {

    void operation1() {
    }

}

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class SimpleApp1 {

    public static void main(String[] arguments) throws Exception {
        method1();
        method2();
        Logger.getAnonymousLogger();
        method3();
    }

    private static void method1() {
        method2();
        method2();
    }

    private static void method2() {
        new ArrayList<SimpleApp1>();
    }

    private static A method3() {
        return new A() {
            @Override
            public void operation1() {

            }
        };
    }

}
