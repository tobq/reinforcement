package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public  final class Input extends InputlessNeuron {
    public static final String SYMBOL = "IN";
    private final String ID;

    @Override
    double doGet(Network network) {
        return network.getInput(this);
    }

    public Input(int index) {
        ID = Integer.toHexString(index);
    }

    @Override
    protected String getID() {
        return ID;
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String serialise(Network network) {
        return toString();
    }
}
