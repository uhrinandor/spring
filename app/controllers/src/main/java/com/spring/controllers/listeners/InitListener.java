package com.spring.controllers.listeners;

import com.spring.controllers.utils.GameContext;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;

public interface InitListener {
    public void onGameStarted(GameContext context);
    public void onFieldAdded(IRField field);
    public void onCrossRoadAdded(IRField field);
    public void onBuildingAdded(Building building);
}
