package com.spring.graphics.panels.menubars;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.controllers.controllers.InitController;
import com.spring.graphics.panels.map.interfaces.IMap;

public class InitView extends JPanel {
    IMap map;
    InitController controller;

    public InitView(IMap map, InitController controller){
        super();
        this.map = map;
        this.controller = controller;

        add(new JLabel("INIT VIEW"));
    }
}
