package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.head.IHead;
import com.spring.app.skeleton.utils.IEntity;

public interface ISnowPlow extends IEntity {
    public boolean switchHead(IHead head);
    public IInventory getInventory();
}
