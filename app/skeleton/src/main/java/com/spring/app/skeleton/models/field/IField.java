package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

public interface IField extends IEntity {
    public List<IField> getAvailible();
    public boolean isUnderGrund();
    public boolean tryEnter(Vehicle v);
    public boolean tryExit(IField f);
    public void interact();
    public void slip();
    public void melt();
}
