package com.spring.app.skeleton.models.buildings;

import com.spring.app.skeleton.utils.Entity;

public abstract class Building extends Entity{
    protected IField field;
    
    public Building(IField field)
    {
        this.field=field;
    }

    public IField getField() {
        return field;
    }

    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }
}
