package com.jsonde.instrumentation;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class ByteCodeTransformException extends Exception {

    public ByteCodeTransformException() {
    }

    public ByteCodeTransformException(String message) {
        super(message);
    }

    public ByteCodeTransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public ByteCodeTransformException(Throwable cause) {
        super(cause);
    }

}
