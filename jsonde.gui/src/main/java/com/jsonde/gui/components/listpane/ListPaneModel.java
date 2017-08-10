package com.jsonde.gui.components.listpane;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public interface ListPaneModel {

    int getSize();

    String getLabelAt(int index);

    Icon getIconAt(int index);

    Action getActionAt(int index);

}
