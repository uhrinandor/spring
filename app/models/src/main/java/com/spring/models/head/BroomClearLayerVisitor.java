package com.spring.models.head;

import java.util.List;

import com.spring.models.layer.ILayerVisitor;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;
import com.spring.models.layer.Stone;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

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
        result = true;
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a jeget.
     * Ha törött, akkor igen.
     * @param i a jég amit el kéne takarítani
     */
    @Override
    public void visit(Ice i) {
        result = Tracer.getInstance().askBool("Torott-e a jeg?", i.getBroken());
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a sima utat.
     * Le tudja takarítani minden esetben.
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

    @Override
    public void visit(Stone s) {
        result = true;
    }

}
