package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public class Output extends Neuron {

    public static final String SYMBOL = "OUT";
    private final String ID;

    public Output(int index) {
        ID = Integer.toHexString(index);
    }

    @Override
    protected double doCompute(Network network, double... inputs) {
        return inputs[0];
    }

    @Override
    public int getInputCount() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    protected String getID() {
        return ID;
    }
}
