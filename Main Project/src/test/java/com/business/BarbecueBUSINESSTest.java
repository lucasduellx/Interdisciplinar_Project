package com.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.helper.Meat;

public class BarbecueBUSINESSTest {
    @Test
    void testCheckCurrentTemp() {

        Meat meat = new Meat();

        Double currentTemp = 200.0;
        
        meat.setPoint("Ao Ponto");
        meat.setType("Bovina");
        meat.setTemp_min(20.0);
        meat.setTemp_max(30.0);

        assertEquals(BarbecueBUSINESS.checkCurrentTemp(meat, currentTemp), "Carne Carvão");

    }
}
