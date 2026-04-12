package com.spring.controllers.listeners;

import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;

public interface CycleListener {
    public void nextSnowPlowPlayer(SnowplowPlayer player);
    public void nextBusPlayer(BusPlayer player);
    public void onGameEnd(SnowplowPlayer winner1, BusPlayer winner2);
}
