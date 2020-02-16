package tobi.reinforcement.problems.gym;

import tobi.gym.*;
import tobi.gym.util.BoxBoxEnvironment;

import java.io.IOException;
import java.net.URISyntaxException;


public class BoxBoxGym extends Gym<Box, Box> {
    public BoxBoxGym(String envId) throws InterruptedException, IOException, URISyntaxException {
        super(envId);
    }

    @Override
    protected int getLabelLength(Environment<Box, Box> env) {
        return ((BoxBoxEnvironment) env).getActionSpace().getArraySize();
    }

    @Override
    protected int getInstanceLength(Environment<Box, Box> env) {
        return ((BoxBoxEnvironment) env).getObservationSpace().getArraySize();
    }

    @Override
    protected Environment<Box, Box> generateEnv(String envId, boolean render) throws IOException {
        return new BoxBoxEnvironment(envId, render, gym);
    }

    @Override
    protected Box formatAction(double[] actions) {
        return new Box(actions);
    }

    @Override
    protected double[] formatObservation(Box observation) {
        return observation.toArray();
    }

}
