package javaapplication31.uteis;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SwingWaypointGreen extends DefaultWaypoint {
    private final JLabel label;
    private final String text;

    public SwingWaypointGreen(String text, GeoPosition coord) {
        super(coord);
        this.text = text;
        ImageIcon icon = new ImageIcon(getClass().getResource("boatYellow30.png"));
        label = new JLabel(icon);
        label.setToolTipText("Barco amarelo");
        label.setVisible(true);
    }

    JLabel getLabel() {
        return label;
    }

    private class SwingWaypointMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(label, "You clicked on " + text);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
