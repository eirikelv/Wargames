package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.model.simulation.Army;
import javafx.scene.control.Button;

/**
 * SimulatorSingleton.java is a class that makes information pass from one controller to another.
 * This method is retrieved from GeeksForGeeks, 21.05.2022 from
 * <a href="https://www.geeksforgeeks.org/singleton-design-pattern/">GeeksForGeeks</a>
 */
public class SimulatorSingleton {
    private Army army1;
    private Army army2;
    private Button button;
    private static SimulatorSingleton obj;

    /**
     * this is the empty constructor for the SimulatorSingleton class
     */
    private SimulatorSingleton() {
    }

    /**
     * Method for getInstance of singleton, this makes it possible to pass singletons
     * @return the singleton object
     */
    public static SimulatorSingleton getInstance() {
        if (obj==null)
            obj = new SimulatorSingleton();
        return obj;
    }

    /**
     * getArmyName1 returns the name of army1
     * @return name of army1 represented ass String
     */
    public String getArmyName1() {
        return army1.getName();
    }

    /**
     * getArmyName2 returns the name of army2
     * @return name of army2 represented ass String
     */
    public String getArmyName2() {
        return army2.getName();
    }

    /**
     * getArmy1 returns  army1
     * @return army1 represented ass Army
     */
    public Army getArmy1(){
        return army1;
    }

    /**
     * getArmy2 returns  army2
     * @return army2 represented ass Army
     */
    public Army getArmy2(){
        return army2;
    }

    /**
     * setArmy1 takes in an army, and sets it as the new army1
     * @param army1 is the new army1
     */
    public void setArmy1(Army army1){
        this.army1 = army1;
    }

    /**
     * setArmy2 takes in an army, and sets it as the new army2
     * @param army2 is the new army2
     */
    public void setArmy2(Army army2){
        this.army2 = army2;
    }

    /**
     * getButton is used to store a button to pass input through scenes, and this method gets the
     * stored button
     * @return the stored button, represented ass button
     */
    public Button getButton() {
        return button;
    }

    /**
     * setButton sets the new button to store
     * @param button the new button, represented ass button
     */
    public void setButton(Button button) {
        this.button = button;
    }

}
