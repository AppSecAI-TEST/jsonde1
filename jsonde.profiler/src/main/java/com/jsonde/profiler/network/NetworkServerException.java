package com.jsonde.profiler.network;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class NetworkServerException extends Exception {

    public NetworkServerException() {
    }

    public NetworkServerException(String message) {
        super(message);
    }

    public NetworkServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkServerException(Throwable cause) {
        super(cause);
    }
}
