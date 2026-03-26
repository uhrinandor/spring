package com.spring.app.skeleton.models.layer;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;
import com.spring.app.skeleton.utils.IRandom;

public interface ILayer extends IEntity {
    public ILayer merge(ILayer layer);
    public boolean slip(Vehicle v, IRandom random);
    public boolean canExit(Vehicle v);
    public ILayer enter();
    public void accept(ILayerVisitor visitor);
}
