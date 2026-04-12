package com.spring.controllers.listeners;

import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;

public interface OnGameEndedListener {
    public void onGameEnded(SnowplowPlayer winner, BusPlayer busWinner);
}
