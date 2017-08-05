package com.jsonde.samples.legacy;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
class Parent {

    public Parent(int parameter1, int parameter2) {

    }

}

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
class Child extends Parent {

    public Child(int parameter1, int parameter2) {
        super(parameter1, parameter2);
    }

}

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class Constructor {

    public static void main(String[] arguments) throws Exception {
        new Child(1, 2);
    }


}