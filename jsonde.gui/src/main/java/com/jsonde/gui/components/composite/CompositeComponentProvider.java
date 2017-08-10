package com.jsonde.gui.components.composite;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface CompositeComponentProvider {

    JComponent createCompositeComponent();

    String getTitle();

    Icon getSmallIcon();

    Icon getLargeIcon();

}
