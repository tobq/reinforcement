package tobi.reinforcement.problems;

import org.junit.jupiter.api.Test;
import tobi.reinforcement.DebugUtils;
import tobi.reinforcement.network.Network;
import tobi.reinforcement.problems.gym.BoxBoxGym;
import tobi.reinforcement.problems.gym.BoxDiscreteGym;
import tobi.reinforcement.problems.gym.DiscreteBoxGym;

import java.util.Arrays;

class GymTest {

    @Test
    public void cartpole() throws Exception {
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#c7a977f\"],\"MULTIPLY#c7a977f\":[\"IN#0\",\"IN#3\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"SIN#6f95cd51\":[],\"IN#3\":[],\"OUT#0\":[],\"MULTIPLY#c7a977f\":[],\"OUT#1\":[\"IN#2\"]},\"inputs\":4}\n";
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"IN#0\"],\"OUT#1\":[\"IN#3\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#1\"]},\"inputs\":4}\n";
//        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\",\"IN#2\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[\"ADD#64a896b0\",\"IN#2\"],\"OUT#0\":[\"OUT#1\"],\"OUT#1\":[\"DIVIDE#219f4597\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#64a896b0\":[\"IN#3\"],\"IN#2\":[],\"IN#3\":[],\"DIVIDE#219f4597\":[],\"OUT#0\":[],\"OUT#1\":[\"IN#3\"]},\"inputs\":4}\n";
        String json = "{\"outputs\":2,\"constValues\":{\"CONST#46d8f407\":-0.07168003444536097},\"neuronInputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"CONST#46d8f407\",\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"MULTIPLY#577f9109\"],\"OUT#1\":[\"IN#2\"]},\"varOutputs\":{\"IN#0\":[],\"CONST#46d8f407\":[],\"MULTIPLY#577f9109\":[\"IN#3\"],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#1\":[]},\"inputs\":4}\n";

        final Network network = Network.parse(json);
        try (BoxDiscreteGym gym = new BoxDiscreteGym("CartPole-v1")) {
//            for (int i = 0; i < 1000; i++)
            gym.test(network, true, Integer.MAX_VALUE, 0);
        }
    }

    @Test
    public void lunarlandercontinuous() throws Exception {
        String json = "{\"outputs\":2,\"constValues\":{\"CONST#a826ff8\":-1.7502161281566444},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"IN#6\"],\"IN#6\":[],\"IN#7\":[],\"OUT#1\":[\"IN#3\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"CONST#a826ff8\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"IN#6\":[],\"IN#7\":[],\"OUT#1\":[]},\"inputs\":8}";
        final Network network = Network.parse(json);
        try (BoxBoxGym gym = new BoxBoxGym("LunarLanderContinuous-v2")) {
            for (int i = 0; i < 1000; i++)
                gym.test(network, true, 100, 0);
        }
    }

    @Test
    public void Acrobot() throws Exception {
        String json = "{\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"OUT#1\"],\"OUT#2\":[\"IN#5\"],\"OUT#1\":[\"IN#4\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#3\",\"IN#4\"],\"OUT#1\":[]},\"inputs\":6}";
        final Network network = Network.parse(json);
        try (BoxDiscreteGym gym = new BoxDiscreteGym("Acrobot-v1")) {
            gym.test(network, true, 0);
        }
    }


