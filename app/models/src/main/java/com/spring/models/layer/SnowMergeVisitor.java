package com.spring.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Megvalósítja a havas útfelszín és a rá kerülő út takarók interakcióját.
 */
public class SnowMergeVisitor extends Entity implements ILayerVisitor{
    /**
     * Az alap hóréteg.
     */
    private Snow base;
    /**
     * A hóréteg és a rákerülő másik réteg együttes eredménye.
     */
    private ILayer result;

    public SnowMergeVisitor(Snow base){
        this.base = base;
        this.result = base;
    }

    /**
     * Az alap hórétegre újabb hóréteg kerül. Ekkor az eredmény egy hóréteg,
     * menynek magassága a két hóréteg összesített magassága.
     * @param s az alapra kerülő újabb hóréteg
     */
    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        base.setLevel(base.getLevel() + s.getLevel());
        result = base; 
        Tracer.getInstance().exitFunction();
    }

    /**
     * Az alap hórétegre jég kerül. Ekkor a hóréteg szintje 1-el növekszik. Az eredmény egy hóréteg.
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        base.setLevel(base.getLevel() + 1);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Az alap hórétegre semmi nem kerül. Az eredmény ugyanaz a hóréteg ami eddig volt.
     * @param l alapvetően a Layer az aszfaltnak felel meg, de ilyen formában nem tud egy másik
     * réteg fölé kerülni
     */
    @Override
    public void visit(Layer l) {
        Tracer.getInstance().enterFunction(this, "visit",l);
        result = base;
        Tracer.getInstance().exitFunction();
    }

   

    /**
     * Visszaadja a rétegek egyesítésének eredményét.
     * @return az egyesített réteg
     */
    public ILayer getResult() {
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    @Override
    public List<String> init() {
        List<String> tmp = new ArrayList<>();
        tmp.add("base: " + (base != null ? base.toString() : "null"));
        tmp.add("result: " + (result != null ? result.toString() : "null"));
        return tmp;
    }

    @Override
    public void visit(Stone s) {
        result = new Stone(base);
    }
    
}
