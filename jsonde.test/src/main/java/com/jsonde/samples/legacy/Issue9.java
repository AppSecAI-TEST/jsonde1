package com.jsonde.samples.legacy;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class Issue9 {

    public Issue9() throws Exception {
        this("argument");
    }

    public Issue9(String argument) throws Exception {
        throw new Exception("test exception");
    }

    public Issue9(int i) {
        this(i, i + 1);
    }

    public Issue9(int i, int j) {
        this(i, j, i + j);
    }

    public Issue9(int i, int j, int k) {
    }

    public static void main(String[] arguments) throws Exception {
        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        
                    }
                }
        );

        thread.start();
        thread.join();

    }

}
