package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public class Variable extends InputlessNeuron {
    @Override
    double doGet(Network network) {
        return network.getVar(this);
    }

    @Override
    public String getSymbol() {
        return "VAR";
    }
}

