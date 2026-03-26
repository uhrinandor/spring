package com.spring.app.skeleton.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.utils.Entity;

public class SnowMergeVisitor extends Entity implements ILayerVisitor{
    private Snow base;
    private ILayer result;

    public SnowMergeVisitor(Snow base){
        this.base = base;
        this.result = base;
    }

    @Override
    public void visit(Snow s) {
        base.setLevel(base.getLevel() + s.getLevel());
        result = base; 
    }

    @Override
    public void visit(Ice i) {
        base.setLevel(base.getLevel() + 1);
        result = base;
    }

    @Override
    public void visit(Layer l) {
        result = base;
    }

    public ILayer getResult() {
        return result;
    }

    @Override
    public List<String> init() {
        List<String> tmp = new ArrayList<>();
        tmp.add(base != null ? base.toString() : "null");
        tmp.add(result != null ? result.toString() : "null");
        return tmp;
    }
    
}
