package com.spring.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.utils.Entity;

public class StoneMergeVisitor extends Entity implements ILayerVisitor {

    /**
     * Az alap kőréteget.
     */
    private Stone base;

    /**
     * A kőréteg és a rákerülő másik réteg együttes eredménye.
     */
    private ILayer result;

    public ILayer getResult(){
        return result;
    }

    public StoneMergeVisitor(Stone base){
        this.base = base;
        this.result = base;
    }

    @Override
    public List<String> init() {
        List<String> tmp = new ArrayList<>();
        tmp.add("base: " + (base != null ? base.toString() : "null"));
        tmp.add("result: " + (result != null ? result.toString() : "null"));
        return tmp;
    }

    @Override
    public void visit(Snow s) {
        result = s;
    }

    @Override
    public void visit(Ice i) {
        base.setPrevious(i);
        result = base;
    }

    @Override
    public void visit(Layer l) {
        base.setPrevious(l);
        result = base;
    }

    @Override
    public void visit(Stone s) {
        result = new Stone(s.getPrevious().merge(base));
    }
}
