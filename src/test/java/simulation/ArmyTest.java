package simulation;

import edu.ntnu.idatt2001.eirielv.Units.*;
import edu.ntnu.idatt2001.eirielv.simulation.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void the_constructor_gets_expected_name_value() {
        //arrange
        Army army = new Army("Army1");
        String expectedValue = "Army1";
        //assert
        assertEquals(expectedValue, army.getName());
    }

    @Nested
    class the_constructor_gets_invalid_name_value {


        @Test
        void name_attribute_gets_empty_name_value() {
            assertThrows(IllegalArgumentException.class, () -> {
                Army army = new Army("");
            });
        }

        @Test
        void name_attribute_gets_blank_name_value() {
            assertThrows(IllegalArgumentException.class, () -> {
                Army army = new Army(" ");
            });
        }
    }

    @Test
    void constructor_with_name_and_array_attribute_ads_right_value(){
        //Arrange:
        String name = "Army";
        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 100));
        units.add(new RangedUnit("Archer", 100));
        //Act
        Army army = new Army(name, units);
        //Assert
        assertEquals(army.getAllUnits().size(),units.size());
    }

    @Test
    void getName_gets_returns_expected_value() {
        //arrange
        Army army = new Army("Army1");
        String expectedValue = "Army1";
        //assert
        assertEquals(expectedValue, army.getName());
    }

    @Test
    void getAllUnits_returns_all_units() {
        //Arrange
        String name = "Army";
        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 100));
        units.add(new RangedUnit("Archer", 100));
        //Act
        Army army = new Army(name);
        army.addAll(units);
        //Assert
        assertEquals(units.size(), army.getAllUnits().size());
    }

    @Test
    void add_method_returns_correct_information() {
        //Arrange
        String name = "Army";
        Unit knight = new CavalryUnit("Knight", 100);
        Unit archer = new RangedUnit("Archer", 100);
        //Act
        Army army = new Army(name);
        army.add(knight);
        army.add(archer);
        //Assert
        assertEquals(knight, army.getAllUnits().get(0));
        assertEquals(archer, army.getAllUnits().get(1));
    }

    @Test
    void addAll_method_adsAll_units() {
        //Arrange:
        String name = "Army";
        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 100));
        units.add(new RangedUnit("Archer", 100));
        //Act
        Army army = new Army(name);
        army.addAll(units);
        //Assert
        assertTrue(army.getAllUnits().containsAll(units));
    }

    @Test
    void remove_method_removes_one_unit() {
        //Arrange:
        String name = "Army";
        Unit knight = new CavalryUnit("Knight", 100);
        Unit archer = new RangedUnit("Archer", 100);
        //Act
        Army army = new Army(name);
        army.add(knight);
        army.add(archer);
        army.remove(archer);
        int expectedValue = 1;
        //Assert
        assertEquals(army.getAllUnits().size(), expectedValue);
    }

    @Test
    void hasUnits_returns_true_if_it_has_units() {
        //Arrange
        String name = "Army";
        Unit knight = new CavalryUnit("Knight", 100);
        Unit archer = new RangedUnit("Archer", 100);
        //Act
        Army army = new Army(name);
        army.add(knight);
        army.add(archer);
        //Assert
        assertTrue(army.hasUnits());
    }

    @Test
    void hasUnits_returns_false_if_it_has_no_units() {
        //Arrange
        String name = "Army";
        //Act
        Army army = new Army(name);
        //Assert
        assertFalse(army.hasUnits());
    }

    @Test
    void getRandom_returns_a_unit() {
        //Arrange
        String name = "Army";
        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 100));
        units.add(new RangedUnit("Archer", 100));
        //Act
        Army army = new Army(name);
        army.addAll(units);
        //Assert
        assertTrue(units.contains(army.getRandom()));

    }

    @Nested
    class getters_returns_expected_information {

        @Test
        public void getInfantryUnit_returns_expected_information() {
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider1", 30));
            units.add(new InfantryUnit("Solider2", 30));
            units.add(new InfantryUnit("Solider3", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> expected = new ArrayList<>();
            expected.add(new InfantryUnit("Solider1", 30));
            expected.add(new InfantryUnit("Solider2", 30));
            expected.add(new InfantryUnit("Solider3", 30));

            List<Unit> actual = new ArrayList<>();
            actual.add(armyOne.getInfantryUnits().get(0));
            actual.add(armyOne.getInfantryUnits().get(1));
            actual.add(armyOne.getInfantryUnits().get(2));
            assertTrue(expected.toString().equals(actual.toString()));
        }

        @Test
        public void getRangedUnit_returns_expected_information() {
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer1", 20));
            units.add(new RangedUnit("Archer2", 20));
            units.add(new RangedUnit("Archer3", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> expected = new ArrayList<>();
            expected.add(new RangedUnit("Archer1", 20));
            expected.add(new RangedUnit("Archer2", 20));
            expected.add(new RangedUnit("Archer3", 20));

            List<Unit> actual = new ArrayList<>();
            actual.add(armyOne.getRangedUnits().get(0));
            actual.add(armyOne.getRangedUnits().get(1));
            actual.add(armyOne.getRangedUnits().get(2));
            assertEquals(expected.toString(), actual.toString());
        }

        @Test
        public void getCavalryUnits_returns_expected_information() {
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight1", 15));
            units.add(new CavalryUnit("Knight2", 15));
            units.add(new CavalryUnit("Knight3", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> expected = new ArrayList<>();
            expected.add(new CavalryUnit("Knight1", 15));
            expected.add(new CavalryUnit("Knight2", 15));
            expected.add(new CavalryUnit("Knight3", 15));

            List<Unit> actual = new ArrayList<>();
            actual.add(armyOne.getCavalryUnits().get(0));
            actual.add(armyOne.getCavalryUnits().get(1));
            actual.add(armyOne.getCavalryUnits().get(2));
            assertEquals(expected.toString(), actual.toString());
        }

        @Test
        public void getCommanderUnits_returns_expected_information() {
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex1", 40));
            units.add(new CommanderUnit("Rex2", 40));
            units.add(new CommanderUnit("Rex3", 40));
            armyOne.addAll(units);

            List<Unit> expected = new ArrayList<>();
            expected.add(new CommanderUnit("Rex1", 40));
            expected.add(new CommanderUnit("Rex2", 40));
            expected.add(new CommanderUnit("Rex3", 40));

            List<Unit> actual = new ArrayList<>();
            actual.add(armyOne.getCommanderUnits().get(0));
            actual.add(armyOne.getCommanderUnits().get(1));
            actual.add(armyOne.getCommanderUnits().get(2));
            assertEquals(expected.toString(), actual.toString());
        }
    }

        @Test
    void testEquals_returns_false_if_to_objects_is_not_equal() {
        //Arrange
        Army army1 = new Army("Army1");
        Army army2 = new Army("Army2");
        //Act
        boolean testEquals = army1.equals(army2);
        //Assert
        assertFalse(testEquals);
    }

    @Test
    void testEquals_returns_true_if_to_objects_is_equal() {
        //Arrange
        Army army1 = new Army("Army1");
        Army army2 = new Army("Army1");
        //Act
        boolean testEquals = army1.equals(army2);
        //Assert
        assertTrue(testEquals);                                      
    }

    @Test
    void testHashCode() {
        //Arrange
        Army army = new Army("Army")  ;
       //Act
        assertNotNull(army.hashCode());
    }
}