package com.spring.app.skeleton.models.field;
import java.util.List;

import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.IEntity;
public class CrossRoad extends Entity implements IRoad {
    private List<IField> fields;

    public CrossRoad(List<IField> fields)
    {
        this.fields=fields;
    }
    
    public List<IField> getAvailable()
    {
        return fields;
    }

    
    public List<String> init() {
       return List.of("fields: " + fields);
    }
}