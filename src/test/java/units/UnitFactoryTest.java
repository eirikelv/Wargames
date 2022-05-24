package units;

import edu.ntnu.idatt2001.eirielv.model.simulation.Army;
import edu.ntnu.idatt2001.eirielv.model.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.model.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.model.units.InfantryUnit;
import edu.ntnu.idatt2001.eirielv.model.units.UnitFactory;
import edu.ntnu.idatt2001.eirielv.model.units.UnitType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class UnitFactoryTest {

    @Nested
    class getUnit_method_makes_works_as_expected_when{

        @Test
        public void createUnit_makes_a_unit(){
            //Arrange
            Army testArmy = new Army("testArmy");
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));
            boolean units;
            //Act
            units = testArmy.hasUnits();
            //Assert
            assertTrue(units);
        }

        @Test
        public void createUnit_gives_correct_unitType(){
            //Arrange
            Army testArmy = new Army("testArmy");
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));
            InfantryUnit infantryUnit = new InfantryUnit("Tarzan",100);
            String expected = InfantryUnit.class.toString();
            //Act
            String actual = testArmy.getAllUnits().get(0).getClass().toString();
            //Assert
            assertEquals(actual,expected);
        }

        @Test
        public void createUnit_makes_correct_information(){
            //Arrange
            Army testArmy = new Army("Army");
            Army expectedArmy = new Army("Army");
            InfantryUnit expectedInfantry = new InfantryUnit("Tarzan", 100);
            expectedArmy.add(expectedInfantry);
            boolean correctInformation;
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));
            //Act
            correctInformation = (Objects.equals(expectedArmy.toString(), testArmy.toString()));
            //Assert
            assertTrue(correctInformation);
        }

    }
    @Nested
    class addDuplicateUnitsAsList_method_works_as_expected_when{

        @Test
        public void addDuplicateUnitsAsList_method_throws_exception_if_param_quantity_is_lower_than_one(){
            //Assert
            int quantity = -1;
            Army testArmy = new Army("testArmy");
            UnitType testUnitType = UnitType.INFANTRYUNIT;

            assertThrows(IllegalArgumentException.class,()->{
                //Act
                UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity);
            });
            //Assert
        }

        @Test
        public void addDuplicateUnitsAsList_method_stores_units(){
            //Arrange
            int quantity = 4;
            Army testArmy = new Army("testArmy");
            UnitType testUnitType = UnitType.INFANTRYUNIT;
            boolean methodStoresUnits;
            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity));
            //Act
            methodStoresUnits = testArmy.hasUnits();
            //Assert
            assertTrue(methodStoresUnits);
        }

        @Test
        public void addDuplicateUnitsAsList_method_stores_correct_information(){
            //Arrange
            int quantity = 1;
            Army testArmy = new Army("army");
            Army expectedArmy = new Army("army");
            UnitType testUnitType = UnitType.INFANTRYUNIT;
            InfantryUnit expectedInfantry = new InfantryUnit("Tarzan", 100);
            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity));
            expectedArmy.add(expectedInfantry);
            String expected = expectedArmy.toString();
            String actual = testArmy.toString();
            boolean correctInformation;
            //Act
            correctInformation = actual.equals(expected);
            //Assert
            assertTrue(correctInformation);
        }
        @Test
        public void addDuplicateUnitsAsList_method_duplicates_correct_amount_of_units() {
            //Arrange
            int expectedQuantity = 3;
            Army testArmy = new Army("army");
            UnitType testUnitType = UnitType.INFANTRYUNIT;
            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType, "Tarzan", 100, expectedQuantity));
            //Act
            int actual = testArmy.getAllUnits().size();
            //Assert
            assertEquals(expectedQuantity, actual);
        }

    }
}
