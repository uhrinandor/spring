package com.spring.app.skeleton;

import com.spring.app.skeleton.models.field.Field;
import com.spring.app.skeleton.models.vehicle.Snowplow;

public class Condition {
    public static Field generateMap(){
        return new Field();
    }

    public static Snowplow generateSnowplow(){
        // Itt generáljuk a hókotrót, inventoryt, fejeket stb.
        return new Snowplow();
    }
}
