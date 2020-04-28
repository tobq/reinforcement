package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public  final class Divide extends Neuron {

    @Override
    protected double doCompute(Network network, double... inputs) {
        return inputs[0] / inputs[1];
    }

    @Override
    public int getInputCount() {
        return 2;
    }

    @Override
    public String getSymbol() {
        return "DIVIDE";
    }
}
