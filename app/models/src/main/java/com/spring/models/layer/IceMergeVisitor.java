package com.spring.models.layer;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Megvalósítja a jeges útfelszín és a rá kerülő út takarók interakcióját.
 */
public class IceMergeVisitor extends Entity implements ILayerVisitor{
    /**
     * Tárolja az alap jégréteget.
     */
    private Ice base;

    /**
     * Az alap jégréteg és a rákerülő másik réteg együttes eredménye.
     */
    private ILayer result;

    public IceMergeVisitor(Ice base){
        this.base = base;
        this.result = base;
    }

    /**
     * Ha jeges útra hó kerül, akkor is jég marad.
     * @param s a jégre kerülő hó
     */
    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ha jeges útra jég kerül, akkor is jég marad.
     * @param a jégre kerülő jég
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        result = base;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ha jeges útra semmi nem kerül, akkor is jég marad.
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
        tmp.add(base != null ? base.toString() : "null");
        tmp.add(result != null ? result.toString() : "null");
        return tmp;
    }

    @Override
    public void visit(Stone s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }
}
