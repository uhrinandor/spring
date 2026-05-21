package com.spring.graphics.panels.map;


import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class Pin extends JPanel {
    protected static final int SIZE = 12;

    public Pin() {
        setSize(SIZE, SIZE);
        setPreferredSize(new Dimension(SIZE, SIZE));
        setOpaque(false);
    }
}