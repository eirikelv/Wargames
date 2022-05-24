package simulation;

import edu.ntnu.idatt2001.eirielv.model.simulation.Army;
import edu.ntnu.idatt2001.eirielv.model.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.model.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.model.units.CavalryUnit;
import edu.ntnu.idatt2001.eirielv.model.units.CommanderUnit;
import edu.ntnu.idatt2001.eirielv.model.units.InfantryUnit;
import edu.ntnu.idatt2001.eirielv.model.units.RangedUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyFileHandlingTest {

    @Nested
    class getArmyFromCSVInput_works_as_expected_when_it{

        @Test
        public void reads_correct_information_from_a_csv_file_and_makes_a_army() throws IOException {
            Army armyOne = new Army("testersHumanArmy");
            ArmyFileHandling armyFileHandling = new ArmyFileHandling();
            List<Unit> expectedInformation = new ArrayList<>();

            expectedInformation.add(new InfantryUnit("Footman",100));
            expectedInformation.add(new InfantryUnit("Footman",100));
            expectedInformation.add(new CommanderUnit("Mountain King",180));
            armyOne.addAll(expectedInformation);

            assertEquals(armyOne.getAllUnits().toString(), armyFileHandling.getArmyFromCSVInput("HumanArmy")
                    .getAllUnits().toString());

        }

        @Test
        public void does_not_read_incorrect_information_from_a_csv_file(){
            assertThrows(IllegalArgumentException.class, () -> {
                Army armyOne = new Army("testersWrongInformation");
                ArmyFileHandling armyFileHandling = new ArmyFileHandling();
                    armyFileHandling.getArmyFromCSVInput("testersWrongInformation");
            });
        }

        @Test
        public void throws_exception_if_the_unitType_does_not_exist(){
            assertThrows(IllegalArgumentException.class, () -> {
                Army armyOne = new Army("testersWrongUnitType");
                ArmyFileHandling armyFileHandling = new ArmyFileHandling();
                armyFileHandling.getArmyFromCSVInput("testersWrongUnitType");
            });
        }

    }

    @Nested
    class unitsToCSVFile_works_as_expected_when_it{

        @Test
        public void throws_illegalArgumentException_if_there_are_special_characters_in_file_name(){
            assertThrows(IllegalArgumentException.class, () -> {
                Army specialCharacterArmy = new Army("Eirik's army!");
                ArmyFileHandling armyFileHandling = new ArmyFileHandling();

                List<Unit> units = new ArrayList<>();
                units.add(new CavalryUnit("Knight", 15));
                units.add(new RangedUnit("Archer", 20));
                units.add(new InfantryUnit("Solider1", 30));
                units.add(new CommanderUnit("Rex", 40));
                specialCharacterArmy.addAll(units);

                armyFileHandling.writeUnitsToCSVFile(specialCharacterArmy);
            });
        }

        @Test
        public void makes_a_CSV_file(){
            Army armyOne = new Army("armyOne");
            ArmyFileHandling armyFileHandling = new ArmyFileHandling();

            List<Unit> units = new ArrayList<>();
            units.add(new CavalryUnit("Knight", 15));
            units.add(new RangedUnit("Archer", 20));
            units.add(new InfantryUnit("Soldier1", 30));
            units.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(units);
            armyFileHandling.writeUnitsToCSVFile(armyOne);
            File testFile = new File("src/main/resources/edu/ntnu/idatt2001/eirielv/armyCSVFiles/armyOne.csv");
            assertTrue(testFile.exists());
            testFile.delete();
        }

        @Test
        public void stores_correct_information_to_a_CSV_file() throws IOException {
            Army armyOne = new Army("armyOne");
            ArmyFileHandling armyFileHandling = new ArmyFileHandling();

            List<Unit> expectedUnits = new ArrayList<>();
            expectedUnits.add(new CavalryUnit("Knight", 15));
            expectedUnits.add(new RangedUnit("Archer", 20));
            expectedUnits.add(new InfantryUnit("Soldier1", 30));
            expectedUnits.add(new CommanderUnit("Rex", 40));
            armyOne.addAll(expectedUnits);
            armyFileHandling.writeUnitsToCSVFile(armyOne);

            Army armyTwo = armyFileHandling.getArmyFromCSVInput("armyOne");
            assertEquals(armyOne.toString(), armyTwo.toString());
            File testFile = new File("src/main/resources/edu/ntnu/idatt2001/eirielv/armyCSVFiles/armyOne.csv");
            testFile.delete();
        }
    }
}
