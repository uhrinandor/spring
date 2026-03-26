package com.spring.app.skeleton.utils;

import java.util.List;

public abstract class Entity {
    protected int id;
    private static int nextId = 0;

    protected Entity(){
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    abstract List<String> init();
}


