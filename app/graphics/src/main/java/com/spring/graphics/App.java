package com.spring.graphics;

import javax.swing.SwingUtilities;

import com.spring.graphics.panels.MainWindow;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
