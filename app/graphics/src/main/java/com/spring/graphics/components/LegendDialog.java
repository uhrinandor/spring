package com.spring.graphics.components;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class LegendDialog extends JDialog{
    public LegendDialog(){
        setTitle("Legend");
        setLayout(new GridLayout());

        ImageIcon legend = new ImageIcon(LegendDialog.class.getResource("/legend/legend.png"));
        Image scaled = legend.getImage().getScaledInstance(1190, 360, Image.SCALE_SMOOTH);
        this.add(new JLabel(new ImageIcon(scaled)));

        pack();
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }
}
