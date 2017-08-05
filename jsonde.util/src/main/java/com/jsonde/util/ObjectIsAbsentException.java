package com.jsonde.util;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class ObjectIsAbsentException extends Exception {

    public ObjectIsAbsentException() {
    }

    public ObjectIsAbsentException(String message) {
        super(message);
    }

    public ObjectIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectIsAbsentException(Throwable cause) {
        super(cause);
    }

}
