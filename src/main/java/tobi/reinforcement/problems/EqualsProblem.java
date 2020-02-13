package tobi.reinforcement.problems;

import tobi.reinforcement.Problem;
import tobi.reinforcement.network.Network;

public class EqualsProblem implements Problem {
    private final int x;

    public EqualsProblem(int x) {
        this.x = x;
    }

    @Override
    public double test(Network network) {
        double fitness = 0;
        for (int i = 0; i < 10000; i++) {
            double classification = network.compute(i)[0];
            fitness -= Math.abs(classification - x);
        }
        if (Double.isNaN(fitness)) return Double.NEGATIVE_INFINITY;
        return fitness;
    }

    @Override
    public int getLabelLength() {
        return 1;
    }

    @Override
    public int getInstanceLength() {
        return 1;
    }
}
