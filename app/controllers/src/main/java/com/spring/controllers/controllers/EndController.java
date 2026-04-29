package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.EndListener;
import com.spring.controllers.utils.GameContext;
import com.spring.models.field.IRField;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.IPlayer;
import com.spring.models.player.IRPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.vehicle.Vehicle;

public class EndController extends BaseController{
    SnowplowPlayer winner1;
    BusPlayer winner2;
    List<EndListener> endListeners = new ArrayList<>();
    GameContext context;

    public EndController(SnowplowPlayer winner1, BusPlayer winner2, GameContext context) {
        this.winner1 = winner1;
        this.winner2 = winner2;
        this.context = context;
    }

    public void addEndListener(EndListener listener){
        endListeners.add(listener);
    }

    public List<IPlayer> winners(){
        List<IPlayer> winners = new ArrayList<>();
        if(winner1 != null) winners.add(winner1);
        if(winner2 != null) winners.add(winner2);
        return winners;
    }

    public void newGame(){
        for(EndListener listener : endListeners){
            listener.onNewGame();
        }
    }

    public IRField getField(int serial){
        if(serial < 0 || serial >= context.getFields().size()){
            error("Invalid field serial: "+serial);
            return null;
        }
        return context.getFields().get(serial);
    }

    public IRPlayer getBusPlayers(int serial){
        if(serial < 0 || serial >= context.getBusPlayers().size()){
            error("Invalid bus player serial: "+serial);
            return null;
        }
        return context.getBusPlayers().get(serial);
    }

    public IRPlayer getSnowPlowPlayers(int serial){
        if(serial < 0 || serial >= context.getSnowplowPlayers().size()){
            error("Invalid snowplow player serial: "+serial);
            return null;
        }
        return context.getSnowplowPlayers().get(serial);
    }

    public Vehicle getCar(int serial){
        if(serial < 0 || serial >= context.getCars().size()){
            error("Invalid car serial: "+serial);
            return null;
        }
        return context.getCars().get(serial);
    }


    public Vehicle getBus(int serial){
        if(serial < 0 || serial >= context.getBusPlayers().size()){
            error("Invalid bus player serial: "+serial);
            return null;
        }
        return context.getBusPlayers().get(serial).vehicles().get(0);
    }

    public Vehicle getSnowplow(int serial){
        if(serial < 0 || serial >= context.getSnowplowPlayers().size()){
            error("Invalid snowplow player serial: "+serial);
            return null;
        }
        return context.getSnowplowPlayers().get(serial).vehicles().get(0);
    }
}
