package com.spring.app.utils.mock;

import java.util.List;

import com.spring.app.utils.IPrintable;
import com.spring.app.utils.Tracer;

public class Test1 implements IPrintable {
    int id;
    String prop;
    public Test1(){
        id = 0;
        prop = "property";

    }
    @Override
    public List<String> init() {
        return List.of(
            "id: "+this.hashCode(),
            "prop: "+this.prop
        );
    }

    public void call(){
        Test1 t1 = new Test1();
        Tracer.getInstance().newObject("t1", t1);
        Tracer.getInstance().enterFunction("t1.call2()");
        t1.call2();
        Tracer.getInstance().exitFunction();
    }

    public void call2(){
        Tracer.getInstance().exitFunction();
    }
}
