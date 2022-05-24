package units;

import edu.ntnu.idatt2001.eirielv.model.simulation.TerrainType;
import edu.ntnu.idatt2001.eirielv.model.units.CommanderUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CommanderUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            String expected_name = "Commander";
            //act
            String actual = commanderUnit.getName();
            //assert
            assertEquals(expected_name, actual);
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            int expected_health = 100;
            //act
            int actual = commanderUnit.getHealth();
            //assert
            assertEquals(expected_health, actual);
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            int expected_attack = 25;
            //act
            int actual = commanderUnit.getAttack();
            //assert
            assertEquals(expected_attack, actual);
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            int expected_armor = 15;
            //act
            int actual = commanderUnit.getArmor();
            //assert
            assertEquals(expected_armor, actual);
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            String expected_name = "Commander";
            //act
            String actual = commanderUnit.getName();
            //assert
            assertEquals(expected_name, actual);
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            int expected_health = 100;
            //act
            int actual = commanderUnit.getHealth();
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
                CommanderUnit commanderUnit = new CommanderUnit("", 100);
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
                CommanderUnit commanderUnit = new CommanderUnit(" ", 100);
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
                CommanderUnit commanderUnit = new CommanderUnit("Commander", -1);
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
                CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, -1, 15);
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
                CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, -1);
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
        public void attackBonus_is_6_with_first_attack() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            TerrainType terrainType = TerrainType.HILL;
            int expectedAttackBonus = 6;
            //act
            int attackBonus = commanderUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }


        @Test
        public void attackBonus_is_2_after_first_attack() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            TerrainType terrainType = TerrainType.HILL;
            int expectedAttackBonus = 2;
            //act
            commanderUnit.getAttackBonus(terrainType);
            int attackBonus = commanderUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }
    }

    @Test
    public void getResistbonus_gives_expected_values_resistBonus_equals_1() {
        //arrange
        CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
        TerrainType terrainType = TerrainType.HILL;
        int expectedResistanceBonus = 1;
        //act
        int resistanceBonus = commanderUnit.getResistBonus(terrainType);
        //assert
        assertEquals(expectedResistanceBonus, resistanceBonus);
    }

}
