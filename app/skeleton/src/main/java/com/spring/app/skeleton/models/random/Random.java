package com.spring.app.skeleton.models.random;

import java.util.List;

import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Valószínűség bekövetkezésének kiszámítása. Annak eldöntésére használja a program,
 * hogy egy autó vagy busz megcsúszik-e a jégen.
 */
public class Random extends Entity implements IRandom{

    @Override
    public List<String> init() {
        return List.of();
    }

    /**
     * @param probability a megadott valószínűséggel tér vissza igazzal.
     * @return az eredmény
     */
    @Override
    public boolean nextBool(double probability) {
        return Tracer.getInstance().askBool("Megtörténjen?");
    }
    
}
