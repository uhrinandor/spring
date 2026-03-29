package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;
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
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a havat.
     * Minden esetben le tudja takarítani.
     * @param a hóréteg amit le kéne takarítani
     */
    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        result = true;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a jeget.
     * Ha törött a jég, akkor le tudja takarítani.
     * @param a jég amit le kéne takarítani
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        result = i.getBroken();
        Tracer.getInstance().exitFunction();
    }

    /**
     * Eldönti, hogy a seprőfej letakaríthatja-e a sima réteget. 
     * Le fogja tudni minden esetben.
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
}
