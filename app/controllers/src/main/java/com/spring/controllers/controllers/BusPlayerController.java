package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.player.BusPlayer;
import com.spring.models.vehicle.Vehicle;

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
        Vehicle bus = player.vehicles().get(0);
        IField current = bus.getDriver().getCurrent();
        IField next = bus.getDriver().getNext();

        if(current != null && bus.equals(current.getVehicle())) return listAvailable(current);
        else if(next != null && bus.equals(next.getVehicle())) return listAvailable(next);
        return List.of();
    }

    /**
     * Vissazaadja a legális lépéseket egy adott mezőről
     * @param field A mező
     * @return A mezők listája
     */
    private List<IField> listAvailable(IField field){
        List<IField> front = field.getFront().getAvailable();
        List<IField> result = new ArrayList<>();
        result.addAll(front);
        if(field.getRight() != null) result.add(field.getRight());
        if(field.getLeft() != null) result.add(field.getLeft());

        return result;
    }

    /**
     * Lépés beállítása, végrehajtása, átléptetés a következő játékosra
     * @param serial
     */
    public void setNext(int serial){
        List<IField> available = listAvailable();

        if(serial < 0 || serial >= available.size()){
            error("Invalid field serial: "+serial);
            return;
        }

        IField next = available.get(serial);
        Vehicle bus = player.vehicles().get(0);
        bus.getDriver().setNext(next);
        bus.step();

        cycleController.nextPlayer();
    }

    /**
     * A következő játékosra lépés
     */
    public void nextPlayer(){
        cycleController.nextPlayer();
    }
}
