package com.spring.app.skeleton.models.random;

import java.util.List;

import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Random extends Entity implements IRandom{

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public boolean nextBool(double probability) {
        return Tracer.getInstance().askBool("Megtörténjen?");
    }
    
}
