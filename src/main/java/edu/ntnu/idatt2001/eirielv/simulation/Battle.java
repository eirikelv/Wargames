package edu.ntnu.idatt2001.eirielv.simulation;

/**
 * This class simulates a battle of 2 armies, armyOne and armyTwo
 * @author Eirik Elvestad
 */
public class Battle {
    private Army armyOne;
    private Army armyTwo;

    /**
     * This constructor takes Army armyOne and Army armyTwo ass attributes
     * @param armyOne is the object holding an army with units
     * @param armyTwo is the object holding an army with units
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * the method simulate, simulates an attack between to armies. If one unit gets 0 health
     * it is dead, and gets removed from the army who ones the unit.
     * @return the winning army
     */
    public Army simulate(){
        while(armyOne.hasUnits() && armyTwo.hasUnits()){
            Unit unit1 = armyOne.getRandom();
            Unit unit2 = armyTwo.getRandom();

            unit1.attack(unit2);
            if(unit2.getHealth() <= 0){
                armyTwo.remove(unit2);
                if(armyTwo.hasUnits()){
                    unit2 = armyTwo.getRandom();
                }
                else break;
            }

            unit2.attack(unit1);
            if(unit1.getHealth() <= 0) armyOne.remove(unit1);

        }
        if(armyOne.hasUnits()){
            return armyOne ;
        }
        else{
            return armyTwo;
        }
    }

    /**
     * Army getArmyOne returns one army
     * @return one army
     */
    public Army getArmyOne(){
        return armyOne;
    }

    /**
     * Army getArmyTwo returns other army
     * @return one army
     */
    public Army getArmyTwo(){
        return armyTwo;
    }

    @Override
    public String toString() {
        return armyOne + "VS" + armyTwo;
    }
}
