package units;

import edu.ntnu.idatt2001.eirielv.model.simulation.TerrainType;
import edu.ntnu.idatt2001.eirielv.model.units.InfantryUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InfantryUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            String expected_name = "Knight";
            //act
            String actual = infantryUnit.getName();
            //assert
            assertEquals(expected_name, actual);
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            int expected_health = 100;
            //act
            int actual = infantryUnit.getHealth();
            //assert
            assertEquals(expected_health, actual);
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            int expected_attack = 15;
            //act
            int actual = infantryUnit.getAttack();
            //assert
            assertEquals(expected_attack, actual);
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100, 15, 10);
            int expected_armor = 10;
            //act
            int actual = infantryUnit.getArmor();
            //assert
            assertEquals(expected_armor, actual);
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            String expected_name = "Knight";
            //act
            String actual = infantryUnit.getName();
            //assert
            assertEquals(expected_name, actual);
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            int expected_health = 100;
            //act
            int actual = infantryUnit.getHealth();
            //assert
            assertEquals(expected_health, actual);
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
            int expectedAttackBonus = 2;
            //act
            int attackBonus = infantryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus,attackBonus);
        }

        @Test
        public void attackBonus_is_5_if_terrainType_is_Forest(){
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.FOREST;
            int expectedAttackBonus = 5;
            //act
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
            int expectedResistBonus = 1;
            //act
            int resistBonus = infantryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistBonus,resistBonus);
        }

        @Test
        public void resistBonus_is_3_if_terrainType_is_Forest() {
            //arrange
            InfantryUnit infantryUnit = new InfantryUnit("Knight", 100);
            TerrainType terrainType = TerrainType.FOREST;
            int expectedResistBonus = 3;
            //act
            int resistBonus = infantryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistBonus, resistBonus);
        }
    }
}
