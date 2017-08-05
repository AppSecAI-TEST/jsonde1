package com.jsonde.samples.legacy;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
class JMSManager {

    public void createConnection() {
    }

    public void sendMessage(String message) {
    }

    public void close() {
    }

}

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class Issue8 {

    public static void main(String[] arguments) throws Exception {
        method1();
        method2();
    }

    private static void method1() throws Exception {
        JMSManager manager = new JMSManager();
        manager.createConnection();
        manager.sendMessage("hello, world");
        manager.close();
    }

    private static void method2() {
        try {
            JMSManager manager = new JMSManager();
            manager.createConnection();
            manager.sendMessage("hello, world");
            manager.close();
        } catch (Exception e) {
           
        }
    }

}
