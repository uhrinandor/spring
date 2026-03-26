package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class MeltLayerVisitor extends Entity implements ILayerVisitor {
    private ILayer result;

    public ILayer getResult() {
        return result;
    }

    /**
     * Ha a hó nem alacsony/letaposott, akkor csökkentjük a szintjét, egyébként eltávolítjuk a réteget
     */
    @Override
    public void visit(Snow s) {
        int level = Tracer.getInstance().askInt("Milyen magas a hó?");
        if(level > 1){
            s.setLevel(level - 1);
            result = s;
            return;
        }
        result = new Layer();
    }

    @Override
    public void visit(Ice i) {
        result = new Layer();
    }

    @Override
    public void visit(Layer l) {
        result = l;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
