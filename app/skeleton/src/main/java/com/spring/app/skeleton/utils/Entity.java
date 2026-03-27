package com.spring.app.skeleton.utils;

public abstract class Entity implements IEntity {
    protected int id;
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
}


