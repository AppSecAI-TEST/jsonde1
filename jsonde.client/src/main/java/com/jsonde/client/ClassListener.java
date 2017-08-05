package com.jsonde.client;

import com.jsonde.client.domain.Clazz;

import java.util.EventListener;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface ClassListener extends EventListener {

    void onRegisterClass(Clazz clazz);

}