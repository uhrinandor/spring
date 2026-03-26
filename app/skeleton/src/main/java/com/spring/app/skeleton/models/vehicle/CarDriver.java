package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

public class CarDriver extends Entity implements IDriver {
    IField current;
    IField next;

    @Override
    public IField nextMove() {
        // TODO: ez itt a kalkulált
        return next;
    }

    @Override
    public void setNext(IField f) {
        current = next;
        next = f;
    }

    @Override
    public IField getCurrent() {
      return current;
    }

    @Override
    public IField getNext() {
        return next;
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
}
