package com.spring.models.head;

import java.util.List;

import com.spring.models.layer.ILayer;
import com.spring.models.layer.ILayerVisitor;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;
import com.spring.models.layer.Stone;
import com.spring.models.utils.Entity;

/**
 * Megvalósítja a sárkány által megtisztított réteg létrehozását. A sárkány minden réteget letisztít maga előtt, így minden réteg helyére sima aszfalt kerül, a köveket leszámítva, amiknek csak a belső rétegét tisztítja meg.
 */
public class DragonClearLayerVisitor extends Entity implements ILayerVisitor {
    ILayer result;

    /**
     * Az eredményt adja vissza
     * @return
     */
    public ILayer getResult() {
        return result;
    } 


    @Override
    public List<String> init() {
        return List.of("result: "+result);
    }

    /**
     * Tiszta felületet hoz létre
     * @return
     */
    @Override
    public void visit(Snow s) {
        result = new Layer();
    }

    /**
     * Tiszta felületet hoz létre
     * @return
     */
    @Override
    public void visit(Ice i) {
        result = new Layer();
    }

    /**
     * Tiszta felületet hoz létre
     * @return
     */
    @Override
    public void visit(Layer l) {
        result = new Layer();
    }

    /**
     * A belső réteget megtisztítja, a külső réteg marad
     * @return
     */
    @Override
    public void visit(Stone s) {
        s.getPrevious().accept(this);
        s.setPrevious(result);
        result = s;
    }
    
}
