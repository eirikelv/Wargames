package edu.ntnu.idatt2001.eirielv.simulation;

/**
 * CavalryUnit klass represents the cavalry unit. The cavalry unit has a charge attack and melee attack.
 * @author Eirik Elvestad
 */
public class CavalryUnit extends Unit{

    private int attackedCount = 0; // how many times the unit has been attacked

    /**
     * This is the constructor for the CavalryUnit class that defines how a cavalry unit should look and its variables
     * @param name   The String name is a short descriptive name of the cavalry unit
     * @param health The int health is a value that describes the cavalry unit's health
     * @param attack The int attack represents the units weapon
     * @param armor  The int armor is a defence value that protects the cavalry unit under attack
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     *This is the construct for cavalry Unit with set variables attack = 20 and armor = 12. This constructor will be used
     * to call cavalry unit with set values attack and armor
     * @param name Ths String name is a short descriptive name of the cavalry unit
     * @param health The int health is the value that describes the cavalry unit's health
     */
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }

    /**
     * {@inheritDoc}
     * The cavalry unit has an advantage because it has extra damage with first attack, and because of that, the cavalry
     * unit gets 6 in attack bonus with the first attack. After that the attackbonus equals 2.
     * attack bonus to illustrate this
     * @return Returns the attack bonus to the cavalry unit
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 2;
        if(attackedCount < 1) attackBonus = 6;

        attackedCount ++;
        return attackBonus;
    }

    /**
     *{@inheritDoc}
     * The cavalry unit has a low resistance bonus. To illustrate this, the cavalry unit gets 1 in resistance bonus
     * @return Returns the resistance bonus to the cavalry unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

}
