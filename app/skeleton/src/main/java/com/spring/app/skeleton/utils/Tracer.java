package com.spring.app.skeleton.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Tracer {
    private static Tracer instance;
    private int indentationLevel = 0;
    private static final String INDENT = "    ";
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final PrintStream tracerStream = new PrintStream(buffer);
    private Tracer(){}

    public void flush(){
        System.out.print(buffer.toString());
        tracerStream.flush();
        buffer.reset();
    }

    public static Tracer getInstance(){
        if(instance == null){
            instance = new Tracer();
        }
        return instance;
    }

    public void printIndent(int increase){
        for(int i=0; i<indentationLevel + increase; i++){
            tracerStream.print(INDENT);
        }
    }
    public void printIndent(){
        printIndent(0);
    }
    
    public void info(String message){
        tracerStream.print("[INFO] "+message);
    }

    public void input(String message){
        System.out.print("[?] "+message+": ");
    }

    public int askInt(String message){
        input(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public boolean askBool(String message){
        input(message+" (true/false)");
        Scanner sc = new Scanner(System.in);
        return sc.nextBoolean();
    }

    

    // Függvény belépés jelzése az indítás ELŐTT, pl:
    // Tracer.getInstance().enterFunction("user.login()");
    // user.login();
    // Paraméter esetén: Tracer.getInstance().enterFunction("user.login("+"username"+", "+"password"+")");
    // Ha osztály akkor Tracer.getInstance().enterFunction("user.login(User@"+ user.getId()  +")");
    // User osztály getId-ja az Entity osztályból származik, ez egy absztrakt osztály, mindenkinek ebből kell származnia
    public void enterFunction(String message){
        printIndent();
        tracerStream.print("[->] " + message);
        indentationLevel++;
    }

    // Indentálás visszavonása, fgv-ből való kilépés jelzése, user.login() végén:
    // Tracer.getInstance().exitFunction();
    public void exitFunction(String returnValue){
        indentationLevel--;
        printIndent();
        tracerStream.print("[<-] " + returnValue);
    }

    // Ha új objektumot inicializáltunk ezzel tudjuk jelezni, példa:
    // Inicializálás így:
    // User user = new User(); // implements IPrintable
    // Tracer.getInstance().newObject("user", user);
    public void newObject(String variableName, Entity object){
        printIndent();
        tracerStream.print("[+] "+ object.getClass().getSimpleName() + "@" + object.getId() + " " + variableName);
        List<String> properties = object.init();
        for(String property: properties){
            printIndent(1);
            tracerStream.print("- "+property);
        }
    }

}
