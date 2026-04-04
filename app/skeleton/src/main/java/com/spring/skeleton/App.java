package com.spring.skeleton;

import com.spring.models.utils.Tracer;


public class App 
{
    public static void main( String[] args )
    {
        Tracer.enableSkeletonMode();
        Skeleton skeleton = new Skeleton();
        skeleton.start();
    }
}
