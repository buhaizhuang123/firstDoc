package com.bu.firstdoc.Invoke;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author haizhuangbu
 * @date 9:38 上午 2022/2/17
 * @mark InvokeDynamicTest
 */
public class InvokeDynamicTest {


    public static void main(String[] args) {

    }

    public static void testMethod(String s){
        System.out.println("hello World:"+s);
    }

    public static CallSite boootStrapMethod(MethodHandles.Lookup lookup, String name, MethodType methodType) throws NoSuchMethodException, IllegalAccessException {

        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name,methodType));

    }

    public static MethodType MT_BootstrapMethod(){
        return null;
    }



}
