package tobi.reinforcement.problems;

import tobi.gym.ActionResult;
import tobi.gym.Box;
import tobi.gym.Discrete;
import tobi.gym.util.BoxDiscreteEnvironment;
import org.json.JSONException;
import tobi.reinforcement.network.Network;

import java.io.IOException;


public class BoxDiscreteGym {
    private static final int ITERATIONS = 1000;

    private final String envId;
    private final int instanceLength;
    private final int labelLength;


    public BoxDiscreteGym(String envId) {
        this.envId = envId;
        try (BoxDiscreteEnvironment env = new BoxDiscreteEnvironment(envId)) {
            instanceLength = env.getObservationSpace().getArraySize();
            labelLength = env.getActionSpace().getSize();
        }
    }

    public double test(Network network, Integer seed) {
        double rewards = 0;
        try (BoxDiscreteEnvironment env = new BoxDiscreteEnvironment(envId)) {
            Box observation = env.reset();
            for (int i = 0; i < ITERATIONS; i++) {
                double[] actions = network.compute(observation.toArray());
                int action = 0;
                double confidence = actions[action];
                for (int j = 1; j < actions.length; j++) {
                    double conf = actions[j];
                    if (conf > confidence) {
                        action = j;
                        confidence = actions[action];
                    }
                }

                final ActionResult<Box> reply;
                try {
                    reply = env.step(new Discrete(action));
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
