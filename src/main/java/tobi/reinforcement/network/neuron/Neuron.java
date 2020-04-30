package tobi.reinforcement.network.neuron;

import tobi.reinforcement.Utils;
import tobi.reinforcement.network.Network;
import tobi.reinforcement.network.Synapse;

import java.util.Arrays;

import static tobi.reinforcement.network.Network.NEURON_ID_PREFIX;

abstract public class Neuron {
    final public double compute(Network network) {
        final Synapse[] inputs = network.getInputs(this);
//        System.out.println("COMPUTING: "+this+", \t\t\t\t\t INPUTS = "+ Arrays.toString(Arrays.stream(inputs).map(Synapse::getNeuron).toArray()));
        double[] inputValues = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            inputValues[i] = inputs[i].getNeuron().compute(network);
        }
        double value = doCompute(network, inputValues);
        network.updateVarOutputs(this, value);

        return value;
    }

//    public static InputlessNeuron NULL = new InputlessNeuron() {
//        @Override
//        public String getSymbol() {
//            return "NULL";
//        }
//
//        @Override
//        protected double doGet(Network network) {
//            return 0;
//        }
//    };

    protected abstract double doCompute(Network network, double... inputs);

    public abstract int getInputCount();

    public String serialise(Network network) {
        return toString() + "(" + getSerialParams(network) + ")";
    }

    protected String getSerialParams(Network network) {
        return Utils.join(network.getInputs(this), n -> n.getNeuron().serialise(network));
    }

    public String toString() {
        return getSymbol() + NEURON_ID_PREFIX + getID();
    }

    protected String getID() {
        return Integer.toHexString(hashCode());
    }

    public abstract String getSymbol();
}