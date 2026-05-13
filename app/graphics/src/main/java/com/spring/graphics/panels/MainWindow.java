package com.spring.graphics.panels;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.spring.controllers.controllers.InitController;
import com.spring.controllers.utils.GameContext;
import com.spring.graphics.panels.map.MapPanel;
import com.spring.graphics.panels.menubars.InitView;
import com.spring.graphics.panels.menubars.MenuBar;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Spring");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GameContext context = new GameContext();
        InitController initController = new InitController(context);
        MapPanel mapPanel = new MapPanel();
        InitView initView = new InitView(mapPanel, initController);

        MenuBar menuBar = new MenuBar();
        setLayout(new BorderLayout());

        menuBar.change(initView);
        add(menuBar, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
