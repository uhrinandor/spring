package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.spring.controllers.listeners.CycleListener;
import com.spring.controllers.utils.GameContext;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;

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
     * Ezután átadja a vezérlést a játékosoknak
     * Ha megvolt az összes hókotrós, utána jönnek a buszosok, majd újra az egész
     */
    public void cycle(){
        // TODO
        snowplowPhase = true;
        spIter = context.getSnowplowPlayers().iterator();
        nextPlayer();
    }

    /**
     * Az új játékosra állítás és jelzés a view-nak, hogy új játékos következik
     */
    public void nextPlayer(){
        if(snowplowPhase) {
            if(spIter.hasNext()) {
                SnowplowPlayer player = spIter.next();
                tracer.info("Next player: "+ player);
                for(CycleListener listener : cycleListeners){
                    listener.nextSnowPlowPlayer(player);
                }
            } else {
                snowplowPhase = false;
                bpIter = context.getBusPlayers().iterator();
                nextPlayer(); // átállunk buszosokra
            }
        } else {
            if (bpIter.hasNext()) {
                BusPlayer player = bpIter.next();
                tracer.info("Next player: "+ player);
                for(CycleListener listener : cycleListeners){
                    listener.nextBusPlayer(player);
                }
            } else {
                endRound();         
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
        // TODO:
        // Eredményszámítás
        tracer.info("The game has ended!");
        SnowplowPlayer winner1 = null;
        BusPlayer winner2 = null;
        for(CycleListener listener : cycleListeners){
            listener.onGameEnd(winner1, winner2);
        }
    }
}
