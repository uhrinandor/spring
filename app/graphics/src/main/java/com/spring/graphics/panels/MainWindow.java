package com.spring.graphics.panels;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.controllers.controllers.CycleController;
import com.spring.controllers.controllers.EndController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.controllers.SnowPlowController;
import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.controllers.listeners.CycleListener;
import com.spring.controllers.listeners.EndListener;
import com.spring.controllers.listeners.InitListener;
import com.spring.controllers.listeners.OnErrorListener;
import com.spring.controllers.listeners.SnowPlowListener;
import com.spring.controllers.listeners.SnowPlowPlayerListener;
import com.spring.controllers.utils.GameContext;
import com.spring.graphics.panels.map.MapGenerator;
import com.spring.graphics.panels.map.MapPanel;
import com.spring.graphics.panels.menubars.BusPlayerView;
import com.spring.graphics.panels.menubars.EndView;
import com.spring.graphics.panels.menubars.InitView;
import com.spring.graphics.panels.menubars.MenuBar;
import com.spring.graphics.panels.menubars.SnowplowPlayerView;
import com.spring.graphics.panels.menubars.SnowplowView;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;

public class MainWindow extends JFrame implements InitListener, CycleListener, OnErrorListener, SnowPlowListener, SnowPlowPlayerListener, EndListener {
    MapPanel mapPanel;
    MenuBar menuBar;
    CycleController cycleController;
    SnowPlowPlayerController snowPlowPlayerController;
    
    public MainWindow() {
        setTitle("Spring");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        try {
            Image img = ImageIO.read(getClass().getResource("/logo/snowplow.jpeg"));

            setIconImages(List.of(
                img.getScaledInstance(16, 16, Image.SCALE_SMOOTH),
                img.getScaledInstance(32, 32, Image.SCALE_SMOOTH),
                img.getScaledInstance(64, 64, Image.SCALE_SMOOTH),
                img.getScaledInstance(128, 128, Image.SCALE_SMOOTH)
            ));

            System.out.println("Icon loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        onNewGame();        

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

    @Override
    public void onSnowPlowSelected() {
        SnowPlowController snowPlowController = new SnowPlowController();
        snowPlowController.setPlayer(snowPlowPlayerController.info());
        snowPlowController.addErrorListener(this);
        snowPlowController.addSnowPlowListener(this);
        SnowplowView snowplowView = new SnowplowView(snowPlowController);
        menuBar.change(snowplowView);
    }

    @Override
    public void onSnowplowClosed() {
        SnowplowPlayerView view = new SnowplowPlayerView(snowPlowPlayerController);
        menuBar.change(view);
    }

    @Override
    public void onError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void nextSnowPlowPlayer(SnowplowPlayer player) {
        snowPlowPlayerController = new SnowPlowPlayerController(cycleController, player);
        snowPlowPlayerController.addErrorListener(this);
        snowPlowPlayerController.addSnowPlowPlayerListener(this);
        SnowplowPlayerView view = new SnowplowPlayerView(snowPlowPlayerController);
        menuBar.change(view);
    }

    @Override
    public void nextBusPlayer(BusPlayer player) {
        BusPlayerController busPlayerController = new BusPlayerController(cycleController);
        busPlayerController.addErrorListener(this);
        BusPlayerView busPlayerView = new BusPlayerView(busPlayerController);
        menuBar.change(busPlayerView);
    }

    @Override
    public void onGameEnd(SnowplowPlayer winner1, BusPlayer winner2) {
        EndController endController = new EndController(winner1, winner2, cycleController.getContext());
        endController.addEndListener(this);
        EndView endView = new EndView(endController);
        menuBar.change(endView);
    }

    @Override
    public void onCarPlaced(Building building) {
        JOptionPane.showMessageDialog(this, String.format("A new car has been placed next to %s", building.toString()), "New Car", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onGameStarted(GameContext context) {
        this.cycleController = new CycleController(context);
        cycleController.addCycleListener(this);
        cycleController.addCycleListener(this);
    }

    @Override
    public void onNewGame() {
        getContentPane().removeAll();
        GameContext context = new GameContext();
        InitController initController = new InitController(context);
        this.mapPanel = new MapPanel(context);
        InitView initView = new InitView(mapPanel, initController);

        this.menuBar = new MenuBar();
        setLayout(new BorderLayout());

        menuBar.change(initView);
        add(menuBar, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);

        chooseAndGenerateMap(initController);

        // TODO: ezt oldja meg a mapgenerator
        for (IRField field: context.getFields()) {
            int x = new Random().nextInt(10, 700);
            int y = new Random().nextInt(10, 370);
            mapPanel.addField(field, new Point(x, y));
        }

        for(IRoad road: context.getCrossRoads()){
            int x = new Random().nextInt(10, 700);
            int y = new Random().nextInt(10, 370);
            mapPanel.addCrossRoad(road, new Point(x, y));
        }

        for(Building building: context.getHomes()){
            mapPanel.addBuilding(building);
        }

        for(Building building: context.getOffices()){
            mapPanel.addBuilding(building);
        }

        for(Building building: context.getStations()){
            mapPanel.addBuilding(building);
        }

        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
