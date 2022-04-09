package edu.ntnu.idatt2001.eirielv.simulation;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idatt2001.eirielv.Units.*;


public class InfantryUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            String expected_name = "Knight";
            //act

            //assert
            assertEquals(expected_name, infantryUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            //assert
            assertEquals(100, infantryUnit.getHealth());
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            //assert
            assertEquals(15, infantryUnit.getAttack());
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            //assert
            assertEquals(10, infantryUnit.getArmor());
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            //assert
            assertEquals("Knight", infantryUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            //assert
            assertEquals(100, infantryUnit.getHealth());
        }
    }


    @Nested
    class the_constructor_gets_wrong_information{
        @Test
        public void name_is_empty(){
            try {
                //arrange
                InfantryUnit infantryUnit = new InfantryUnit("", 100);
                //act
            } catch (IllegalArgumentException e){
                //assert
                assertTrue(true);
            }
        }

        @Test
        public void name_is_blank(){
            try {
                //arrange
                InfantryUnit infantryUnit = new InfantryUnit(" ", 100);
                //act
            } catch (IllegalArgumentException e){
                //assert
                assertTrue(true);
            }
        }

        @Test
        public void health_is_lower_than_0(){
            try {
                //arrange
                InfantryUnit infantryUnit = new InfantryUnit("Knight", -1);
                //act
            } catch (IllegalArgumentException e){
                //assert
                assertTrue(true);
            }
        }

        @Test
        public void attack_is_negative(){
            try {
                //arrange
                InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, -1, 10);
                //act
            } catch(IllegalArgumentException e) {
                //assort
                assertTrue(true);
            }
        }

        @Test
        public void armor_is_negative(){
            try {
                //arrange
                InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, -1);
                //act
            } catch(IllegalArgumentException e) {
                //assort
                assertTrue(true);
            }
        }
    }

    @Nested
    class getAttackbonus_gives_expected_values{
        @Test
        public void attackBonus_is_2(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            //act
            int expectedAttackBonus = 2;
            int attackBonus = infantryUnit.getAttackBonus();
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }
    }

    @Nested
    class getResistbonus_gives_expected_values {
        @Test
        public void resistBonus_is_1(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            //act
            int expectedResistBonus = 1;
            int resistBonus = infantryUnit.getResistBonus();
            //assert
            assertEquals(expectedResistBonus,resistBonus);
        }

    }
}
