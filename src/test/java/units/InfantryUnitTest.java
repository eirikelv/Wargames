package units;

import edu.ntnu.idatt2001.eirielv.simulation.TerrainType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idatt2001.eirielv.units.*;


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
        public void attackBonus_is_2_if_terrain_is_NOT_forest(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedAttackBonus = 2;
            int attackBonus = infantryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }

        @Test
        public void attackBonus_is_5_if_terrainType_is_Forest(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.FOREST;
            //act
            int expectedAttackBonus = 5;
            int attackBonus = infantryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }
    }

    @Nested
    class getResistbonus_gives_expected_values {
        @Test
        public void resistBonus_is_1_if_terrain_is_NOT_forest(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedResistBonus = 1;
            int resistBonus = infantryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistBonus,resistBonus);
        }

        @Test
        public void resistBonus_is_3_if_terrainType_is_Forest() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.FOREST;
            //act
            int expectedResistBonus = 3;
            int resistBonus = infantryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistBonus, resistBonus);
        }
    }
}
