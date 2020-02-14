package tobi.reinforcement.problems.gym;

import tobi.gym.Box;
import tobi.gym.Discrete;
import tobi.gym.Environment;
import tobi.gym.util.DiscreteBoxEnvironment;

import java.io.IOException;
import java.net.URISyntaxException;


public class DiscreteBoxGym extends Gym<Discrete, Box> {
    public DiscreteBoxGym(String envId) throws InterruptedException, IOException, URISyntaxException {
        super(envId);
    }

    @Override
    protected int getLabelLength(Environment<Discrete, Box> env) {
        return ((DiscreteBoxEnvironment) env).getActionSpace().getArraySize();
    }

    @Override
    protected int getInstanceLength(Environment<Discrete, Box> env) {
        return ((DiscreteBoxEnvironment) env).getObservationSpace().getSize();
    }

    @Override
    protected DiscreteBoxEnvironment generateEnv(String envId, boolean render) {
        return new DiscreteBoxEnvironment(envId, render, gym);
    }

    @Override
    protected Box formatAction(double[] actions) {
        return new Box(actions);
    }

    @Override
    protected double[] formatObservation(Discrete observation) {
        final int value = observation.getValue();
        final double[] obArray = new double[instanceLength];
        for (int j = 0; j < obArray.length; j++) {
            obArray[j] = j == value ? 1 : 0;
        }
        return obArray;
    }
}

