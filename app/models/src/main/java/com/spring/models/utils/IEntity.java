package com.spring.models.utils;

import java.util.List;

public interface IEntity{
    public int getId();
    public abstract List<String> init();
    public void subscribe(IObserver observer);
    public void unsubscribe(IObserver observer);
    void notifyObservers();
}
