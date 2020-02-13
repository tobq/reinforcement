package tobi.reinforcement.problems;

import tobi.reinforcement.Problem;
import tobi.reinforcement.network.Network;

import java.util.function.DoubleUnaryOperator;

public class Approximate implements Problem {
    private static final double MAX_X = 1000;
    public static final double MIN_X = -MAX_X;
    private static final double RANGE = MAX_X - MIN_X;
    public static final int INSTANCES = 10000;
    public static final double increment = RANGE / INSTANCES;
    private final DoubleUnaryOperator f;

    public Approximate(DoubleUnaryOperator f) {
        this.f = f;
    }

    @Override
    public double test(Network network) {
        double error = 0;
        for (int i = 0; i < INSTANCES; i++) {
//            double x = Utils.random(MIN_X, MAX_X);
            double x = MIN_X + i * increment;

            double hx = network.compute(x)[0];
            error += Math.abs(hx - f.applyAsDouble(x));
        }

        if (Double.isNaN(error)) return Double.NEGATIVE_INFINITY;
        return -error / INSTANCES;
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
