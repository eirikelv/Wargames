package units;

import edu.ntnu.idatt2001.eirielv.simulation.TerrainType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idatt2001.eirielv.units.*;


public class CommanderUnitTest {

    @Nested
    class the_manual_constructor_gets_expected_values {
        @Test
        public void name_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            String expected_name = "Commander";
            //act

            //assert
            assertEquals(expected_name, commanderUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            //assert
            assertEquals(100, commanderUnit.getHealth());
        }

        @Test
        public void attack_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            //assert
            assertEquals(25, commanderUnit.getAttack());
        }

        @Test
        public void armor_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100, 25, 15);
            //assert
            assertEquals(15, commanderUnit.getArmor());
        }
    }

    @Nested
    class all_variables_in_constructor_with_set_attack_and_armor_is_valid{
        @Test
        public void name_is_a_expected_value(){
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            //assert
            assertEquals("Commander", commanderUnit.getName());
        }

        @Test
        public void health_is_a_expected_value() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            //assert
            assertEquals(100, commanderUnit.getHealth());
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
            //act
            int expectedAttackBonus = 6;
            int attackBonus = commanderUnit.getAttackBonus(terrainType);
            //assert
            assertEquals(expectedAttackBonus, attackBonus);
        }


        @Test
        public void attackBonus_is_2_after_first_attack() {
            //arrange
            CommanderUnit commanderUnit = new CommanderUnit("Commander", 100);
            TerrainType terrainType = TerrainType.HILL;
            //act
            int expectedAttackBonus = 2;
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
        //act
        int expectedResistanceBonus = 1;
        int resistanceBonus = commanderUnit.getResistBonus(terrainType);
        //assert
        assertEquals(expectedResistanceBonus, resistanceBonus);
    }

}
