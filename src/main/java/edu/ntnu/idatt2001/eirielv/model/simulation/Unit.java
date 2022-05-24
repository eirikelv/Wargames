package edu.ntnu.idatt2001.eirielv.model.simulation;

import edu.ntnu.idatt2001.eirielv.model.units.UnitType;

/**
 * This is a unit class that works as a blueprint for all future units and their methods and variables
 * @author Eirik Elvestad
 */
public abstract class Unit {
    private String name;
    private int health;
    private int attack;
    private int armor;
    private UnitType unitType;

    /**
     * this is a constructor for the unit class that defines how a unit should look and its variables
     * @param name The String name is a short descriptive name of the unit
     * @param health The int health is a value that describes the unit's health
     * @param attack The int attack represents the units weapon
     * @param armor The int armor is a defence value that protects the unit under attack
     */
    public Unit(String name, int health, int attack, int armor) throws IllegalArgumentException{
        this.name = name;
            if(this.name.isBlank()) throw new IllegalArgumentException("You need to type in a name");
            if(this.name.isEmpty()) throw new IllegalArgumentException("You need to type in a name");
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
     * @param terrainType is the terrain the units are fighting in
     */
    public void attack(Unit opponent,TerrainType terrainType){
        int newOpponentHealth = (opponent.getHealth()) - (this.attack + this.getAttackBonus(terrainType)) +
                (opponent.getArmor() + opponent.getResistBonus(terrainType));
        opponent.setHealth(newOpponentHealth);
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
     * The method getAttackBonus returns the attackbonus when a unit is attacking an opponent. The
     * attackbonus is also effected by the terrainType
     * @param terrainType is the terrain the units are fighting in
     * @return attackbonus to the unit representet as int
     */
    public abstract int getAttackBonus(TerrainType terrainType);

    /**
     * The method getResistBonus returns the resistbonus of the opponent getting attacked. The
     * resistbonus is also effected by the terrainType
     * @param terrainType is the terrain the units are fighting in
     * @return resistbonus of the opponent getting attacked representet as int
     */
    public abstract int getResistBonus(TerrainType terrainType);

    /**
     * The method setHealth sets the health value of the unit
     * @param health is the health value og the unit represented as int
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * setUnitType sets the unit type of the unit
     * @param unitType the unit type represented as UnitType
     */
    public void setUnitType(UnitType unitType){this.unitType = unitType;}

    /**
     * getUnitType returns the unit type of the unit
     * @return the unit type represented as UnitType
     */
    public UnitType getUnitType(){
        return unitType;
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
