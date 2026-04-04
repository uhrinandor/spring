package com.spring.models.field;
import java.util.List;

import com.spring.models.utils.Entity;

/**
 * Tartalmazza azokat a mezőket ahova el lehet jutni egy lépéssel ha a kereszteződés egyik 
 * kezdőpontjára érünk.
 */
public class CrossRoad extends Entity implements IRoad {
    private final List<IField> fields;

    public CrossRoad(List<IField> fields)
    {
        this.fields=fields;
    }

    /**
     * Visszatér a kimenő Field-ekkel.
     */
    @Override
    public List<IField> getAvailable()
    {
        return fields;
    }

    @Override
    public List<String> init() {
       return List.of("fields: " + fields);
    }
}