    @Test
    public void Bipedal() throws Exception {
//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"DIVIDE#7823a2f9\":[\"IN#8\",\"IN#e\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"VAR#20e2cbe0\"],\"OUT#2\":[\"SIN#4c70fda8\"],\"OUT#1\":[\"DIVIDE#7823a2f9\"],\"OUT#3\":[\"VAR#20e2cbe0\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[\"ADD#91161c7\"],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[\"IN#0\",\"OUT#3\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"varOutputs\":{\"DIVIDE#7823a2f9\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#2\"],\"OUT#1\":[],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"SIN#4c70fda8\":[],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"ADD#91161c7\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"VAR#20e2cbe0\":[]},\"inputs\":24}\n";
//        String json = "   {\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"SUBTRACT#5a564a61\"],\"OUT#2\":[\"IN#3\"],\"RELU#4c6a5712\":[\"ADD#4c32aa7e\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"RELU#4c6a5712\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[\"IN#14\",\"IN#f\"]},\"varOutputs\":{\"ADD#4c32aa7e\":[\"IN#14\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#d\",\"IN#b\"],\"OUT#2\":[],\"RELU#4c6a5712\":[],\"OUT#1\":[\"IN#17\"],\"OUT#3\":[\"IN#a\",\"IN#2\",\"IN#9\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[]},\"inputs\":24}";
//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"SUBTRACT#67f25c\":[\"IN#3\",\"IN#5\"],\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#f\"],\"OUT#2\":[\"SUBTRACT#67f25c\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#4c32aa7e\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"varOutputs\":{\"SUBTRACT#67f25c\":[],\"ADD#4c32aa7e\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#a\"],\"OUT#2\":[],\"OUT#1\":[\"IN#12\"],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"inputs\":24}";
//        String json = " {\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#4c32aa7e\":[\"OUT#1\",\"IN#7\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"SUBTRACT#5a564a61\"],\"OUT#2\":[\"IN#3\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#4c32aa7e\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[\"IN#14\",\"IN#f\"]},\"varOutputs\":{\"ADD#4c32aa7e\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#10\"],\"OUT#2\":[],\"OUT#1\":[\"IN#10\",\"IN#12\"],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"SUBTRACT#5a564a61\":[]},\"inputs\":24}";
//        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#fcaf12a\":[\"IN#c\",\"OUT#1\"],\"ADD#4c32aa7e\":[\"ADD#fcaf12a\",\"IN#7\"],\"ADD#19640d53\":[\"IN#c\",\"MAX#7335aae5\"],\"MAX#7335aae5\":[\"IN#7\",\"ADD#4c32aa7e\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#f\"],\"OUT#2\":[\"IN#3\"],\"OUT#1\":[\"IN#c\"],\"OUT#3\":[\"ADD#19640d53\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"varOutputs\":{\"ADD#fcaf12a\":[],\"ADD#4c32aa7e\":[],\"ADD#19640d53\":[],\"MAX#7335aae5\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[],\"OUT#3\":[\"IN#17\",\"IN#6\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"inputs\":24}";
//        String json2 = "{\"outputs\":4,\"constValues\":{\"CONST#7bdc5700\":0},\"neuronInputs\":{\"SIN#31ecb361\":[\"SUBTRACT#1d2def55\"],\"DIVIDE#56d87044\":[\"SUBTRACT#719d2bdc\",\"EXP#2823a822\"],\"MAX#1eb6037d\":[\"DIVIDE#50930bff\",\"MIN#50b03929\"],\"CONST#7bdc5700\":[],\"SUBTRACT#1d2def55\":[\"IN#c\",\"IN#a\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"RELU#186481d4\":[\"MAX#3b5c6eee\"],\"OUT#0\":[\"SIN#31ecb361\"],\"OUT#2\":[\"CONST#7bdc5700\"],\"ABS#77a19366\":[\"ABS#28cf3047\"],\"OUT#1\":[\"MIN#238869e8\"],\"MAX#3b5c6eee\":[\"ABS#3ed79f41\",\"SIN#31ecb361\"],\"MIN#238869e8\":[\"IN#2\",\"ABS#77a19366\"],\"OUT#3\":[\"MAX#1eb6037d\"],\"ADD#1717302b\":[\"RELU#186481d4\",\"MIN#c5a82d6\"],\"IN#a\":[],\"ABS#28cf3047\":[\"SIN#10cb1e30\"],\"MIN#c5a82d6\":[\"OUT#0\",\"IN#17\"],\"IN#d\":[],\"MIN#50b03929\":[\"ADD#1717302b\",\"IN#0\"],\"IN#e\":[],\"IN#b\":[],\"SIN#10cb1e30\":[\"DIVIDE#56d87044\"],\"IN#c\":[],\"IN#13\":[],\"SUBTRACT#719d2bdc\":[\"IN#16\",\"IN#6\"],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"ABS#3ed79f41\":[\"IN#f\"],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"EXP#2823a822\":[\"IN#16\"],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"DIVIDE#50930bff\":[\"OUT#0\",\"ABS#3ed79f41\"]},\"varOutputs\":{\"SIN#31ecb361\":[\"IN#14\"],\"DIVIDE#56d87044\":[],\"MAX#1eb6037d\":[\"IN#e\"],\"CONST#7bdc5700\":[],\"SUBTRACT#1d2def55\":[\"IN#17\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"RELU#186481d4\":[\"IN#6\"],\"OUT#0\":[\"IN#d\",\"IN#e\"],\"OUT#2\":[\"IN#b\",\"IN#14\"],\"ABS#77a19366\":[\"IN#1\"],\"OUT#1\":[\"IN#5\",\"IN#11\",\"IN#10\",\"IN#14\"],\"MAX#3b5c6eee\":[\"IN#b\"],\"MIN#238869e8\":[\"IN#3\",\"IN#2\"],\"OUT#3\":[\"IN#8\",\"IN#6\"],\"ADD#1717302b\":[],\"IN#a\":[],\"ABS#28cf3047\":[],\"MIN#c5a82d6\":[\"IN#2\"],\"IN#d\":[],\"MIN#50b03929\":[],\"IN#e\":[],\"IN#b\":[],\"SIN#10cb1e30\":[],\"IN#c\":[],\"IN#13\":[],\"SUBTRACT#719d2bdc\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"ABS#3ed79f41\":[\"IN#5\",\"IN#12\"],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"EXP#2823a822\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"DIVIDE#50930bff\":[]},\"inputs\":24}";

//      20/03/2020 18:25
        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"SIG#16022d9d\":[{\"strength\":0.17096832867463851,\"neuron\":\"IN#9\"}],\"MIN#376b4233\":[{\"strength\":-0.2780681773072706,\"neuron\":\"MULTIPLY#1da51a35\"},{\"strength\":2.255470117955606,\"neuron\":\"IN#10\"}],\"SIN#b49c85f\":[{\"strength\":0.4931034788482721,\"neuron\":\"MIN#376b4233\"}],\"SUBTRACT#35a50a4c\":[{\"strength\":0.9415588554277767,\"neuron\":\"SIG#16022d9d\"},{\"strength\":0.5392127063733579,\"neuron\":\"SIN#5d11346a\"}],\"MULTIPLY#1da51a35\":[{\"strength\":0.5716729261280462,\"neuron\":\"SIG#16022d9d\"},{\"strength\":1.6576560720518694,\"neuron\":\"IN#7\"}],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[{\"strength\":0.1697268233007826,\"neuron\":\"IN#b\"}],\"OUT#2\":[{\"strength\":0.03277424223048098,\"neuron\":\"ABS#4fcd19b3\"}],\"OUT#1\":[{\"strength\":0.9062322223449814,\"neuron\":\"SUBTRACT#35a50a4c\"}],\"OUT#3\":[{\"strength\":0.6189824512450861,\"neuron\":\"MULTIPLY#1da51a35\"}],\"IN#a\":[],\"ABS#4fcd19b3\":[{\"strength\":0.13236688764784788,\"neuron\":\"SIN#b49c85f\"}],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"SIN#5d11346a\":[{\"strength\":0.6490628750388859,\"neuron\":\"ADD#3b94d659\"}],\"ADD#3b94d659\":[{\"strength\":0.03266448134026312,\"neuron\":\"IN#4\"},{\"strength\":-0.6258535057199397,\"neuron\":\"IN#5\"}],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"varOutputs\":{\"SIG#16022d9d\":[],\"MIN#376b4233\":[],\"SIN#b49c85f\":[],\"SUBTRACT#35a50a4c\":[],\"MULTIPLY#1da51a35\":[],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[],\"OUT#3\":[],\"IN#a\":[],\"ABS#4fcd19b3\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"SIN#5d11346a\":[],\"ADD#3b94d659\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[]},\"inputs\":24}";
        try (BoxBoxGym gym = new BoxBoxGym("BipedalWalker-v3")) {
            for (int i = 0; i < 1000; i++)
                gym.test(Network.parse(json), true, 0);
        }
    }

