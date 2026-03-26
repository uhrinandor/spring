package com.spring.app.skeleton.models.random;

import com.spring.app.skeleton.utils.IEntity;

public interface IRandom extends IEntity{
    public boolean nextBool(double probability);
}
