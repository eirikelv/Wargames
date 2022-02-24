package edu.ntnu.idatt2001.eirielv.simulation;

/**
 * Range Unit class represents the ranged unit. The ranged unit can attack from distance and has a resistbonus when
 * attacked
 * @author Eirik Elvestad
 */
public class RangedUnit extends Unit{

    private int attackedCount = 0; // how many times the unit has been attacked


    /**
     * This is the constructor for the RangedUnit class that defines how a unit should look and its variables
     * @param name   The String name is a short descriptive name of the ranged unit
     * @param health The int health is a value that describes the ranged unit's health
     * @param attack The int attack represents the units weapon
     * @param armor  The int armor is a defence value that protects the ranged unit under attack
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     *This is the construktør for RangedUnit with set variables attack = 15 and armor = 8. This constructor will be used
     * to call RangedUnit with set values
     * @param name Ths String name is a short descriptive name of the ranged unit
     * @param health The int healt is the value that describes the reanged unit's health
     */
    public RangedUnit(String name, int health) {
        super(name,health, 15, 8);
    }

    /**
     * {@inheritDoc}
     * The ranged unit has an advantage because it can attack from range, and because of that, the ranged unit gets 3
     * attack bonus to illustrate this
     * @return Returns the attack bonus to the ranged unit
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     *{@inheritDoc}
     * Then a range unit is first attack, the resist bonus will defend the ranged unit for 6 attack points, the second
     * time it will defend the ranged unit for 4 attack points, after this the resist bonus will defend ranged unit by 2
     * resist bonuses.
     * @return Returns the resistbonus
     */
    @Override
    public int getResistBonus() {
        int resistBonus = 6 - (2 * this.attackedCount);

        if(resistBonus <= 0) resistBonus = 2;

        this.attackedCount += 1;
        return resistBonus;
    }
}
