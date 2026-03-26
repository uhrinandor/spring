package com.spring.app.utils;

public abstract class Entity {
    private static int nextId = 0;
    int id;

    protected Entity(){
        this.id = nextId++;
    }
}
