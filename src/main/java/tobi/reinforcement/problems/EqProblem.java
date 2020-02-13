package tobi.reinforcement.problems;

import tobi.reinforcement.Problem;
import tobi.reinforcement.network.Network;

public class EqProblem implements Problem<Integer[], Integer> {

    private static final double X = 2;

    public double test(Network network) {
        double fitness = 0;
        for (int i = 0; i < X; i++)
            for (int j = 0; j < X; j++) {
                double classification = network.compute(i, j)[0];
                int label = i == j ? 1 : 0;
                fitness += 1 - Math.abs(classification - label);
            }
        if (Double.isNaN(fitness)) return Double.NEGATIVE_INFINITY;
        return fitness;
    }


    public int getLabelLength() {
        return 1;
    }

    public int getInstanceLength() {
        return 2;
    }
}

