package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public class Constant extends InputlessNeuron {
    @Override
    protected double doGet(Network network) {
        return network.getConst(this);
    }

    @Override
    public String getSymbol() {
        return "CONST";
//        return "CONST#" + Integer.toHexString(hashCode());
    }
}