package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public class T1 extends Neuron {
    @Override
    protected double doCompute(Network network, double... inputs) {
return         network.updateT1(this, inputs[0]);
    }

    @Override
    public int getInputCount() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "T1";
    }
}
