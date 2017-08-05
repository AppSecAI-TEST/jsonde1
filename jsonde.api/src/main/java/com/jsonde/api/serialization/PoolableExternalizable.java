package com.jsonde.api.serialization;

import java.io.Externalizable;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface PoolableExternalizable extends Externalizable {

    String getFactoryClassName();

}
