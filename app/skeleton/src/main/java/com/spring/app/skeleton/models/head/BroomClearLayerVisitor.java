package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;

/**
 * Eldönti, hogy a hányófej eltakaríthatja-e az adott mezőn az út felszínt.
 */
public class BroomClearLayerVisitor extends Entity implements ILayerVisitor{
    /**
     * Megadja, hogy a fej letakaríthatja-e az adott felületet.
     */
    private boolean result;

    public boolean getResult(){
        return result;
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a havat.
     * El tudja takarítani minden esetben.
     * @param s a hó amit el kéne takarítani
     */
    @Override
    public void visit(Snow s) {
        result=true;
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a jeget.
     * Ha törött, akkor igen.
     * @param i a jég amit el kéne takarítani
     */
    @Override
    public void visit(Ice i) {
        result = i.getBroken();
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a sima utat.
     * Le tudja takarítani minden esetben.
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
