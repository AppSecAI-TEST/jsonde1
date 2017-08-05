package com.jsonde.client.network;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class NetworkClientException extends Exception {

    public NetworkClientException() {
    }

    public NetworkClientException(String message) {
        super(message);
    }

    public NetworkClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkClientException(Throwable cause) {
        super(cause);
    }

}
