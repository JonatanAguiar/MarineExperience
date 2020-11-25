package marineExperience.uteis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.geom.AffineTransform;

public class SwingWaypoint extends DefaultWaypoint {

    private final JLabel label;
    private final String msi;

    public SwingWaypoint(String msi, Double angulo, String canal, GeoPosition coord) {
        super(coord);
        this.msi = msi;
        ImageIcon icon;
        if (canal.equals("A")) { //adiciona o barco correto, dependendo do canal
            icon = new ImageIcon(getClass().getResource("boatYellow30.png"));
        } else {
            icon = new ImageIcon(getClass().getResource("boatGreen30.png"));
        }
        this.label = new JLabel(icon) {
            @Override //metodo que adiciona o angulo do barco
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldshape = g2.getClip();
                double x = getWidth() / 2.0;
                double y = getHeight() / 2.0;
                aT.rotate(Math.toRadians(angulo), x, y);
                g2.setTransform(aT);
                g2.setClip(oldshape);
                super.paintComponent(g);
            }
        };
        //adiciona o msi na tooltip
        label.setToolTipText(this.msi);
        label.setVisible(true);
    }

    JLabel getLabel() {
        return label;
    }
}
