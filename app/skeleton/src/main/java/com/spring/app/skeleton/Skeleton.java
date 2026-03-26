package com.spring.app.skeleton;

import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.Layer;

public class Skeleton {
    public static ILayer askLayer(String msg){
        // Magas hó, alacsony hó, jég, üres
        return new Layer();
    }

    public static boolean askBool(String msg){
        return true;
    }
}
