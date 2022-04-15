package edu.ntnu.idatt2001.eirielv.Units;

import edu.ntnu.idatt2001.eirielv.simulation.Unit;


/**
 * InfantryUnit klass represents the infantry unit. The infantry unit has melee attack.
 * @author Eirik Elvestad
 */
public class InfantryUnit extends Unit {

    /**
     * This is the constructor for the InfantryUnit class that defines how an infantry unit should look and its variables
     * @param name   The String name is a short descriptive name of the infantry unit
     * @param health The int health is a value that describes the infantry unit's health
     * @param attack The int attack represents the units weapon
     * @param armor  The int armor is a defence value that protects the infantry unit under attack
     */
    public InfantryUnit(String name, int health, int attack, int armor){
        super(name, health, attack, armor);
    }

    /**
     *This is the construct for InfantryUnit with set variables attack = 15 and armor = 10. This constructor will be used
     * to call Infantry unit with set values attack and armor
     * @param name Ths String name is a short descriptive name of the infantry unit
     * @param health The int health is the value that describes the infantry unit's health
     */
    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    /**
     * {@inheritDoc}
     * The infantry unit attacks with melee, and because of that, the infantry unit gets 2
     * attack bonus to illustrate this
     * @return Returns the attack bonus to the infantry unit
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     *{@inheritDoc}
     * The infantry unit has a low resistance bonus. To illustrate this, the infantry unit gets 1 in resistance bonus
     * @return Returns the resistance bonus to the infantry unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

}
