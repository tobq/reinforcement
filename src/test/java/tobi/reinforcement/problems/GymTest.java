package tobi.reinforcement.problems;

import org.junit.jupiter.api.Test;
import tobi.gym.ActionResult;
import tobi.gym.Box;
import tobi.gym.Discrete;
import tobi.gym.Environment;
import tobi.gym.util.BoxBoxEnvironment;
import tobi.gym.util.BoxDiscreteEnvironment;
import tobi.gym.util.DiscreteDiscreteEnvironment;
import tobi.reinforcement.network.Network;

import java.util.Arrays;

class GymTest {

    @Test
    public void cartpole() throws Exception {
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#c7a977f\"],\"MULTIPLY#c7a977f\":[\"IN#0\",\"IN#3\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"SIN#6f95cd51\":[],\"IN#3\":[],\"OUT#0\":[],\"MULTIPLY#c7a977f\":[],\"OUT#1\":[\"IN#2\"]},\"inputs\":4}\n";
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"IN#0\"],\"OUT#1\":[\"IN#3\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#1\"]},\"inputs\":4}\n";
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\",\"IN#2\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[\"ADD#64a896b0\",\"IN#2\"],\"OUT#0\":[\"OUT#1\"],\"OUT#1\":[\"DIVIDE#219f4597\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#3\"]},\"inputs\":4}\n";
        String json = "{\"outputs\":2,\"constValues\":{\"CONST#46d8f407\":-0.07168003444536097},\"neuronInputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"CONST#46d8f407\",\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#577f9109\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[]},\"inputs\":4}\n";

//        for (int i = 0; i < 1000; i++)
        testGymEnv(json, "CartPole-v1");
    }

    @Test
    public void Acrobot() throws Exception {
        String json = "{\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"OUT#1\"],\"OUT#2\":[\"IN#5\"],\"OUT#1\":[\"IN#4\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#3\",\"IN#4\"],\"OUT#1\":[]},\"inputs\":6}\n";
        testGymEnv(json, "Acrobot-v1");
    }


    @Test
    public void Bipedal() throws Exception {
        Environment.init();

//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"DIVIDE#7823a2f9\":[\"IN#8\",\"IN#e\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"VAR#20e2cbe0\"],\"OUT#2\":[\"SIN#4c70fda8\"],\"OUT#1\":[\"DIVIDE#7823a2f9\"],\"OUT#3\":[\"VAR#20e2cbe0\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[\"ADD#91161c7\"],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[\"IN#0\",\"OUT#3\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"varOutputs\":{\"DIVIDE#7823a2f9\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#2\"],\"OUT#1\":[],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"inputs\":24}\n";
//        String json = "   {\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"SUBTRACT#5a564a61\"],\"OUT#2\":[\"IN#3\"],\"RELU#4c6a5712\":[\"ADD#4c32aa7e\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"RELU#4c6a5712\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[\"IN#14\",\"IN#f\"]},\"varOutputs\":{\"ADD#4c32aa7e\":[\"IN#14\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#d\",\"IN#b\"],\"OUT#2\":[],\"RELU#4c6a5712\":[],\"OUT#1\":[\"IN#17\"],\"OUT#3\":[\"IN#a\",\"IN#2\",\"IN#9\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[]},\"inputs\":24}";
//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"SUBTRACT#67f25c\":[\"IN#3\",\"IN#5\"],\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#f\"],\"OUT#2\":[\"SUBTRACT#67f25c\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#4c32aa7e\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"varOutputs\":{\"SUBTRACT#67f25c\":[],\"ADD#4c32aa7e\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#a\"],\"OUT#2\":[],\"OUT#1\":[\"IN#12\"],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"inputs\":24}";
//        String json = " {\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"SUBTRACT#5a564a61\"],\"OUT#2\":[\"IN#3\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#4c32aa7e\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[\"IN#14\",\"IN#f\"]},\"varOutputs\":{\"ADD#4c32aa7e\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#10\"],\"OUT#2\":[],\"OUT#1\":[\"IN#10\",\"IN#12\"],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[]},\"inputs\":24}";
        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#fcaf12a\":[\"IN#c\",\"OUT#1\"],\"ADD#4c32aa7e\":[\"ADD#fcaf12a\",\"IN#7\"],\"ADD#19640d53\":[\"IN#c\",\"MAX#7335aae5\"],\"MAX#7335aae5\":[\"IN#7\",\"ADD#4c32aa7e\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#f\"],\"OUT#2\":[\"IN#3\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#19640d53\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"varOutputs\":{\"ADD#fcaf12a\":[],\"ADD#4c32aa7e\":[],\"ADD#19640d53\":[],\"MAX#7335aae5\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[],\"OUT#3\":[\"IN#17\",\"IN#6\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"inputs\":24}";
        testGymEnv(json, "BipedalWalker-v2");
    }

