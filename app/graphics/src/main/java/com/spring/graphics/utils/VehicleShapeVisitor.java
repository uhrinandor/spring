/*package com.spring.graphics.utils;

import com.spring.models.utils.Entity;
import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.IVehicleVisitor;
import com.spring.models.vehicle.Snowplow;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class VehicleShapeVisitor extends Entity implements IVehicleVisitor {

    private Shape shape;

    public Shape getShape() {
        return shape;
    }

    @Override
    public void visit(Car c){
        double x = c.getX(), y = c.getY(), d = c.getDiameter();
        shape = new Ellipse2D.Double(x, y, d, d);
    }

    @Override
    public void visit(Snowplow s){

    }

    @Override
    public void visit(Bus b){
        double x = b.getX(), y = b.getY(), d = b.getDiameter();
        shape = new Ellipse2D.Double(x, y, d, d);
    }
}
*/