package com.spring.app.skeleton.models.buildings;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

public abstract class Building extends Entity{
    protected IField field;
    
    protected Building(IField field)
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