    @Test
    public void BipedalHardcore() throws Exception {
        final String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"IN#10\":[],\"RELU#18316934\":[\"IN#15\"],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"MIN#1a1d7832\"],\"OUT#2\":[\"SIN#5c275b83\"],\"OUT#1\":[\"ABS#35d44cd3\"],\"ABS#35d44cd3\":[\"IN#a\"],\"OUT#3\":[\"IN#2\"],\"ADD#4306709f\":[\"IN#c\",\"IN#3\"],\"IN#a\":[],\"SUBTRACT#40e28324\":[\"IN#e\",\"ADD#4306709f\"],\"SIN#5c275b83\":[\"MULTIPLY#21a462ce\"],\"IN#d\":[],\"MAX#5c281ccb\":[\"ABS#35d44cd3\",\"IN#8\"],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"MULTIPLY#21a462ce\":[\"MAX#5c281ccb\",\"SUBTRACT#40e28324\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"MIN#1a1d7832\":[\"RELU#18316934\",\"ADD#4306709f\"],\"IN#7\":[]},\"varOutputs\":{\"IN#10\":[],\"RELU#18316934\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[\"IN#16\",\"IN#8\",\"IN#b\"],\"OUT#1\":[\"IN#4\",\"IN#12\",\"IN#5\",\"IN#1\",\"IN#8\"],\"ABS#35d44cd3\":[\"IN#14\",\"IN#16\",\"IN#1\"],\"OUT#3\":[\"IN#f\"],\"ADD#4306709f\":[],\"IN#a\":[],\"SUBTRACT#40e28324\":[\"IN#d\"],\"SIN#5c275b83\":[],\"IN#d\":[],\"MAX#5c281ccb\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"MULTIPLY#21a462ce\":[\"IN#11\",\"IN#13\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"MIN#1a1d7832\":[\"IN#17\"],\"IN#7\":[]},\"inputs\":24}";
        try (BoxBoxGym gym = new BoxBoxGym("BipedalWalkerHardcore-v3")) {
            for (int i = 0; i < 1000; i++)
                gym.test(Network.parse(json), true, 0);
        }
    }

