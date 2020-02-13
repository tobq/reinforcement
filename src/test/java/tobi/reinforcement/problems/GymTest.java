package tobi.reinforcement.problems;//package tobi.reinforcement.problems;
//
//import org.junit.jupiter.api.Test;
//import tobi.reinforcement.network.Network;
//import tobi.reinforcement.problems.Gym.ActionResult;
//import tobi.reinforcement.problems.Gym.Box;
//import tobi.reinforcement.problems.Gym.BoxBoxEnvironment;
//import tobi.reinforcement.problems.Gym.Environment;
//
//import java.util.Arrays;
//
//class GymTest {
//
//    @Test
//    public void cartpole() throws Exception {
////        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#c7a977f\"],\"MULTIPLY#c7a977f\":[\"IN#0\",\"IN#3\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"SIN#6f95cd51\":[],\"IN#3\":[],\"OUT#0\":[],\"MULTIPLY#c7a977f\":[],\"OUT#1\":[\"IN#2\"]},\"inputs\":4}\n";
////        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"IN#0\"],\"OUT#1\":[\"IN#3\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#1\"]},\"inputs\":4}\n";
////        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\",\"IN#2\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[\"ADD#64a896b0\",\"IN#2\"],\"OUT#0\":[\"OUT#1\"],\"OUT#1\":[\"DIVIDE#219f4597\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#3\"]},\"inputs\":4}\n";
//        String json = "{\"outputs\":2,\"constValues\":{\"CONST#46d8f407\":-0.07168003444536097},\"neuronInputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"CONST#46d8f407\",\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#577f9109\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[]},\"inputs\":4}\n";
//
//        for (int i = 0; i < 1000; i++)
//            testGymEnv(json, "CartPole-v1");
//    }
//
//    @Test
//    public void Acrobot() throws Exception {
//        String json = "{\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"OUT#1\"],\"OUT#2\":[\"IN#5\"],\"OUT#1\":[\"IN#4\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#3\",\"IN#4\"],\"OUT#1\":[]},\"inputs\":6}\n";
//        testGymEnv(json, "Acrobot-v1");
//    }
//
//
//    @Test
//    public void Bipedal() throws Exception {
//        Environment.init();
//
//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"DIVIDE#7823a2f9\":[\"IN#8\",\"IN#e\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"VAR#20e2cbe0\"],\"OUT#2\":[\"SIN#4c70fda8\"],\"OUT#1\":[\"DIVIDE#7823a2f9\"],\"OUT#3\":[\"VAR#20e2cbe0\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[\"ADD#91161c7\"],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[\"IN#0\",\"OUT#3\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"varOutputs\":{\"DIVIDE#7823a2f9\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#2\"],\"OUT#1\":[],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"inputs\":24}\n";
//        testGymEnv(json, "BipedalWalker-v2");
//    }
//
//    @Test
//    public void LunarLander() throws Exception {
//        Environment.init();
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"SUBTRACT#2375a976\":[\"DIVIDE#1e194966\",\"EXP#5eee3da9\"],\"DIVIDE#1e194966\":[\"IN#5\",\"ABS#34bf66af\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[\"IN#0\"],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[\"SUBTRACT#2375a976\"],\"IN#7\":[],\"OUT#1\":[\"IN#4\"],\"EXP#5eee3da9\":[\"IN#7\"]},\"varOutputs\":{\"SUBTRACT#2375a976\":[],\"DIVIDE#1e194966\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[],\"IN#7\":[],\"OUT#1\":[],\"EXP#5eee3da9\":[\"IN#6\"]},\"inputs\":8}";
//        testGymEnv(json, "LunarLanderContinuous-v2");
//    }
//
//    @Test
//    public void pendulum() throws Exception {
////        testGymEnv(json, "Pendulum-v0");
//    }
//
//    @Test
//    public void Mountain() throws Exception {
//        String json = " {\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[\"IN#1\"],\"OUT#2\":[\"OUT#1\"],\"OUT#1\":[\"OUT#0\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[]},\"inputs\":2}";
//        testGymEnv(json, "MountainCar-v0");
//    }
//
//    private static void testGymEnv(String json, String envId) throws Exception {
//        testGymEnv(json, envId, 1000);
//    }
//
//    private static void testGymEnv(String json, String envId, int iterations) throws Exception {
//        Network network = Network.parse(json);
//        double rewards = 0;
//        try (BoxBoxEnvironment env = new BoxBoxEnvironment(envId, true)) {
//
//            Box observation = env.reset();
//            for (int i = 0; i < iterations; i++) {
//                double[] ob = observation.toArray();
////            System.out.println("ob = " + Arrays.toString(ob));
//                double[] actions = network.compute(ob);
//
////            int action = 0;
////            double confidence = actions[action];
////            for (int j = 1; j < actions.length; j++) {
////                double conf = actions[j];
////                if (conf > confidence) {
////                    action = j;
////                    confidence = actions[action];
////                }
////            }
//
//                ActionResult<Box> reply = env.step(new Box(actions));
//                rewards += reply.getReward();
//                observation = reply.getObservation();
//            }
//        }
//        System.out.println("AVG REWARD: " + rewards / iterations);
//        System.out.println("TOTAL REWARD: " + rewards);
//    }
//}