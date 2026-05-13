package com.spring.graphics.panels.map;

import com.spring.controllers.controllers.InitController;
import com.spring.models.layer.Snow;

public class MapGenerator {
    private final InitController initController;

    public MapGenerator(InitController initController) {
        this.initController = initController;
    }

    public void genMap1() {
        initController.addField(new Snow(1), false);
        
    }
}
