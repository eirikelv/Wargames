package edu.ntnu.idatt2001.eirielv.model.simulation;

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
     * The simulate method, simulates an attack between to armies in a terrain. If one unit gets 0 health
     * it is dead, and gets removed from the army who ones the unit.
     * @param terrainType is the chosen terrainType the armies battles in
     * @return the winning army, represented ass Army
     */
    public Army simulate(TerrainType terrainType){
        while(armyOne.hasUnits() && armyTwo.hasUnits()){
            Unit unit1 = armyOne.getRandom();
            Unit unit2 = armyTwo.getRandom();

            unit1.attack(unit2,terrainType);
            if(unit2.getHealth() <= 0){
                armyTwo.remove(unit2);
                if(armyTwo.hasUnits()){
                    unit2 = armyTwo.getRandom();
                }
                else break;
            }
            unit2.attack(unit1,terrainType);
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
     * The singleSimulate method simulates on fight between one unit from army1 and one army from army2
     * in a chosen TerrainType
     * @param terrainType is the chosen terrainType the armies battles in
     */
    public void singleSimulate(TerrainType terrainType){
        Unit unit1 = armyOne.getRandom();
        Unit unit2 = armyTwo.getRandom();

        unit1.attack(unit2,terrainType);
        if(unit2.getHealth() <= 0){
            armyTwo.remove(unit2);
            if(armyTwo.hasUnits()){
                unit2 = armyTwo.getRandom();
            }
        }
        unit2.attack(unit1,terrainType);
        if(unit1.getHealth() <= 0) armyOne.remove(unit1);

    }

    /**
     * Army getArmyOne returns armyOne
     * @return armyOne represented as Army
     */
    public Army getArmyOne(){
        return armyOne;
    }

    /**
     * Army getArmyTwo returns armyTwo
     * @return armyTwo represented as Army
     */
    public Army getArmyTwo(){
        return armyTwo;
    }

    @Override
    public String toString() {
        return armyOne.getName() + " VS " + armyTwo.getName();
    }
}
