package com.spring.models.layer;

import java.util.List;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Megvalósítja a rétegek és a rájuk kerülő só interakcióját.
 */
public class MeltLayerVisitor extends Entity implements ILayerVisitor {
    /**
     * Az alap réteg és a só kölcsönhatásának eredményeként létrejövő réteg.
     */
    private ILayer result;

    public ILayer getResult() {
        return result;
    }

    /**
     * Ha a hó nem alacsony/letaposott, akkor csökkentjük a szintjét, egyébként eltávolítjuk a réteget.
     * @param s a hó, amire a só hat
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

    /**
     * A jeget elolvasztja a só és sima aszfalt réteg lesz helyette.
     * @param i a jég amire a só hat
     */
    @Override
    public void visit(Ice i) {
        result = new Layer();
    }

    /**
     * A sima aszfalt rétegre nincs hatással a só, az eredmény ugyanúgy sima réteg marad.
     * @param l sima aszfalt réteg amire a só hat
     */
    @Override
    public void visit(Layer l) {
        result = l;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public void visit(Stone s) {
        result = s;
    }
    
}
