package com.jsonde.gui.action;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public abstract class ListSelectionListenerAction
        extends AbstractAction implements
        ListSelectionListener {

    protected ListSelectionListenerAction() {
    }

    protected ListSelectionListenerAction(String name) {
        super(name);
    }

    protected ListSelectionListenerAction(String name, Icon icon) {
        super(name, icon);
    }

    protected int selectedId = -1;

    public void valueChanged(ListSelectionEvent e) {
        findSelection(e);
    }

    private void findSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            ListSelectionModel mod = (ListSelectionModel) e.getSource();
            int newSelectedId = getSelectedId(mod, e);
            if (selectedId != newSelectedId) {

                boolean wasEnabled = isEnabled();

                selectedId = newSelectedId;

                if (wasEnabled != isEnabled()) {
                    firePropertyChange("enabled", wasEnabled, isEnabled());
                }

            }
        }
    }

    private int getSelectedId(ListSelectionModel mod, ListSelectionEvent e) {
        int result = -1;
        int a = e.getFirstIndex();
        int b = e.getLastIndex();
        for (int i = e.getFirstIndex(); i <= b; i++) {
            if (mod.isSelectedIndex(i)) {
                result = i;
                break;
            }
            a = e.getFirstIndex();
            b = e.getLastIndex();
        }
        return result;
    }

    @Override
    public boolean isEnabled() {
        return -1 != selectedId;
    }

}
