package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.OnErrorListener;
import com.spring.models.utils.Tracer;

public abstract class BaseController {
    List<OnErrorListener> listeners = new ArrayList<>();
    Tracer  tracer = Tracer.getInstance();

    public void addErrorListener(OnErrorListener listener){
        listeners.add(listener);
    }

    public void error(String message){
        for(OnErrorListener listener : listeners){
            listener.onError(message);
        }
    }
}
