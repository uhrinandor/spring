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
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a havat.
     * El tudja takarítani minden esetben.
     * @param s a hó amit el kéne takarítani
     */
    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        result = true;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a jeget.
     * Ha törött, akkor igen.
     * @param i a jég amit el kéne takarítani
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        result = Tracer.getInstance().askBool("Torott-e a jeg?", i.getBroken());
        Tracer.getInstance().exitFunction();
    }

    /**
     * Eldönti, hogy a hányófej letakaríthatja-e a sima utat.
     * Le tudja takarítani minden esetben.
     * @param l a sima út
     */
    @Override
    public void visit(Layer l) {
        Tracer.getInstance().enterFunction(this, "visit",l);
        result = true;
        Tracer.getInstance().exitFunction();
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
