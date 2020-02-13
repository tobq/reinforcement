package tobi.reinforcement.problems;

import tobi.reinforcement.Problem;
import tobi.reinforcement.network.Network;

public class SequenceProblem implements Problem<Integer[], Integer> {
    public static final Integer[][] DATA_SET;
    private static final int SEQUENCE_LENGTH = 8;
    private static final int MAX_DIFF = 2;
    private static final int MAX_DIFF_SQUARED = MAX_DIFF * MAX_DIFF;

    static {
        int instanceCount = 2 << SEQUENCE_LENGTH;
        DATA_SET = new Integer[instanceCount][SEQUENCE_LENGTH];
        for (int i = 0; i < instanceCount; i++) {
            for (int j = 0; j < SEQUENCE_LENGTH; j++) {
                int val = DATA_SET.length * j + i;
                int ret = (1 & (val >>> j));
                DATA_SET[i][j] = ret;
            }
        }
    }

    public static final int[] LABELS = new int[DATA_SET.length];

    static {
        for (int i = 0; i < DATA_SET.length; i++) {
            int label = 0;
            for (int bit : DATA_SET[i]) {
                label += bit == 1 ? 1 : -1;
            }
            LABELS[i] = Integer.compare(label, 0);
        }
    }

    @Override
    public double test(Network network) {
        double fitness = 0;
        for (int s = 0; s < DATA_SET.length; s++) {
            Integer[] sequence = DATA_SET[s];
            for (int i = 0; i < sequence.length - 1; i++) {
                network.compute(sequence[i]);
                // TODO:  experiment: with building fitness as you go as opposed to using final classification
            }
            double classification = network.compute(sequence[sequence.length - 1])[0];
//        System.out.println("sequence = " + Arrays.toString(sequence));
//        System.out.println("classification - label = " + (classification - label));
            double diff = Math.abs(classification - LABELS[s]);
            fitness -= diff;
//            fitness += MAX_DIFF_SQUARED - diff * diff;
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
