package com.spring.models.vehicle;

import java.util.Map;

import com.spring.models.head.Item;
import com.spring.models.utils.IEntity;

public interface IRInventory extends IEntity{
     public Map<Item,Integer> getMap();
}