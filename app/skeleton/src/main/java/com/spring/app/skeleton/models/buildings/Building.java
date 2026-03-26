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
}
