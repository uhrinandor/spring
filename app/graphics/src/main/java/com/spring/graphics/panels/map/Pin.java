package com.spring.graphics.panels.map;


import javax.swing.*;
import java.awt.*;

public abstract class Pin extends JPanel {
    protected static final int SIZE = 14;

    public Pin() {
        setSize(SIZE, SIZE);
        setPreferredSize(new Dimension(SIZE, SIZE));
        setOpaque(false);
    }
}