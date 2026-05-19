package com.spring.graphics.panels;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.spring.controllers.controllers.CycleController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.controllers.SnowPlowController;
import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.controllers.utils.GameContext;
import com.spring.graphics.panels.map.MapGenerator;
import com.spring.graphics.panels.map.MapPanel;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.graphics.panels.menubars.InitView;
import com.spring.graphics.panels.menubars.SnowplowPlayerView;
import com.spring.graphics.panels.menubars.SnowplowView;
import com.spring.graphics.panels.menubars.MenuBar;
import com.spring.models.field.IRField;
import com.spring.models.layer.Layer;
import com.spring.models.player.SnowplowPlayer;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Spring");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        GameContext context = new GameContext();
        InitController initController = new InitController(context);
        CycleController cycleController = new CycleController(context);
        SnowPlowController snowPlowController = new SnowPlowController();
        snowPlowController.setPlayer(new SnowplowPlayer());
        SnowPlowPlayerController snowPlowPlayerController = new SnowPlowPlayerController(cycleController, null);

        MapPanel mapPanel = new MapPanel(context);
        InitView initView = new InitView(mapPanel, initController);
        SnowplowPlayerView snowplowPlayerView = new SnowplowPlayerView(snowPlowPlayerController);
        SnowplowView snowplowView = new SnowplowView(snowPlowController);

        MenuBar menuBar = new MenuBar();
        setLayout(new BorderLayout());

        menuBar.change(snowplowView);
        add(menuBar, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);

        chooseAndGenerateMap(initController);
        renderBase(context, mapPanel);

        setVisible(true);
    }

    private void chooseAndGenerateMap(InitController initController) {
        String[] maps = { "Map 1", "Map 2", "Map 3" };

        String selectedMap = (String) JOptionPane.showInputDialog(
                this,
                "Válassz pályát:",
                "Map választás",
                JOptionPane.QUESTION_MESSAGE,
                null,
                maps,
                maps[0]
        );

        MapGenerator mapGenerator = new MapGenerator(initController);

        if (selectedMap == null || selectedMap.equals("Map 1")) {
            mapGenerator.genMap1();
        } else if (selectedMap.equals("Map 2")) {
            mapGenerator.genMap2();
        } else if (selectedMap.equals("Map 3")) {
            mapGenerator.genMap3();
        }
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
