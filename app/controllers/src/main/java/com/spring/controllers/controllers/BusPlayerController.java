package com.spring.controllers.controllers;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.player.BusPlayer;

public class BusPlayerController extends BaseController {
    BusPlayer player;
    CycleController cycleController;

    public BusPlayerController(CycleController cycleController) {
        this.cycleController = cycleController;
    }

    /**
     * A játékos beállítása, akinek a buszját irányítjuk
     * @param player
     */
    public void setPlayer(BusPlayer player) {
        this.player = player;
    }

    /**
     * Visszaadja a buszjátékost
     * @return A buszjátékos
     */
    public BusPlayer info(){
        return player;
    }

    /**
     * Legális lépések listázása a busz kontextusában
     * @return Egy lista a legális lépésekről
     */
    public List<IField> listAvailable(){
        //TODO:
        return List.of();
    }

    /**
     * Lépés beállítása, végrehajtása, átléptetés a következő játékosra
     * @param serial
     */
    public void setNext(int serial){
        // TODO:
        cycleController.nextPlayer();
    }
}
