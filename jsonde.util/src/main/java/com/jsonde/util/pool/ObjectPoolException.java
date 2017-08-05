package com.jsonde.util.pool;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class ObjectPoolException extends Exception {

    public ObjectPoolException() {
    }

    public ObjectPoolException(String message) {
        super(message);
    }

    public ObjectPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectPoolException(Throwable cause) {
        super(cause);
    }

}
