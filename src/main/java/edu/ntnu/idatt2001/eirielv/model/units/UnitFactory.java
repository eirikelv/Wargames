package edu.ntnu.idatt2001.eirielv.model.units;
import edu.ntnu.idatt2001.eirielv.model.simulation.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class is a factory class for creating units. It founds out what type of unit to make by using the
 * enum class UnitType
 * @author Eirik Elvestad
 */
public class UnitFactory {
    /**
     * createUnit takes in parameters and adds a unit based on the unitType parameter.
     * @param unitType is the type of the unit, represented as UnitType
     * @param unitName is the name of the unit, represented as String
     * @param health is the health value of the unit, represented as an integer
     * @return a new unit, or null if the @param unitType don't match the represented units
     */
    public static Unit createUnit(UnitType unitType, String unitName, int health){
        switch(unitType){
            case CAVALRYUNIT -> {
                return new CavalryUnit(unitName,health);
            }
            case COMMANDERUNIT -> {
                return new CommanderUnit(unitName,health);
            }
            case INFANTRYUNIT -> {
                return new InfantryUnit(unitName, health);
            }
            case RANGEDUNIT -> {
                return new RangedUnit(unitName,health);
            }
            default -> throw new IllegalArgumentException("No such unit");
        }
    }

    /**
     * addDuplicateUnitsAsList makes an arraylist that stores n duplicated units. It goes through a forEach and makes a
     * unit with createUnit method.
     * @param unitType is the type of the unit, represented as a UnitType
     * @param unitName is the name of the unit, represented as a String
     * @param health is the health value of the unit, represented as an Integer
     * @param quantity is the integer that decides how many duplicates it should be made
     * @return an arraylist of the unit duplicates
     */
    public static List<Unit> addDuplicateUnitsAsList(UnitType unitType, String unitName, int health, int quantity){
        if (quantity <= 0) throw new IllegalArgumentException("quantity needs to be 1 or higher");
        List<Unit> units = new ArrayList<>();
        IntStream.range(0, quantity).forEach(run -> units.add(createUnit(unitType,unitName,health)));
        return units;
    }
}
