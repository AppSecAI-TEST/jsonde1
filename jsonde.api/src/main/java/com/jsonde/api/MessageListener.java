package com.jsonde.api;

import java.util.EventListener;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface MessageListener extends EventListener {

    void onMessage(Message message);

}
