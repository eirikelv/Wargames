package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;

/**
 * Singleton.java is a class that makes information pass from one controller to another.
 * This method is retrieved from GeeksForGeeks, 21.05.2022 from @
 * <a href="https://www.geeksforgeeks.org/singleton-design-pattern/">GeeksForGeeks</a>
 */
public class Singleton {
    private Army army1;
    private Army army2;


    private static Singleton obj;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (obj==null)
            obj = new Singleton();
        return obj;
    }

    public String getArmyName1() {
        return army1.getName();
    }

    public String getArmyName2() {
        return army2.getName();
    }

    public Army getArmy1(){
        return army1;
    }

    public Army getArmy2(){
        return army2;
    }

    public void setArmy1(Army army1){
        this.army1 = army1;
    }

    public void setArmy2(Army army2){
        this.army2 = army2;
    }
}
