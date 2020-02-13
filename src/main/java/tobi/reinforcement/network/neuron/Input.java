package tobi.reinforcement.network.neuron;

import tobi.reinforcement.network.Network;

public class Input extends Variable {
    public static final String SYMBOL = "IN";
    private final String ID;

    @Override
    double doGet(Network network) {
        //        if (value == -1) new RuntimeException().printStackTrace();
        return super.doGet(network);
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
