package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spring.controllers.listeners.CycleListener;
import com.spring.controllers.utils.GameContext;
import com.spring.controllers.utils.PickCarVisitor;
import com.spring.models.buildings.Home;
import com.spring.models.field.IField;
import com.spring.models.layer.ILayer;
import com.spring.models.layer.Snow;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.IPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.random.Random;
import com.spring.models.vehicle.Car;

public class CycleController extends BaseController{
    GameContext context;
    boolean snowplowPhase = true;
    List<CycleListener> cycleListeners = new ArrayList<>();
    Iterator<SnowplowPlayer> spIter;
    Iterator<BusPlayer> bpIter;

    public CycleController(GameContext context){
        this.context = context;
    }

    public void addCycleListener(CycleListener listener){
        cycleListeners.add(listener);
    }
    /**
     * Feladata: az automatikus dolgok végrehajtása:
     * - Havazás
     * - Olvasztás
     * - Autók mozgása
     * - Új autók létrehozása a Home-ok mellett, ha nincs elég autó a pályán
     * Ezután átadja a vezérlést a játékosoknak
     * Ha megvolt az összes hókotrós, utána jönnek a buszosok, majd újra az egész
     */
    public void cycle(){
        snowplowPhase = true;
        spIter = context.getSnowplowPlayers().iterator();
        IPlayer player = nextPlayer();
        if(player == null) return;

        Random rand = new Random();
        PickCarVisitor pickCarVisitor = new PickCarVisitor();
        int carsOnField = 0;

        Map<Car, Boolean> moved = new HashMap<>();
        // Havazás és autók begyűjtése
        for(IField field : context.getFields()){
            if(context.getIsSnowing() && !field.isUnderGround() && rand.nextBool(0.5)){
                Snow s = new Snow(1);
                ILayer layer = field.getLayer().merge(s);
                field.setLayer(layer);
            }

            // Olvasztás
            field.melt();

            // Autó lépés
            if(field.getVehicle() != null){
                field.getVehicle().accept(pickCarVisitor);
                Car picked = pickCarVisitor.getPicked();
                if(picked != null){
                    carsOnField++;
                    if(!context.getCars().contains(picked))
                        context.getCars().add(picked);

                    if(!moved.containsKey(picked)){
                        picked.step();
                        moved.put(picked, true);
                    }
                }
            }
        }

        if(carsOnField < context.getHomes().size() && !context.getOffices().isEmpty()){
            for(Home h : context.getHomes()){
                if(carsOnField >= context.getHomes().size()) break;
                if(h.getField().getVehicle() == null){
                    int officeSerial = new java.util.Random().nextInt(0, context.getOffices().size());
                    h.generateCar(context.getOffices().get(officeSerial));
                    carsOnField++;
                    for(CycleListener listener : cycleListeners){
                        listener.onCarPlaced(h);
                    }
                }
            }
        }


        
    }

    /**
     * Az új játékosra állítás és jelzés a view-nak, hogy új játékos következik
     */
    public IPlayer nextPlayer(){
        if(snowplowPhase) {
            if(spIter.hasNext()) {
                SnowplowPlayer player = spIter.next();
                for(CycleListener listener : cycleListeners){
                    listener.nextSnowPlowPlayer(player);
                }
                return player;
            } else {
                snowplowPhase = false;
                bpIter = context.getBusPlayers().iterator();
                return nextPlayer(); // átállunk buszosokra
            }
        } else {
            if (bpIter.hasNext()) {
                BusPlayer player = bpIter.next();
                for(CycleListener listener : cycleListeners){
                    listener.nextBusPlayer(player);
                }
                return player;
            } else {
                endRound();         
                return null;
            }
        }
    }

    /**
     * A kör végén csökkentjük a körszámot, ha van még kör, akkor újraindítjuk a cycle-t, egyébként vége a játéknak
     */
    private void endRound(){
        if(context.increaseRound()) cycle();
        else endGame();
    }

    /**
     * Játék vége
     */
    private void endGame(){
        SnowplowPlayer winner1 = context.getSnowplowPlayers().stream().max((p1, p2) -> p1.getPoints() - p2.getPoints()).orElse(null);
        BusPlayer winner2 = context.getBusPlayers().stream().max((p1, p2) -> p1.getPoints() - p2.getPoints()).orElse(null);
        for(CycleListener listener : cycleListeners){
            listener.onGameEnd(winner1, winner2);
        }
    }

    public GameContext getContext() {
        return context;
    }
}
