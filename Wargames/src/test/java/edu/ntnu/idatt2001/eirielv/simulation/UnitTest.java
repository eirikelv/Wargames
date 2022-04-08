package edu.ntnu.idatt2001.eirielv.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void name_not_null() throws Exception {
        try {
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
        }
        catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void name_not_blank() throws Exception{
        try {
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
        }
        catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }
}
