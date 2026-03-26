package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.utils.IEntity;

public interface IVehicleVisitor extends IEntity {
    public void visit(Car c);
    public void visit(Snowplow s);
    public void visit(Bus b);
}
