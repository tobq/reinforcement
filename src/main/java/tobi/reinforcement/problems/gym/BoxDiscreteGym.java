package tobi.reinforcement.problems.gym;

import tobi.gym.Box;
import tobi.gym.Discrete;
import tobi.gym.Environment;
import tobi.gym.util.BoxDiscreteEnvironment;

import java.io.IOException;
import java.net.URISyntaxException;


public class BoxDiscreteGym extends Gym<Box, Discrete> {
    public BoxDiscreteGym(String envId) throws InterruptedException, IOException, URISyntaxException {
        super(envId);
    }

    @Override
    protected int getLabelLength(Environment<Box, Discrete> env) {
        return ((BoxDiscreteEnvironment) env).getActionSpace().getSize();
    }

    @Override
    protected int getInstanceLength(Environment<Box, Discrete> env) {
        return ((BoxDiscreteEnvironment) env).getObservationSpace().getArraySize();
    }

    @Override
    protected BoxDiscreteEnvironment generateEnv(String envId, boolean render) {
        return new BoxDiscreteEnvironment(envId, render, gym);
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
    protected double[] formatObservation(Box observation) {
        return observation.toArray();
    }
}
