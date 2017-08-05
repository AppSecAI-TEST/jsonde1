package com.jsonde.api.function.echo;

import com.jsonde.api.function.FunctionRequest;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class EchoFunctionRequest extends FunctionRequest<EchoFunctionResponse> {

    private String message;

    public EchoFunctionRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
