package com.spring.graphics.panels;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

import com.spring.controllers.controllers.InitController;
import com.spring.controllers.utils.GameContext;
import com.spring.graphics.panels.map.MapPanel;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.graphics.panels.menubars.InitView;
import com.spring.graphics.panels.menubars.MenuBar;
import com.spring.models.field.IRField;
import com.spring.models.layer.Layer;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Spring");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GameContext context = new GameContext();
        InitController initController = new InitController(context);
        MapPanel mapPanel = new MapPanel(context);
        InitView initView = new InitView(mapPanel, initController);

        MenuBar menuBar = new MenuBar();
        setLayout(new BorderLayout());

        menuBar.change(initView);
        add(menuBar, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);

        genMap(initController);
        renderBase(context, mapPanel);

        setVisible(true);
    }

    public void genMap(InitController initController) {
        initController.addField(new Layer(), false);
        initController.addField(new Layer(), false);
        initController.addField(new Layer(), false);
        initController.addField(new Layer(), false);
        initController.setSide(true, 0, 1);
        initController.setFrontField(1, 2);
        initController.setFrontField(2, 3);
    }

    public void renderBase(GameContext context, IMap map) {
        for(IRField field : context.getFields()) {
            int x = new Random().nextInt(1, 400);
            int y = new Random().nextInt(1, 400);
            map.addField(field, new Point(x, y));
        }
    }
}
