package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.EndListener;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.IPlayer;
import com.spring.models.player.SnowplowPlayer;

public class EndController extends BaseController{
    SnowplowPlayer winner1;
    BusPlayer winner2;
    List<EndListener> endListeners = new ArrayList<>();

    public EndController(SnowplowPlayer winner1, BusPlayer winner2) {
        this.winner1 = winner1;
        this.winner2 = winner2;
    }

    public void addEndListener(EndListener listener){
        endListeners.add(listener);
    }

    public List<IPlayer> winners(){
        return List.of(winner1, winner2);
    }

    public void newGame(){
        for(EndListener listener : endListeners){
            listener.onNewGame();
        }
    }

}
