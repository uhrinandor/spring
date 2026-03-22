package com.spring.app.utils;

import java.util.List;

public class Tracer {
    private static Tracer instance;
    private int indentationLevel = 0;
    private static final String INDENT = "  ";
    private Tracer(){}

    public static Tracer getInstance(){
        if(instance == null){
            instance = new Tracer();
        }
        return instance;
    }

    // Minden teszt előtt érdemes meghívni, hogy az előzőben tesztelt hívás ne okozzon gondot.
    public void reset(){
        indentationLevel = 0;
    }
    
    public void info(String message){
        System.out.println("[INFO] "+message);
    }

    public void input(String message){
        System.out.println("[?] "+message);
    }

    // Függvény belépés jelzése az indítás ELŐTT, pl:
    // Tracer.getInstance().enterFunction("user.login()");
    // user.login();
    public void enterFunction(String message){
        for(int i=0; i<indentationLevel; i++){
            System.out.print(INDENT);
        }
        System.out.println("[->] " + message);
        indentationLevel++;
    }

    // Indentálás visszavonása, fgv-ből való kilépés jelzése, user.login() végén:
    // Tracer.getInstance().exitFunction();
    public void exitFunction(){
        indentationLevel--;
        for(int i=0; i<indentationLevel; i++){
            System.out.print(INDENT);
        }
        System.out.println("[<-]");
    }

    // Ha új objektumot inicializáltunk ezzel tudjuk jelezni, példa:
    // Inicializálás így:
    // User user = new User(); // implements IPrintable
    // Tracer.getInstance().newObject("user", user);
    public void newObject(String variableName, IPrintable object){
        for(int i=0; i<indentationLevel; i++){
            System.out.print(INDENT);
        }
        System.out.println("[+] "+ object.getClass().getSimpleName() + " "+ variableName);
        List<String> properties = object.init();
        for(String property: properties){
            for(int i=0; i<indentationLevel+1; i++){
                System.out.print(INDENT);
            }
            System.out.println("- "+property);
        }
    }

}
