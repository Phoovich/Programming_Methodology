package util;

import java.util.Random;

public class GenRandom {

    public static Random random = new Random(1234);

    public static double randomDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }
}