    @Test
    public void LunarLander() throws Exception {
        Environment.init();
        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"SUBTRACT#2375a976\":[\"DIVIDE#1e194966\",\"EXP#5eee3da9\"],\"DIVIDE#1e194966\":[\"IN#5\",\"ABS#34bf66af\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[\"IN#0\"],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[\"SUBTRACT#2375a976\"],\"IN#7\":[],\"OUT#1\":[\"IN#4\"],\"EXP#5eee3da9\":[\"IN#7\"]},\"varOutputs\":{\"SUBTRACT#2375a976\":[],\"DIVIDE#1e194966\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[],\"IN#7\":[],\"OUT#1\":[],\"EXP#5eee3da9\":[\"IN#6\"]},\"inputs\":8}";
        testGymEnv(json, "LunarLanderContinuous-v2");
    }

    @Test
    public void pendulum() throws Exception {
//        testGymEnv(json, "Pendulum-v0");
    }

    @Test
    public void Mountain() throws Exception {
        String json = " {\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[\"IN#1\"],\"OUT#2\":[\"OUT#1\"],\"OUT#1\":[\"OUT#0\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[]},\"inputs\":2}";
        testGymEnv(json, "MountainCar-v0");
    }

    private static void testGymEnv(String json, String envId) throws Exception {
        Environment.init();
        testGymEnv(json, envId, 1000);
    }

    private static void testGymEnv(String json, String envId, int iterations) throws Exception {
        Network network = Network.parse(json);
        double rewards = 0;
        try (BoxDiscreteEnvironment env = new BoxDiscreteEnvironment(envId, true)) {

            Box observation = env.reset();
            for (int i = 0; i < iterations; i++) {
                double[] ob = observation.toArray();
//            System.out.println("ob = " + Arrays.toString(ob));
                double[] actions = network.compute(ob);

                int action = 0;
                double confidence = actions[action];
                for (int j = 1; j < actions.length; j++) {
                    double conf = actions[j];
                    if (conf > confidence) {
                        action = j;
                        confidence = actions[action];
                    }
                }

                ActionResult<Box> reply = env.step(new Discrete(action));
                rewards += reply.getReward();
                observation = reply.getObservation();
            }
        }
        System.out.println("AVG REWARD: " + rewards / iterations);
        System.out.println("TOTAL REWARD: " + rewards);
    }

    private static void testGymEnvBox(String json, String envId, int iterations) throws Exception {
        Network network = Network.parse(json);
        double rewards = 0;
        try (BoxBoxEnvironment env = new BoxBoxEnvironment(envId, true)) {

            Box observation = env.reset();
            for (int i = 0; i < iterations; i++) {
                double[] ob = observation.toArray();
//            System.out.println("ob = " + Arrays.toString(ob));
                double[] actions = network.compute(ob);

                ActionResult<Box> reply = env.step(new Box(actions));
                rewards += reply.getReward();
                observation = reply.getObservation();
            }
        }
        System.out.println("AVG REWARD: " + rewards / iterations);
        System.out.println("TOTAL REWARD: " + rewards);
    }
}