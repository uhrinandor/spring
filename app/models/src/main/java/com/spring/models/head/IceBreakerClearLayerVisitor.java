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
    }

    /**
     * Összetöri a jeget
     */
    @Override
    public void visit(Ice i) {
        i.setBroken(true);
        result = i;
    }

    /**
     * Nem tud interaktálni a réteggel
     */
    @Override
    public void visit(Layer l) {
    }

    public Ice getResult() {
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
