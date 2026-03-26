package com.spring.app.utils;

import org.junit.Test;

import com.spring.app.utils.mock.Test1;


public class TracerTest {

    @Test
    public void testNewObject(){
        Tracer tracer = Tracer.getInstance();

        Test1 testObj = new Test1();
        tracer.newObject("testObj", testObj);
    }

    @Test
    public void testEnterExitFunction(){
        Tracer tracer = Tracer.getInstance();
        Test1 testObj = new Test1();
        
        tracer.enterFunction("testObj.call()");
        testObj.call();
    }

}
