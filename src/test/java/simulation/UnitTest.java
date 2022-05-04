package simulation;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void name_not_null() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Unit unittest = new Unit("", 1, 1, 1) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
        });
    }

    @Test
    public void name_not_blank() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            Unit unittest = new Unit(" ", 1, 1, 1) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
        });
    }
}
