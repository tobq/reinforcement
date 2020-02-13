package tobi.reinforcement.problems;

import tobi.gym.ActionResult;
import tobi.gym.Box;
import tobi.gym.util.BoxBoxEnvironment;
import org.json.JSONException;
import tobi.reinforcement.network.Network;

import java.io.IOException;


public class BoxBoxGym {
    private static final int ITERATIONS = 1000;

    private final String envId;
    private final int instanceLength;
    private final int labelLength;


    public BoxBoxGym(String envId) {
        this.envId = envId;
        try (BoxBoxEnvironment env = new BoxBoxEnvironment(envId)) {
            instanceLength = env.getObservationSpace().getArraySize();
            labelLength = env.getActionSpace().getArraySize();
        }
    }

    public double test(Network network, Integer seed) {
        double rewards = 0;
        try (BoxBoxEnvironment env = new BoxBoxEnvironment(envId)) {
            Box observation = env.reset();
            for (int i = 0; i < ITERATIONS; i++) {
//            System.out.println("observation = " + Arrays.toString(observation.toArray()));
                double[] actions = network.compute(observation.toArray());

                final ActionResult<Box> reply;
                try {
                    reply = env.step(new Box(actions));
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

    public String toString() {
        return "Gym<" + envId + ">";
    }

    public int getInstanceLength() {
        return instanceLength;
    }

    public int getLabelLength() {
        return labelLength;
    }
}
