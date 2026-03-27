package com.spring.app.skeleton.models.field;
import java.util.List;

import com.spring.app.skeleton.utils.Entity;

public class CrossRoad extends Entity implements IRoad {
    private final List<IField> fields;

    public CrossRoad(List<IField> fields)
    {
        this.fields=fields;
    }

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