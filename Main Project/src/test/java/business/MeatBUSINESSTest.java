package business;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import helper.Meat;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeatBUSINESSTest {
    
    @Test
    void testCheckMeat() throws Exception {

        Boolean test = false;
        Meat meat = MeatBUSINESS.checkMeat("teste");

        if(meat != null){
            test = true;
        }

        assertEquals(test, true);
    }

    @Test
    void testCheckTemp() {
        ArrayList<Double> testList = new ArrayList<Double>(Arrays.asList(30.0,33.0));
        ArrayList<Double> list = MeatBUSINESS.checkTemp("Mal Passada");

        assertIterableEquals(testList,list);
    }
}
