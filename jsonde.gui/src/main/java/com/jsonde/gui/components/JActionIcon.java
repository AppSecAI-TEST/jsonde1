package com.jsonde.gui.components;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class JActionIcon extends JLabel implements MouseListener {

    private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    private Action action;

    public JActionIcon(Action action) {

        super();

        Object smallIconObject = action.getValue(Action.SMALL_ICON);
        if (null != smallIconObject) {
            setIcon((Icon) smallIconObject);
        }

        this.action = action;

        setBorder(emptyBorder);
        addMouseListener(this);

    }

    public void mouseEntered() {
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void mouseExited() {
        setBorder(emptyBorder);
    }

    public void mousePressed() {
        setBorder(BorderFactory.createLoweredBevelBorder());
    }

    public void mouseReleased() {
        setBorder(emptyBorder);

        Component component = e.getComponent();
        if (component.contains(e.getPoint())) {
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "iconClicked");
            action.actionPerformed(event);
        }
    }

    public void mouseClicked() {
    }

}
