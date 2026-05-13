package com.spring.models.buildings;


public interface IBuildingVisitor{
    void visit(Office o);
    void visit(Station s);
    void visit(Home h);
}
