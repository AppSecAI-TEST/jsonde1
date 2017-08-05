package com.jsonde.gui.components.composite;

import javax.swing.*;

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
