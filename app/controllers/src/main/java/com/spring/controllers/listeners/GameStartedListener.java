package com.spring.controllers.listeners;

import com.spring.controllers.utils.GameContext;

public interface GameStartedListener {
    public void onGameStarted(GameContext context);
}
