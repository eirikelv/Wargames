package edu.ntnu.idatt2001.eirielv.simulation;

/**
 * This is a unit class that works as a blueprint for all future units and their methods and variables
 * @author Eirik Elvestad
 */

public abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armor;

    /**
     * this is a constructor for the unit class that defines how a unit should look and its variables
     * @param name The String name is a short descriptive name of the unit
     * @param health The int health is a value that describes the unit's health
     * @param attack The int attack represents the units weapon
     * @param armor The int armor is a defence value that protects the unit under attack
     */
    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException{
        this.name = name;
            if(this.name.isBlank()) throw new IllegalArgumentException("You need to type inn a name");
        this.health = health;
            if(this.health < 0) throw new IllegalArgumentException("Health is lower than 0, not valid");
        this.attack = attack;
            if(this.attack < 0) throw new IllegalArgumentException("Attack value is lower than 0, not valid");
        this.armor = armor;
            if(this.armor < 0) throw new IllegalArgumentException("Armor value is lower than 0, not valid");

    }

    /**
     * This method simulates an attack on an opponent. When an opponent is attacked, then the health of the opponent is
     * subtracted with the attack points and AttackBonus, then the armor points and ResistBonus is added with the health.
     * @param opponent The Unit opponent is the opponent receiving the attack
     */
    public void attack(Unit opponent){
        int newOpponentHealth = opponent.getHealth() - (this.attack + this.getAttackBonus()) + (opponent.getArmor() + opponent.getResistBonus());
    }

    /**
     * getName returns name of the unit
     * @return name of the unit represented as String
     */
    public String getName() {
        return name;
    }

    /**
     * The method getHealth returns the health of the unit
     * @return health of the unit represented as int
     */
    public int getHealth() {
        return health;
    }

    /**
     * The method getAttack returns the attackvalue given to the unit
     * @return the attackvalue given to the unit represented as int
     */
    public int getAttack() {
        return attack;
    }

    /**
     * The method getArmor returns the armorvalue of the unit
     * @return the armorvalue of the unit represented as int
     */
    public int getArmor() {
        return armor;
    }

    /**
     * The method getAttackBonus returns the attackbonus when a unit is attacking an opponent
     * @return attackbonus to the unit representet as int
     */
    public abstract int getAttackBonus();

    /**
     * The method getResistBonus returns the resistbonus of the opponent getting attacked
     * @return resistbonus of the opponent getting attacked representet as int
     */
    public abstract int getResistBonus();

    /**
     * The method setHealth sets the healthvalue of the unit
     * @param health is the healthvalue og the unit represented as int
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * a toString method that prints out the statistics of a unit
     * @return string of the units statistic by name health attack and armor
     */
    @Override
    public String toString() {
        return "Unit name: " + name + " , Health: " + health + ", Attack damage: " + attack + ", Armor: " + armor;
    }
}
