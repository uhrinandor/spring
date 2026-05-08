package com.spring.graphics.panels;
import java.awt.GridLayout;

import javax.swing.JFrame;

import com.spring.controllers.controllers.InitController;
import com.spring.controllers.utils.GameContext;
import com.spring.graphics.panels.map.MapPanel;
import com.spring.graphics.panels.menubars.InitView;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Spring");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GameContext context = new GameContext();
        InitController initController = new InitController(context);
        MapPanel mapPanel = new MapPanel();
        InitView initView = new InitView(mapPanel, initController);
        setLayout(new GridLayout(2, 1));
        
        add(initView);
        add(mapPanel);
        setVisible(true);

    }
}
