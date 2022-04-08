package edu.ntnu.idatt2001.eirielv.simulation;

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
            try {
                //arrange
                Army army = new Army("");
                //act
            } catch (IllegalArgumentException e) {
                //assert
                assertTrue(true);
            }
        }

        @Test
        void name_attribute_gets_blank_name_value() {
            try {
                //arrange
                Army army = new Army(" ");
                //act
            } catch (IllegalArgumentException e) {
                //assert
                assertTrue(true);
            }
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