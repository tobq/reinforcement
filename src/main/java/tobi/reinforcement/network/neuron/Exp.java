package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public  final class Exp extends Neuron {
    @Override
    protected double doCompute(Network network, double... inputs) {
        return Math.exp(inputs[0]);
    }

    @Override
    public int getInputCount() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "EXP";
    }
}
