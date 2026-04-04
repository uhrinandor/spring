package com.spring.models.buildings;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

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
        Tracer.getInstance().enterFunction(this, "getField");
        Tracer.getInstance().exitFunction(field);
        return field;
    }

    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }
}
