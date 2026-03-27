package com.spring.app.skeleton.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class IceMergeVisitor extends Entity implements ILayerVisitor{
    private Ice base;
    private ILayer result;

    public IceMergeVisitor(Ice base){
        this.base = base;
        this.result = base;
    }

    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public void visit(Layer l) {
        Tracer.getInstance().enterFunction(this, "visit",l);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    public ILayer getResult() {
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
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
