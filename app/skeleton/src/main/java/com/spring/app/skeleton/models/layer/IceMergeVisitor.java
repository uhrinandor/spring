package com.spring.app.skeleton.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.utils.Entity;

public class IceMergeVisitor extends Entity implements ILayerVisitor{
    private Ice base;
    private ILayer result;

    public IceMergeVisitor(Ice base){
        this.base = base;
        this.result = base;
    }

    @Override
    public void visit(Snow s) {
        result = base;
    }

    @Override
    public void visit(Ice i) {
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
