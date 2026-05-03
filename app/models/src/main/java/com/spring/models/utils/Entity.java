package com.spring.models.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements IEntity {
    protected int id;
    protected List<IObserver> observers = new ArrayList<>();
    private static int nextId = 0;

    protected Entity(){
        this.id = nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "@" + id;
    }

    public static void reset(){
        nextId = 0;
    }

    @Override
    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.notifyChange(this);
        }
    }
}


