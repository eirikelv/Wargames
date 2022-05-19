package simulation;

import edu.ntnu.idatt2001.eirielv.units.*;
import edu.ntnu.idatt2001.eirielv.simulation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void name_not_null() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Unit unittest = new Unit("", 1, 1, 1) {
                @Override
                public int getAttackBonus(TerrainType terrainType) {
                    return 0;
                }

                @Override
                public int getResistBonus(TerrainType terrainType) {
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
                public int getAttackBonus(TerrainType terrainType) {
                    return 0;
                }

                @Override
                public int getResistBonus(TerrainType terrainType) {
                    return 0;
                }
            };
        });
    }
}
