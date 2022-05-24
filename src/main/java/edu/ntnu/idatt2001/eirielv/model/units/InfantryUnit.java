package edu.ntnu.idatt2001.eirielv.model.units;

import edu.ntnu.idatt2001.eirielv.model.simulation.TerrainType;
import edu.ntnu.idatt2001.eirielv.model.simulation.Unit;


/**
 * InfantryUnit class represents the infantry unit. The infantry unit has melee attack.
 * @author Eirik Elvestad
 */
public class InfantryUnit extends Unit {

    /**
     * This is the constructor for the InfantryUnit class that defines how an infantry unit should look and its variables
     * It also set the UnitType to InfantryUnit
     * @param name   The String name is a short descriptive name of the infantry unit
     * @param health The int health is a value that describes the infantry unit's health
     * @param attack The int attack represents the units weapon
     * @param armor  The int armor is a defence value that protects the infantry unit under attack
     */
    public InfantryUnit(String name, int health, int attack, int armor){
        super(name, health, attack, armor);
        setUnitType(UnitType.INFANTRYUNIT);
    }

    /**
     * This is the construct for InfantryUnit with set variables attack = 15 and armor = 10. This constructor will be used
     * It also set the UnitType to InfantryUnit
     * to call Infantry unit with set values attack and armor
     * @param name Ths String name is a short descriptive name of the infantry unit
     * @param health The int health is the value that describes the infantry unit's health
     * @TODO javaDoc unitType
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
        setUnitType(UnitType.INFANTRYUNIT);
    }

    /**
     * {@inheritDoc}
     * The infantry unit attacks with melee, and because of that, the infantry unit gets 2
     * attack bonus to illustrate this
     * The infantry unit also have a benefit in forest, where it gets extra attack bonus.
     * @param terrainType is the terrain the units are fighting in
     * @return Returns the attack bonus to the infantry unit
     */
    @Override
    public int getAttackBonus(TerrainType terrainType) {
        if(terrainType == TerrainType.FOREST) return 2+3;
        return 2;
    }

    /**
     *{@inheritDoc}
     * The infantry unit has a low resistance bonus. To illustrate this, the infantry unit gets 1 in resistance bonus
     * The infantry unit also have a benefit in forest, where it gets extra resist bonus.
     * @param terrainType is the terrain the units are fighting in
     * @return Returns the resistance bonus to the infantry unit
     */
    @Override
    public int getResistBonus(TerrainType terrainType) {
        if(terrainType == TerrainType.FOREST) return 1+2;
        return 1;
    }

}
