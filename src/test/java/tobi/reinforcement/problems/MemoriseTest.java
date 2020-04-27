package tobi.reinforcement.problems;

import tobi.reinforcement.DebugUtils;
import tobi.reinforcement.network.Network;

import java.util.Arrays;

class MemoriseTest {
    public static void main(String[] args) {
        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"T1#2e866175\":[{\"strength\":-0.13784615725772653,\"neuron\":\"T1#1b1b5fc1\"}],\"OUT#0\":[{\"strength\":0.0967117531064492,\"neuron\":\"T1#62d7c6a3\"}],\"T1#1b1b5fc1\":[{\"strength\":0.7379538170782397,\"neuron\":\"IN#0\"}],\"OUT#2\":[{\"strength\":0.5034824618572035,\"neuron\":\"OUT#3\"}],\"OUT#1\":[{\"strength\":0.8322187422399921,\"neuron\":\"T1#380739e8\"}],\"T1#6e1cc424\":[{\"strength\":0.655392307936177,\"neuron\":\"T1#1b1b5fc1\"}],\"T1#380739e8\":[{\"strength\":0.22352939911214953,\"neuron\":\"T1#2e866175\"}],\"T1#62d7c6a3\":[{\"strength\":0.18837894166311142,\"neuron\":\"T1#6e1cc424\"}],\"T1#36ef7a81\":[{\"strength\":0.09824844070561667,\"neuron\":\"IN#0\"}],\"OUT#3\":[{\"strength\":0.5423641002650395,\"neuron\":\"T1#36ef7a81\"}]},\"varOutputs\":{\"IN#0\":[],\"T1#2e866175\":[],\"OUT#0\":[],\"T1#1b1b5fc1\":[],\"OUT#2\":[],\"OUT#1\":[],\"T1#6e1cc424\":[],\"T1#380739e8\":[],\"T1#62d7c6a3\":[],\"T1#36ef7a81\":[],\"OUT#3\":[]},\"inputs\":1}";
        String json2 = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"VAR#37fdd626\":[],\"OUT#0\":[{\"strength\":0.17236745346205729,\"neuron\":\"VAR#71dd492c\"}],\"VAR#71dd492c\":[],\"OUT#2\":[{\"strength\":0.8675902785267354,\"neuron\":\"VAR#37fdd626\"}],\"OUT#1\":[{\"strength\":0.5787639596681496,\"neuron\":\"VAR#4c39ec4d\"}],\"VAR#4c39ec4d\":[],\"OUT#3\":[{\"strength\":0.28617609713665526,\"neuron\":\"IN#0\"}]},\"varOutputs\":{\"IN#0\":[\"VAR#37fdd626\"],\"VAR#37fdd626\":[],\"OUT#0\":[],\"VAR#71dd492c\":[],\"OUT#2\":[\"VAR#4c39ec4d\"],\"OUT#1\":[\"VAR#71dd492c\",\"VAR#4c39ec4d\"],\"VAR#4c39ec4d\":[],\"OUT#3\":[\"VAR#37fdd626\"]},\"inputs\":1}";
        Network network = Network.parse(json);
        Network network2 = Network.parse(json2);
//        network.compute(12);
//        network.compute(1);
//        network.compute(6);
//        System.out.println(Arrays.toString(network.compute(-3)));
        DebugUtils.showGraph(network);
        DebugUtils.showGraph(network2);
    }
}