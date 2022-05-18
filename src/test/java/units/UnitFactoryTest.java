package units;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idatt2001.eirielv.units.*;
import edu.ntnu.idatt2001.eirielv.simulation.*;

public class UnitFactoryTest {

    @Nested
    class getUnit_method_makes_works_as_expected_when{

        @Test
        public void getUnit_makes_a_unit(){
            Army testArmy = new Army("testArmy");
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));
            assertTrue(testArmy.hasUnits());
        }

        @Test
        public void getUnit_gives_correct_unitType(){
            Army testArmy = new Army("testArmy");
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));
            InfantryUnit infantryUnit = new InfantryUnit("Tarzan",100);
            assertEquals(testArmy.getAllUnits().get(0).getClass(),InfantryUnit.class);
        }

        @Test
        public void getUnit_makes_correct_information(){
            Army testArmy = new Army("Army");
            Army expectedArmy = new Army("Army");
            InfantryUnit expectedInfantry = new InfantryUnit("Tarzan", 100);

            expectedArmy.add(expectedInfantry);
            testArmy.add(UnitFactory.createUnit(UnitType.INFANTRYUNIT,"Tarzan",100));

            assertEquals(expectedArmy.toString(),testArmy.toString());
        }

    }
    @Nested
    class addDuplicateUnitsAsList_method_works_as_expected_when{

        @Test
        public void addDuplicateUnitsAsList_method_throws_exception_if_param_quantity_is_lower_than_one(){
            int quantity = -1;
            Army testArmy = new Army("testArmy");
            UnitType testUnitType = UnitType.INFANTRYUNIT;

            assertThrows(IllegalArgumentException.class,()->{
                UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity);
            });
        }

        @Test
        public void addDuplicateUnitsAsList_method_stores_units(){
            int quantity = 4;
            Army testArmy = new Army("testArmy");
            UnitType testUnitType = UnitType.INFANTRYUNIT;

            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity));

            assertTrue(testArmy.hasUnits());
        }

        @Test
        public void addDuplicateUnitsAsList_method_stores_correct_information(){
            int quantity = 1;
            Army testArmy = new Army("army");
            Army expectedArmy = new Army("army");
            UnitType testUnitType = UnitType.INFANTRYUNIT;
            InfantryUnit expectedInfantry = new InfantryUnit("Tarzan", 100);

            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType,"Tarzan",100,quantity));
            expectedArmy.add(expectedInfantry);

            assertEquals(testArmy.toString(),expectedArmy.toString());
        }
        @Test
        public void addDuplicateUnitsAsList_method_duplicates_correct_amount_of_units() {
            int expectedQuantity = 3;
            Army testArmy = new Army("army");
            UnitType testUnitType = UnitType.INFANTRYUNIT;

            testArmy.addAll(UnitFactory.addDuplicateUnitsAsList(testUnitType, "Tarzan", 100, expectedQuantity));

            assertEquals(3, testArmy.getAllUnits().size());
        }

    }
}
