package edu.ntnu.idatt2001.eirielv.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents an army of units.
 * @author Eirik Elvestad
 */
public class Army {
    private String name;
    private List<Unit> units;

    /**
     * This is a constructor for the army class where the units ar not implemented and need to be made from scratch
     * @param name
     */
    public Army(String name) {
        this.name = name;
        this.units = new ArrayList<Unit>();
    }

    /**
     *This constructor is based on name and a list of units, where these units does not need to be crated after creating
     * army object
     * @param name
     * @param units
     */
    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
    }

    /**
     *The method getName returns the name of the army, represented as a String
     * @return the name of the army as String
     */
    public String getName() {
        return name;
    }

    /**
     *The method add, adds a unit to the units list.
     * @param unit is a unit based on the Unit class
     */
    public void add(Unit unit){
        if(unit != null){
            this.units.add(unit);
        }
    }

    /**
     *the method addAll adds all units inn to the units list
     * @param units is the list holding units
     */
    public void addAll(List<Unit> units){
        if(units != null){
            for(Unit unit : units){
                if(unit != null){
                    this.units.add(unit);
                }
            }
        }
    }

    /**
     *The method remove, removes a unit from the units list
     * @param unit is a unit based on the Unit class
     */
    public void remove(Unit unit){
        if(unit != null){
            this.units.remove(unit);
        }
    }

    /**
     *The method hasUnits checkes if the list units is not null
     * @return Returns true if units has units inside the list, and returns false if not
     */
    public boolean hasUnits(){
        return units != null;
    }

    /**
     *getAllUnits returns the list units, which contains all units added
     * @return Returns the list units
     */
    public List<Unit> getAllUnits(){
        return units;
    }

    /**
     * The method getRandom gives a random number from 0 to the size of the units list, the random number is represented
     * by x. This number will be the index of the unit that will be returned
     * @return Returns the unit of index x
     */
    public Unit getRandom(){
        Random random = new Random();
        int x = random.nextInt(units.size());
        return units.get(x);
    }

    /**
     * This method checkes if two objects are equal, it also cheks if the name is the same.
     * @param o is the object the user try to find out if equal
     * @return a boolean dependend on if the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     *  this is a hashcode method that creats a hashcode to an unit object based on name and units
     * @return an hashcode as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
