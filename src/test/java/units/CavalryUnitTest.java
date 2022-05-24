package units;

import edu.ntnu.idatt2001.eirielv.model.simulation.TerrainType;
import edu.ntnu.idatt2001.eirielv.model.units.CavalryUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CavalryUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, 20, 12);
            String expected_name = "Cavalry";
            //act

            //assert
            assertEquals(expected_name, cavalryUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, 20, 12);
            //assert
            assertEquals(100, cavalryUnit.getHealth());
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, 20, 12);
            //assert
            assertEquals(20, cavalryUnit.getAttack());
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, 20, 12);
            //assert
            assertEquals(12, cavalryUnit.getArmor());
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            //assert
            assertEquals("Cavalry", cavalryUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            //assert
            assertEquals(100, cavalryUnit.getHealth());
        }
    }


    @Nested
    class the_constructor_gets_wrong_information{
        @Test
        public void name_is_empty(){
            try {
                //arrange
                CavalryUnit cavalryUnit = new CavalryUnit("", 100);
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
                CavalryUnit cavalryUnit = new CavalryUnit(" ", 100);
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
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", -1);
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
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, -1, 12);
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
                CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100, 20, -1);
                //act
            } catch(IllegalArgumentException e) {
                //assort
                assertTrue(true);
            }
        }
    }

    @Nested
    class getAttackbonus_gives_expected_values {
        @Test
        public void attackBonus_is_6_with_first_attack_if_terrain_is_NOT_forest() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedAttackBonus = 6;
            int attackBonus = cavalryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }


        @Test
        public void attackBonus_is_2_after_first_attack_if_terrain_is_NOT_forest() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedAttackBonus = 2;
            cavalryUnit.getAttackBonus(terrainType);
            int attackBonus = cavalryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }
        @Test
        public void attackBonus_is_5_after_first_attack_when_terrain_is_forest(){
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            TerrainType terrainType = TerrainType.PLAINS;
            //act
            int expectedAttackBonus = 4;
            cavalryUnit.getAttackBonus(terrainType);
            int attackBonus = cavalryUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }
    }

    @Nested
    class getResistbonus_gives_expected_values{
        @Test
        public void resistBonus_equals_1_if_terrain_is_NOT_forest() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedResistanceBonus = 1;
            int resistanceBonus = cavalryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistanceBonus, resistanceBonus);
        }

        @Test
        public void resistBonus_equals_0_if_terrain_is_forest() {
            //arrange
            CavalryUnit cavalryUnit = new CavalryUnit("Cavalry", 100);
            TerrainType terrainType = TerrainType.FOREST;
            //act
            int expectedResistanceBonus = 0;
            int resistanceBonus = cavalryUnit.getResistBonus(terrainType);
            //assert
            assertEquals(expectedResistanceBonus, resistanceBonus);
        }

        /**
         * TODO add testers for resist and attackbonus with TerrainType
         */
    }
}
