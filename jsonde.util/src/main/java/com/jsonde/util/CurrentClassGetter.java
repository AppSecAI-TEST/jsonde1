package com.jsonde.util;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class CurrentClassGetter extends SecurityManager {

    public Class getCallerClass(int depth) {
        return getClassContext()[depth + 1];
    }

}
