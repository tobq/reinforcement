package tobi.reinforcement.problems;

import tobi.reinforcement.network.Network;

import java.util.Random;

public class Memorise {
    private final int digits;

    public Memorise(int digits) {
        this.digits = digits;
    }

    public double test(Network network, Integer seed) {
        Random random = new Random(seed);
        network.reset();

        double[] string = new double[digits];
        double min = 0;
        double max = 10;
        for (int i = 0; i < digits; i++) {
            string[i] = random.nextDouble() * (min-max) - min;
        }

        int finalIndex = string.length - 1;
        for (int i = 0; i < finalIndex; i++) {
            network.compute(string[i]);
        }
        double[] remembered = network.compute(string[finalIndex]);
        double error = 0;
        for (int i = 0; i < remembered.length; i++) {
            error += Math.abs(remembered[i] - string[i]);
        }
        if (Double.isNaN(error)) return Double.NEGATIVE_INFINITY;
        return -error;
    }

    public int getLabelLength() {
        return digits;
    }

    public int getInstanceLength() {
        return 1;
    }
}
