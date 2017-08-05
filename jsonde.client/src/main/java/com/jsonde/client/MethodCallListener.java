package com.jsonde.client;

import com.jsonde.client.domain.MethodCall;

import java.util.EventListener;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface MethodCallListener extends EventListener {

    void onMethodCall(MethodCall methodCall);

}
