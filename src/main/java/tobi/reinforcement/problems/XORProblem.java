package tobi.reinforcement.problems;

import tobi.reinforcement.Problem;
import tobi.reinforcement.network.Network;

public class XORProblem implements Problem<Integer[], Integer> {
    public double test(Network network) {
        double fitness = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                double classification = network.compute(i, j)[0];
                int label = i == j ? 0 : 1;
//                fitness += 1 - Math.abs(classification - label);
                fitness -= Math.abs(classification - label);
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

