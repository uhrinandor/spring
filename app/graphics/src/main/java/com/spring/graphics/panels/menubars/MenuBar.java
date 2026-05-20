package com.spring.graphics.panels.menubars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MenuBar extends JPanel{
    private JPanel current;

    public MenuBar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 90));
        setBackground(new Color(0xF5F5DC));
    }

    public void change(JPanel newPanel){
        removeAll();
        current = newPanel;
        newPanel.setOpaque(false);
        add(current, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
