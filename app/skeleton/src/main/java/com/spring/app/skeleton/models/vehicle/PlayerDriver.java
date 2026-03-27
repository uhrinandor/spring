package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class PlayerDriver extends Entity implements IDriver {
    IField current;
    IField next;

    @Override
    public IField nextMove() {
        Tracer.getInstance().enterFunction(this, "nextMove");
        Tracer.getInstance().exitFunction(next);
        return next;
    }

    @Override
    public void setNext(IField f) {
        Tracer.getInstance().enterFunction(this, "setNext",f);
        current = next;
        next = f;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public IField getCurrent() {
        Tracer.getInstance().enterFunction(this, "getCurrent");
        Tracer.getInstance().exitFunction(current);
      return current;
    }

    @Override
    public IField getNext() {
        Tracer.getInstance().enterFunction(this, "getNext");
        Tracer.getInstance().exitFunction(next);
        return next;
    }

    @Override
    public List<String> init() {
        return List.of("current: " + current, "next: " + next);
    }

    @Override
    public void setCurrent(IField f) {
        Tracer.getInstance().enterFunction(this, "setCurrent");
        Tracer.getInstance().exitFunction();
        current = f;
    }
}
