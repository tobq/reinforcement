package tobi.reinforcement.problems.gym;

import tobi.gym.*;
import tobi.gym.Discrete;
import tobi.gym.util.DiscreteDiscreteEnvironment;

import java.io.IOException;
import java.net.URISyntaxException;


public class DiscreteDiscreteGym extends Gym<Discrete, Discrete> {
    public DiscreteDiscreteGym(String envId) throws InterruptedException, IOException, URISyntaxException {
        super(envId);
    }

    @Override
    protected int getLabelLength(Environment<Discrete, Discrete> env) {
        return ((DiscreteDiscreteEnvironment) env).getActionSpace().getSize();
    }

    @Override
    protected int getInstanceLength(Environment<Discrete, Discrete> env) {
        return ((DiscreteDiscreteEnvironment) env).getObservationSpace().getSize();
    }

    @Override
    protected DiscreteDiscreteEnvironment generateEnv(String envId, boolean render) throws IOException {
        return new DiscreteDiscreteEnvironment(envId, render, gym);
    }

    @Override
    protected Discrete formatAction(double[] actions) {
        int action = 0;
        double confidence = actions[action];
        for (int j = 1; j < actions.length; j++) {
            double conf = actions[j];
            if (conf > confidence) {
                action = j;
                confidence = actions[action];
            }
        }
        return new Discrete(action);
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
