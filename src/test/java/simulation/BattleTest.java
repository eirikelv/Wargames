package simulation;

import edu.ntnu.idatt2001.eirielv.model.simulation.TerrainType;
import edu.ntnu.idatt2001.eirielv.model.units.CavalryUnit;
import edu.ntnu.idatt2001.eirielv.model.units.CommanderUnit;
import edu.ntnu.idatt2001.eirielv.model.units.InfantryUnit;
import edu.ntnu.idatt2001.eirielv.model.units.RangedUnit;
import edu.ntnu.idatt2001.eirielv.model.simulation.Army;
import edu.ntnu.idatt2001.eirielv.model.simulation.Battle;
import edu.ntnu.idatt2001.eirielv.model.simulation.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    @Test
    void battle_constructor_has_armies(){
        //Arrange
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        Battle battle = new Battle(armyOne, armyTwo);

        String expected1 = armyOne.toString();
        String actual1 = battle.getArmyOne().toString();
        String expected2 = armyTwo.toString();
        String actual2 = battle.getArmyTwo().toString();

        //Act
        boolean equals = expected1.equals(actual1) && expected2.equals(actual2);

        //Assert
        assertTrue(equals);
    }

    @Test
    void the_simulator_gives_a_winner(){
        //Arrange
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");

        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 15));
        units.add(new RangedUnit("Archer", 20));
        units.add(new InfantryUnit("Solider", 30));
        units.add(new CommanderUnit("Rex", 40));
        armyOne.addAll(units);

        List<Unit> units2 = new ArrayList<>();
        units2.add(new CavalryUnit("Knight", 10));
        units2.add(new RangedUnit("Archer", 10));
        units2.add(new InfantryUnit("Solider", 10));
        units2.add(new CommanderUnit("Rex", 10));
        armyTwo.addAll(units2);

        Battle battle = new Battle(armyOne, armyTwo);

        //Act
        Army winner = battle.simulate(TerrainType.PLAINS);

        //Assert
         assertNotNull(winner);
    }

    @Test
    public void toString_gives_correct_toString(){
        //Arrange
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");

        List<Unit> units = new ArrayList<>();
        units.add(new CavalryUnit("Knight", 15));
        units.add(new RangedUnit("Archer", 20));
        units.add(new InfantryUnit("Solider", 30));
        units.add(new CommanderUnit("Rex", 40));
        armyOne.addAll(units);

        List<Unit> units2 = new ArrayList<>();
        units2.add(new CavalryUnit("Knight", 10));
        units2.add(new RangedUnit("Archer", 10));
        units2.add(new InfantryUnit("Solider", 10));
        units2.add(new CommanderUnit("Rex", 10));
        armyTwo.addAll(units2);
        Battle battle = new Battle(armyOne, armyTwo);

        String expected = "armyOne vs armyTwo";

        //Act
        String actual = battle.toString();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    public void singleSimulate_runs_a_simpulation(){
        //Arrange
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");

        armyOne.add(new CavalryUnit("Knight", 15));
        armyTwo.add(new RangedUnit("Archer", 15));

        int unexpected1 = armyOne.getCavalryUnits().get(0).getHealth();
        Battle battle = new Battle(armyOne, armyTwo);

        //Act
        battle.singleSimulate(TerrainType.PLAINS);


        //Assert
        assertNotEquals(unexpected1, armyOne.getCavalryUnits().get(0).getHealth());
    }

    @Nested
    class terrainWorks_works_as_expected_when {
        @Test
        public void simulator_with_terrainType_plains_gives_a_winner() {
            //Arrange
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> units2 = new ArrayList<>();
            units2.add(new CavalryUnit("Knight", 10));
            units2.add(new RangedUnit("Archer", 10));
            units2.add(new InfantryUnit("Solider", 10));
            units2.add(new CommanderUnit("Rex", 10));
            armyTwo.addAll(units2);

            Battle battle = new Battle(armyOne, armyTwo);

            //Act
            Army winner = battle.simulate(TerrainType.PLAINS);

            //Assert
            assertNotNull(winner);
        }

        @Test
        public void simulator_with_terrainType_forest_gives_a_winner() {
            //Arrange
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> units2 = new ArrayList<>();
            units2.add(new CavalryUnit("Knight", 10));
            units2.add(new RangedUnit("Archer", 10));
            units2.add(new InfantryUnit("Solider", 10));
            units2.add(new CommanderUnit("Rex", 10));
            armyTwo.addAll(units2);

            Battle battle = new Battle(armyOne, armyTwo);

            //Act
            Army winner = battle.simulate(TerrainType.FOREST);

            //Assert
            assertNotNull(winner);
        }

        @Test
        public void simulator_with_terrainType_hill_gives_a_winner() {
            //Arrange
            Army armyOne = new Army("armyOne");
            Army armyTwo = new Army("armyTwo");

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Solider", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);

            List<Unit> units2 = new ArrayList<>();
            units2.add(new CavalryUnit("Knight", 10));
            units2.add(new RangedUnit("Archer", 10));
            units2.add(new InfantryUnit("Solider", 10));
            units2.add(new CommanderUnit("Rex", 10));
            armyTwo.addAll(units2);

            Battle battle = new Battle(armyOne, armyTwo);

            //Act
            Army winner = battle.simulate(TerrainType.HILL);

            //Assert
            assertNotNull(winner);
        }
    }
}
