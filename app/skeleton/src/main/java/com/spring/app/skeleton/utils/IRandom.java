package com.spring.app.skeleton.utils;

import org.w3c.dom.Entity;

public interface IRandom extends Entity{
    public boolean nextBool(double probability);
}
