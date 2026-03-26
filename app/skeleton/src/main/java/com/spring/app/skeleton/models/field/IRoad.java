package com.spring.app.skeleton.models.field;
import java.util.List;

import com.spring.app.skeleton.utils.IEntity;

public interface IRoad extends IEntity{
    public List<IField> getAvailable();
}
