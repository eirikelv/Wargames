package edu.ntnu.idatt2001.eirielv.Units;




/**
 * The CommanderUnit extends CavalryUnit, and uses the same bonuses ass the cavalry unit. The CommanderUnit class
 * represents the commander unit. The commander unit has a charge attack and melee attack.
 * @author Eirik Elvestad
 */
public class CommanderUnit extends CavalryUnit{

    private int attackedCount = 0; // how many times the unit has been attacked

    /**
     * This is the constructor for the CommanderUnit class that defines how a commander unit should look and its variables
     * @param name   The String name is a short descriptive name of the commander unit
     * @param health The int health is a value that describes the commander unit's health
     * @param attack The int attack represents the commander units weapon
     * @param armor  The int armor is a defence value that protects the commander unit under attack
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     *This is the construct for commander Unit with set variables attack = 25 and armor = 15. This constructor will be
     * used to call cavalry unit with set values attack and armor.
     * @param name Ths String name is a short descriptive name of the commander unit
     * @param health The int health is the value that describes the commander unit's health
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }

    /**
     * The method getAttackBonus returns the attack bonus when a unit is attacking an opponent
     * The commander unit has an advantage because it has extra damage with first attack, and because of that, the
     * commander unit gets 6 in attack bonus with the first attack. After that the attack bonus equals 2
     * attack bonus to illustrate this
     * @return Returns the attack bonus to the commander unit
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 2;
        if(attackedCount < 1) attackBonus = 6;

        this.attackedCount +=1;
        return attackBonus;
    }

    /**
     *The method getResistBonus returns the resist bonus of the opponent getting attacked. The commander unit has a low
     * resistance bonus, to illustrate this the commanderunit gets 1 in resistance bonus
     * @return Returns the resistancebonus of the Commander
     */
    @Override
    public int getResistBonus() {

        return 1;
    }
}
