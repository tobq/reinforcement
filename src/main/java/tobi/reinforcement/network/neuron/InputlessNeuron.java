package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public abstract class InputlessNeuron extends Neuron {
    @Override
    protected double doCompute(Network network, double... inputs) {
        return doGet(network);
    }

    abstract double doGet(Network network);

    @Override
    final public int getInputCount() {
        return 0;
    }


    @Override
    public String getSerialParams(Network network) {
        return Double.toString(doGet(network));
    }
}