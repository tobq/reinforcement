package tobi.reinforcement.problems.gym;

import org.json.JSONException;
import tobi.gym.ActionResult;
import tobi.gym.Environment;
import tobi.gym.SpaceInstance;
import tobi.reinforcement.network.Network;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Gym<O extends SpaceInstance, A extends SpaceInstance> implements AutoCloseable {
    protected static final int ITERATIONS = 1000;
    protected final String envId;
    protected final int instanceLength;
    protected final int labelLength;
    protected final tobi.gym.Gym gym;

    public Gym(String envId) throws InterruptedException, IOException, URISyntaxException {
        this.envId = envId;
        this.gym = new tobi.gym.Gym();
        try (Environment<O, A> env = generateEnv(envId)) {
            instanceLength = getInstanceLength(env);
            labelLength = getLabelLength(env);
        }
    }

    public double test(Network network, boolean render, int seed) throws InterruptedException {
        return test(network, render, ITERATIONS, seed);
    }

    public double test(Network network, boolean render, int iterations, int seed) throws InterruptedException {
        double rewards = 0;
        try (Environment<O, A> env = generateEnv(envId, render)) {
            O observation = env.reset();
            for (int i = 0; i < iterations; i++) {
//            System.out.println("observation = " + Arrays.toString(observation.toArray()));
                double[] actions = network.compute(formatObservation(observation));

                final ActionResult<O> reply;
                try {
                    reply = env.step(formatAction(actions));
                } catch (JSONException e) {
                    rewards = Double.NEGATIVE_INFINITY;
                    break;
                }
                double reward = reply.getReward();
                observation = reply.getObservation();

                rewards += reward;
//                System.out.println("Reward: " + reward);
            }
//        System.out.println("average reward: " + rewards / ITERATIONS / EPISODES);
        }
        return rewards;
    }


    protected abstract int getLabelLength(Environment<O, A> env);

    protected abstract int getInstanceLength(Environment<O, A> env);

    protected Environment<O, A> generateEnv(String envId) {
        return generateEnv(envId, false);
    }

    protected abstract Environment<O, A> generateEnv(String envId, boolean render);

    protected abstract A formatAction(double[] actions);

    protected abstract double[] formatObservation(O observation);

    @Override
    public void close() throws InterruptedException {
        gym.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "<" + envId + ">";
    }

    public int getInstanceLength() {
        return instanceLength;
    }

    public int getLabelLength() {
        return labelLength;
    }
}
