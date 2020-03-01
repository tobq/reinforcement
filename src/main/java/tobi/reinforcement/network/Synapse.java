package tobi.reinforcement.network;

import org.json.JSONObject;
import tobi.reinforcement.Utils;
import tobi.reinforcement.network.neuron.Neuron;

public class Synapse {
    private final Neuron neuron;
    private final double strength;

    public Synapse(Neuron neuron, double strength) {
        this.neuron = neuron;
        this.strength = strength;
    }

    public Synapse(Neuron newNeuron) {
        this(newNeuron, Utils.random.nextDouble());
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return serialise().toString();
    }

    JSONObject serialise() {
        return new JSONObject()
                .put("neuron", neuron.toString())
                .put("strength", strength);
    }
}