    @Test
    public void LunarLander() throws Exception {
        String json = "{\"outputs\":2,\"constValues\":{},\"neuronInputs\":{\"SUBTRACT#2375a976\":[\"DIVIDE#1e194966\",\"EXP#5eee3da9\"],\"DIVIDE#1e194966\":[\"IN#5\",\"ABS#34bf66af\"],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[\"IN#0\"],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[\"SUBTRACT#2375a976\"],\"IN#7\":[],\"OUT#1\":[\"IN#4\"],\"EXP#5eee3da9\":[\"IN#7\"]},\"varOutputs\":{\"SUBTRACT#2375a976\":[],\"DIVIDE#1e194966\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"ABS#34bf66af\":[],\"IN#3\":[],\"IN#6\":[],\"OUT#0\":[],\"IN#7\":[],\"OUT#1\":[],\"EXP#5eee3da9\":[\"IN#6\"]},\"inputs\":8}";
        final Network network = Network.parse(json);
        try (BoxBoxGym gym = new BoxBoxGym("LunarLanderContinuous-v2")) {
            gym.test(network, true, 0);
        }
    }

    @Test
    public void pendulum() throws Exception {
//        testGymEnv(json, "Pendulum-v0");
    }

    //    @Test
    public static void main(String[] args) {
//    public void XOR(){
//        String json = "{\"outputs\":1,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"ABS#542aa32a\":[{\"strength\":0.6093020621799065,\"neuron\":\"SUBTRACT#5dcaa37a\"}],\"SUBTRACT#5dcaa37a\":[{\"strength\":1.0522973863247547,\"neuron\":\"IN#0\"},{\"strength\":0.29214384813516614,\"neuron\":\"IN#1\"}],\"OUT#0\":[{\"strength\":0.7048253621922413,\"neuron\":\"ABS#542aa32a\"}]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"ABS#542aa32a\":[],\"SUBTRACT#5dcaa37a\":[],\"OUT#0\":[\"IN#1\"]},\"inputs\":2}";
        String json = "{\"outputs\":1,\"constValues\":{\"CONST#4c67cd9b\":-1},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#7a05671c\":[{\"strength\":1.3573650209440797,\"neuron\":\"MULTIPLY#7ef79209\"},{\"strength\":0.42643050418755957,\"neuron\":\"IN#1\"}],\"MULTIPLY#7e9c257f\":[{\"strength\":0.9781877305214235,\"neuron\":\"ADD#7a05671c\"},{\"strength\":0.5013741592939107,\"neuron\":\"ADD#7a05671c\"}],\"OUT#0\":[{\"strength\":0.7245815991298363,\"neuron\":\"MULTIPLY#7e9c257f\"}],\"CONST#4c67cd9b\":[],\"MULTIPLY#7ef79209\":[{\"strength\":0.45763520377401967,\"neuron\":\"IN#0\"},{\"strength\":0.8049105040477885,\"neuron\":\"CONST#4c67cd9b\"}]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"ADD#7a05671c\":[],\"MULTIPLY#7e9c257f\":[],\"OUT#0\":[],\"CONST#4c67cd9b\":[],\"MULTIPLY#7ef79209\":[]},\"inputs\":2}";
        Network network = Network.parse(json);
        double[] result = network.compute(0, 0);
        System.out.println("result = " + Arrays.toString(result));
        DebugUtils.showGraph(network);
        DebugUtils.buildGraph(network);

    }

    @Test
    public void hottercolder() throws Exception {
        final String json = "{\"outputs\":1,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[\"IN#0\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"IN#2\":[],\"IN#3\":[],\"OUT#0\":[]},\"inputs\":4}";
        final Network network = Network.parse(json);
        try (DiscreteBoxGym gym = new DiscreteBoxGym("HotterColder-v0")) {
            gym.test(network, true, 0);
        }
//        testGymEnv(json, "Pendulum-v0");
    }

    @Test
    public void Mountain() throws Exception {
        String json = " {\"outputs\":3,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[\"IN#1\"],\"OUT#2\":[\"OUT#1\"],\"OUT#1\":[\"OUT#0\"]},\"varOutputs\":{\"IN#0\":[],\"IN#1\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[]},\"inputs\":2}";
        final Network network = Network.parse(json);
        try (BoxDiscreteGym gym = new BoxDiscreteGym("MountainCar-v0")) {
            gym.test(network, true, 0);
        }
    }
}