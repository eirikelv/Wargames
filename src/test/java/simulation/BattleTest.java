package simulation;

import edu.ntnu.idatt2001.eirielv.units.*;
import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.Battle;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    @Test
    void constructor_has_armies(){
        Army armyOne = new Army("armyOne");
        Army armyTwo = new Army("armyTwo");
        Battle battle = new Battle(armyOne, armyTwo);

        assertEquals(armyOne, battle.getArmyOne());
        assertEquals(armyTwo, battle.getArmyTwo());
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
        //Assert
         assertNotNull(battle.simulate());
    }

    @Test
    public void BattleTest_toString_gives_correct_toString(){
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
        //Assert
        assertEquals("armyOne VS armyTwo",battle.toString());
    }

}
