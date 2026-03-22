package com.spring.app.utils;

public abstract class Entity {
    protected int id;
    private static int nextId = 0;

    protected Entity(){
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}


