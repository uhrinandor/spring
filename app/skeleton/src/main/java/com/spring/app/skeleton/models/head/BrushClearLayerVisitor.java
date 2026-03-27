package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;

/**
 * Eldönti, hogy a söprőfej eltakaríthatja-e az adott mezőn az út felszínt.
 */
public class BrushClearLayerVisitor extends Entity implements ILayerVisitor{

    /**
     * Megadja, hogy a fej letakaríthatja-e az adott felületet.
     */
    private boolean result;

    public boolean getResult(){
        return result;
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a havat.
     * Minden esetben le tudja takarítani.
     * @param a hóréteg amit le kéne takarítani
     */
    @Override
    public void visit(Snow s) {
        result = true;
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a jeget.
     * Ha törött a jég, akkor le tudja takarítani.
     * @param a jég amit le kéne takarítani
     */
    @Override
    public void visit(Ice i) {
        result = i.getBroken();
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a sima réteget. 
     * Le fogja tudni minden esetben.
     * @param l a sima út
     */
    @Override
    public void visit(Layer l) {
        result = true;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
}
