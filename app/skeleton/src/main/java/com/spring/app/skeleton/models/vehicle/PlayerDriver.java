package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

public class PlayerDriver extends Entity implements IDriver {
    IField current;
    IField next;

    @Override
    public IField nextMove() {
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
        return List.of("current: " + current, "next: " + next);
    }

    @Override
    public void setCurrent(IField f) {
        current = f;
    }
}
