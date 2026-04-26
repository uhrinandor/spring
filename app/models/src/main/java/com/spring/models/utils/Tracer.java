package com.spring.models.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Tracer {
    private static Tracer instance;
    private int indentationLevel = 0;
    private static final String INDENT = "    ";
    private static boolean skeletonMode = false;
    private static boolean deterministicMode = false;
    private static PrintStream stream = System.out;
    private static InputStream inputStream = System.in;
    private Tracer(){}


    /**
     * Megváltoztatja a kimeneti és bemeneti streamet, hogy a tesztelés során könnyebben lehessen szimulálni a bemenetet és elfogni a kimenetet
     * @param output A kimeneti stream, ahova az üzeneteket írjuk
     * @param input A bemeneti stream, ahonnan a bemenetet olvassuk
     */
    public static void changeStream(PrintStream output, InputStream input){
        stream = output;
        inputStream = input;
    }

    /**
     * Engedélyezzük a szkeleton módot
     */
    public static void enableSkeletonMode(){
        skeletonMode = true;
    }

    /**
     * 
     */
    public static void disableSkeletonMode(){
        skeletonMode = false;
    }

    public static void changeDeterministicMode(boolean value){
        deterministicMode = value;
    }

    public static boolean isDeterministicMode(){
        return deterministicMode;
    }

    /**
     * Singleton instance-t adja vissza
     * @return Tracer instance
     */
    public static Tracer getInstance(){
        if(instance == null){
            instance = new Tracer();
        }
        return instance;
    }

    /**
     * Hiba esetén reseteli az indentálást
     */
    public static void resetIndent(){
        instance.indentationLevel = 0;
    }

    /**
     * Indentálásért felelős függvény
     * @param increase Mennyivel többre húzza be a jelenlegin felül
     */
    private void printIndent(int increase){
        for(int i=0; i<indentationLevel + increase; i++){
            stream.print(INDENT);
        }
    }
    /**
     * Indentálásért felelős függvény
     */
    private void printIndent(){
        printIndent(0);
    }

    /**
     * Kiírja a hibaüzenetet konzolra
     * @param message A hibaüzenet
     */
    public void error(String message){
        printIndent();
        stream.println("[ERR] "+message);
    }
    
    /**
     * Információs üzenet kiírása a konzolra
     * @param message Az üzenet, amit ki szeretnénk írni    
     */
    public void info(String message){
        printIndent();
        stream.println("[INFO] "+message);
    }
    /**
     * Bement bekérés kiírása a konzolra
     * @param message Az üzenet, amit ki szeretnénk írni    
     */
    public void input(String message){
        printIndent();
        stream.print("[?] "+message+": ");
    }

    /**
     * Int típusú bemenet bekérés
     * @param message Az üzenet, amit ki szeretnénk írni    
     */
    public int askInt(String message, int variable){
        if(!skeletonMode) return variable;
        input(message);
        Scanner sc = new Scanner(inputStream);
        return sc.nextInt();
    }

    /**
     * String típusú bemenet bekérés, nincs hatással a Skeleton mód rá
     * @param message Az üzenet, amit ki szeretnénk írni    
     */
    public String askString(String message){
        input(message);
        Scanner sc = new Scanner(inputStream);
        return sc.nextLine().trim();
    }

     /**
     * Boolean típusú bemenet bekérés
     * @param message Az üzenet, amit ki szeretnénk írni    
     */
    public boolean askBool(String message, boolean variable){
        if(!skeletonMode) return variable;
        input(message+" (true/false)");
        Scanner sc = new Scanner(inputStream);
        return sc.nextBoolean();
    }  
    
    public boolean askDeterministic(String message){
        input(message+" (true/false)");
        Scanner sc = new Scanner(inputStream);
        return sc.nextBoolean();
    }

     /**
     * Függvényhívás előtt meghívandó, base az amin hívni fogjuk a hívást, ezt csak Entity típusokra követjük le, functionName hogy mit hívunk, Object... params pedig további listában megadhatjuk hogy milyen bemenő értékei vannak.
     * Növeli az indentálást
     * @param base Az Entity, amin a függvényt hívjuk
     * @param functionName A függvény neve
     * @param params A függvény paraméterei
     */
    public void enterFunction(IEntity base, String functionName, Object... params){
        if(!skeletonMode)  return;
        printIndent();
        stream.print(String.format("[->] %s.%s(", base, functionName));
        for(int i=0; i<params.length; i++){
            stream.print(params[i]);
            if(i < params.length - 1){
                stream.print(", ");
            }
        }
        stream.println(")");
        indentationLevel++;
    }

    /**
     * Függvénykilépés végén meghívandó, csökkenti az indentálást
     * @param returnValue a visszatérési érték, ha van
     */
    public void exitFunction(Object returnValue){
        if(!skeletonMode) return;
        indentationLevel--;
        printIndent();
        stream.println("[<-] " + returnValue);
    }

      /**
     * Függvénykilépés végén meghívandó void típus esetén, csökkenti az indentálást
     */
    public void exitFunction(){
        if(!skeletonMode) return;
        exitFunction("");
    }

    /**
     * Új objektum létrehozásakor meghívandó, kiírja az objektum típusát és azonosítóját
     * valamint, ha vannak attribútumai vagy asszociáció is kiíratjuk
     * Külön implementálandó az Entity osztályból
     * @param object Az objektum, amit létrehozunk, csak Entity típusokat követjük le
     */
    public void newObject(IEntity object){
        printIndent();
        stream.println("[+] "+ object);
        List<String> properties = object.init();
        for(String property: properties){
            printIndent(1);
            stream.println("- "+property);
        }
    }

    public void enterFunction(IEntity base, String functionName){
        if(!skeletonMode) return;
        printIndent();
        stream.println(String.format("[->] %s.%s()", base, functionName));
        indentationLevel++;
    }


    public static void hide(){
        stream = new PrintStream(new ByteArrayOutputStream());
    }

    public static void show(){
        stream = System.out;
    }
}
