package tobi.reinforcement.problems;

import tobi.reinforcement.DebugUtils;
import tobi.reinforcement.network.Network;

class MemoriseTest {
    public static void main(String[] args) {
        String json = "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"VAR#7d371e12\":[],\"VAR#6ff9da4d\":[],\"OUT#0\":[\"VAR#6ff9da4d\"],\"OUT#2\":[\"VAR#1e19635d\"],\"VAR#1e19635d\":[],\"OUT#1\":[\"VAR#7d371e12\"],\"OUT#3\":[\"IN#0\"]},\"varOutputs\":{\"IN#0\":[],\"VAR#7d371e12\":[],\"VAR#6ff9da4d\":[],\"OUT#0\":[],\"OUT#2\":[\"VAR#7d371e12\"],\"VAR#1e19635d\":[],\"OUT#1\":[\"VAR#6ff9da4d\"],\"OUT#3\":[\"VAR#1e19635d\",\"IN#0\"]},\"inputs\":1}";
        Network network = Network.parse(json);
        DebugUtils.showGraph(network);
    }
}