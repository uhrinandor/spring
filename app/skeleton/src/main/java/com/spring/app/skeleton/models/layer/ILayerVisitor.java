package com.spring.app.skeleton.models.layer;

import com.spring.app.skeleton.utils.IEntity;

public interface ILayerVisitor extends IEntity {
    public void visit(ILayer layer);
}