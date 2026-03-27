package com.spring.app.skeleton.models.buildings;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

/**
 * Absztrakt osztály melyből származik az Office, Station és Home osztály. Az osztály tárolja az 
 * épület melletti egyetlen IField-et.
 */
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
