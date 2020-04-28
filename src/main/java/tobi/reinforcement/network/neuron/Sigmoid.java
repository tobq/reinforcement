package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public  final class Sigmoid extends Neuron {
    @Override
    protected double doCompute(Network network, double... inputs) {
        final double input = inputs[0];
        return 1 / (1 + Math.exp(-input));
//        return input / (1 + Math.abs(input));
    }

    @Override
    public int getInputCount() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "SIG";
    }
}
