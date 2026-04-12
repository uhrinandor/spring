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
 * Eldönti, hogy a jégtörőfej eltakaríthatja-e az adott mezőn az út felszínt.
 */
public class IceBreakerClearLayerVisitor extends Entity implements ILayerVisitor{
    /**
     * Megadja, hogy a fej letakaríthatja-e az adott felületet.
     */
    Ice result = null; 


    /**
     * Jégtörő hóval nem tud interaktálni
     */
    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        Tracer.getInstance().exitFunction();
    }

    /**
     * Összetöri a jeget
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        i.setBroken(true);
        result = i;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Nem tud interaktálni a réteggel
     */
    @Override
    public void visit(Layer l) {
        Tracer.getInstance().enterFunction(this, "visit",l);
        Tracer.getInstance().exitFunction();
    }

    public Ice getResult() {
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public void visit(Stone s) {
        
    }
    
}
