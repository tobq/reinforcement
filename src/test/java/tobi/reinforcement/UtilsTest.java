package tobi.reinforcement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void logRandom() {
        for (int i = 0; i < 1001; i++) {
            int max = 1000;
            int min = 0;
            double x = Utils.logRandom(min, max, 200);
            Assertions.assertTrue(x <= max);
            Assertions.assertTrue(x >= min);
        }
    }
}