package com.spring.app.utils.mock;

import java.util.List;

import com.spring.app.utils.Entity;
import com.spring.app.utils.IPrintable;
import com.spring.app.utils.Tracer;

public class Test1 extends Entity implements IPrintable {
    String prop;
    public Test1(){
        super();
        prop = "property";
    }

    @Override
    public List<String> init() {
        return List.of(
            "id: "+id,
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
