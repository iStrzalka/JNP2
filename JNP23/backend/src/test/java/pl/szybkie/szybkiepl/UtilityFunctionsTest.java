package pl.szybkie.szybkiepl;

import pl.szybkie.szybkiepl.utilityFunctions.UtilityFunctions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class UtilityFunctionsTest {
    @Test
    void testSimpleDistance(){
        int calculated_distance = (int)UtilityFunctions.distance(52.2308, 52.1678, 21.0098, 20.8318, 0, 0)/1000;
        assertEquals(14, calculated_distance);
    }

    @Test
    void testZeroDistance(){
        int calculated_distance = (int)UtilityFunctions.distance(0, 0, 0, 0, 0, 0)/1000;
        assertEquals(0, calculated_distance);
    }
    
    @Test
    void testNegativeDistance(){
        int calculated_distance = (int)UtilityFunctions.distance(-5, -5, 5, 5, 0, 0)/1000;
        assertEquals(0, calculated_distance);
    }

    @Test
    void testNegative2Distance(){
        int calculated_distance = (int)UtilityFunctions.distance(-5, -2, 5, 2, 0, 0)/1000;
        assertEquals(471, calculated_distance);
    }

    @Test
    void testNegative3Distance(){
        int calculated_distance = (int)UtilityFunctions.distance(-5.5, -2.0, 5.3, 2, 1, 0)/1000;
        assertEquals(534, calculated_distance);
    }
}
