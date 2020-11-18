/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jxmapviewer.JXMapViewer;

/**
 *
 * @author jonat
 */
public class MapView{
    private JPanel jp = new JPanel();
    public void show(JXMapViewer mapViewer) {
        final JFrame frame = new JFrame(); 
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 100));
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Use o botão esquerdo do mouse para mover e o scroll para aplicar zoom"), BorderLayout.NORTH);
        frame.add(mapViewer);
        frame.add(jp, BorderLayout.WEST);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Marine Experience");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
