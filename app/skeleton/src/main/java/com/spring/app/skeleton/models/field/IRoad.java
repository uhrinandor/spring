package com.spring.app.skeleton.models.field;
import java.util.ArrayList;

import com.spring.app.skeleton.utils.IEntity;

public interface IRoad extends IEntity{
    public ArrayList<IField> getAvailable();
}
