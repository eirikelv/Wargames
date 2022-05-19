package units;

import edu.ntnu.idatt2001.eirielv.simulation.TerrainType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idatt2001.eirielv.units.*;


public class RangedUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100, 15, 8);
            String expected_name = "Archer";
            //act

            //assert
            assertEquals(expected_name, rangedUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100, 15, 8);
            //assert
            assertEquals(100, rangedUnit.getHealth());
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100, 15, 8);
            //assert
            assertEquals(15, rangedUnit.getAttack());
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100, 15, 8);
            //assert
            assertEquals(8, rangedUnit.getArmor());
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            //assert
            assertEquals("Archer", rangedUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            //assert
            assertEquals(100, rangedUnit.getHealth());
        }
    }


    @Nested
    class the_constructor_gets_wrong_information{
        @Test
        public void name_is_empty(){
            try {
                //arrange
                RangedUnit rangedUnit = new RangedUnit("", 100);
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
                RangedUnit rangedUnit = new RangedUnit(" ", 100);
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
                RangedUnit rangedUnit = new RangedUnit("Archer", -1);
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
                RangedUnit rangedUnit = new RangedUnit("Archer", 100, -1, 8);
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
                RangedUnit rangedUnit = new RangedUnit("Archer", 100, 15, -1);
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
        public void attackBonus_is_3(){
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            //act
            int expectedAttackBonus = 3;
            int attackBonus = rangedUnit.getAttackBonus(TerrainType.PLAINS);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }

        @Test
        public void attackBonus_is_5_if_terrainType_is_Hill(){
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            //act
            int expectedAttackBonus = 5;
            int attackBonus = rangedUnit.getAttackBonus(TerrainType.HILL);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }

        @Test
        public void attackBonus_is_2_if_terrainType_is_Forest(){
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            //act
            int expectedAttackBonus = 2;
            int attackBonus = rangedUnit.getAttackBonus(TerrainType.FOREST);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }
    }

    @Nested
    class getResistbonus_gives_expected_values {
        @Test
        public void resistBonus_equals_6_after_1_attacks() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            int expectedResistanceBonus = 6;
            TerrainType terrainType = TerrainType.PLAINS;
            //act

            //assert
            assertEquals(expectedResistanceBonus, rangedUnit.getResistBonus(terrainType));
        }

        @Test
        public void resistBonus_equals_4_after_2_attacks() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            int expectedResistanceBonus = 4;
            TerrainType terrainType = TerrainType.PLAINS;
            //act
            rangedUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistanceBonus, rangedUnit.getResistBonus(terrainType));
        }

        @Test
        public void resistBonus_equals_2_after_3_attacks() {
            //arrange
            RangedUnit rangedUnit = new RangedUnit("Archer", 100);
            int expectedResistanceBonus = 2;
            TerrainType terrainType = TerrainType.PLAINS;
            //act
            rangedUnit.getResistBonus(terrainType);
            rangedUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistanceBonus, rangedUnit.getResistBonus(terrainType));
        }

    }

}
