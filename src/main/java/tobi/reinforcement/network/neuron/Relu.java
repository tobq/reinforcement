package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public final  class Relu extends Neuron {

    @Override
    protected double doCompute(Network network, double... inputs) {
        return Math.max(0D, inputs[0]);
    }

    @Override
    public int getInputCount() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "RELU";
    }
}
