package edu.ntnu.idatt2001.eirielv.controller;

/**
 * Singleton.java is a class that makes information pass from one controller to another.
 * This method is retrieved from GeeksForGeeks, 21.05.2022 from @
 * <a href="https://www.geeksforgeeks.org/singleton-design-pattern/">GeeksForGeeks</a>
 */
public class Singleton {
    private String armyName1;
    private String armyName2;

    private static Singleton obj;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (obj==null)
            obj = new Singleton();
        return obj;
    }

    public String getArmyName1() {
        return armyName1;
    }

    public String getArmyName2() {
        return armyName2;
    }

    public void setArmyName1(String armyName1) {
        this.armyName1 = armyName1;
    }

    public void setArmyName2(String armyName2) {
        this.armyName2 = armyName2;
    }
}
