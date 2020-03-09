package tobi.reinforcement.network;

import tobi.reinforcement.DebugUtils;
import tobi.reinforcement.Utils;
import tobi.reinforcement.network.neuron.*;
import tobi.reinforcement.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Network {
    public static final String NEURON_ID_PREFIX = "#";
    private final Input[] inputs;

    private final Output[] outputs;
    // todo: MERGE VAR OUTPUTS + NEURON INPUTS INTO ONE OBJECT, AND USE A NEURONENTRY OBJECT FOR in/out
    private final Map<Neuron, Synapse[]> neuronInputs;
    private final Map<Neuron, Set<Variable>> varOutputs;
    private final Map<Variable, Double> varValues;
    private final Map<Constant, Double> constValues;

//    private Neuron[] getNeurons() {
//        return Neuron.getNeurons(this);

    //    }
    public Network(int inputSize, int outputSize) {
        neuronInputs = new HashMap<>();
        varOutputs = new HashMap<>();
        varValues = new HashMap<>();
        constValues = new HashMap<>();

//        this.inputSize = inputSize;
//        this.outputSize = outputSize;
        inputs = new Input[inputSize];
        outputs = new Output[outputSize];
//        neurons = new Neuron[0];
//        Neuron.registerNetwork(this);

        for (int i = 0; i < inputSize; i++) {
            final Input input = new Input(i);
            inputs[i] = input;
            neuronInputs.put(input, new Synapse[0]);
            varOutputs.put(input, new HashSet<>());
            //            registerNeuron(input);
        }

        for (int i = 0; i < outputSize; i++) {
            final Output output = new Output(i);
            outputs[i] = output;
            neuronInputs.put(output, new Synapse[]{new Synapse(inputs[0])});
            varOutputs.put(output, new HashSet<>());
            // This should never be checked for - thus not lead to any NPE's
        }

        reset();
        neuronInputs.keySet().retainAll(getNeurons(neuronInputs, inputs, outputs));
        for (Output output : outputs) {
            this.neuronInputs.get(output)[0] = randomInput(output, neuronInputs);
        }
    }

    public Network(
            Input[] inputs,
            Output[] outputs,
            Map<Neuron, Synapse[]> neuronInputs,
            Map<Neuron, Set<Variable>> varOutputs,
            Map<Constant, Double> constValues
    ) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.neuronInputs = neuronInputs;
        this.varOutputs = varOutputs;
        this.constValues = constValues;

        varValues = new HashMap<>();

        reset();
        neuronInputs.keySet().retainAll(getNeurons(neuronInputs, inputs, outputs));
    }

    static public Network parse(String JSON) {
        JSONObject network = new JSONObject(JSON);
        Input[] inputs = new Input[network.getInt("inputs")];
        Output[] outputs = new Output[network.getInt("outputs")];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new Input(i);
        }
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = new Output(i);
        }
        JSONObject neuronInputsOb = network.getJSONObject("neuronInputs");
        JSONObject varOutputOb = network.getJSONObject("varOutputs");
        JSONObject constValuesOb = network.getJSONObject("constValues");

        Map<String, Neuron> mapping = new HashMap<>();
        Map<Neuron, Synapse[]> neuronInputs = new HashMap<>();
        Map<Neuron, Set<Variable>> varOutputs = new HashMap<>();
        for (String key : neuronInputsOb.keySet()) {
            int splitIndex = key.indexOf(NEURON_ID_PREFIX);
            String symbol = key.substring(0, splitIndex);
            String id = key.substring(splitIndex + 1);
            int ID = Integer.parseInt(id, 16);
            Neuron newNeuron;
            switch (symbol) {
                case Input.SYMBOL:
                    newNeuron = inputs[ID];
                    break;
                case Output.SYMBOL:
                    newNeuron = outputs[ID];
                    break;
                default:
                    newNeuron = Utils.parseNeuronSymbol(symbol);
                    break;
            }
            mapping.put(key, newNeuron);
            neuronInputs.put(newNeuron, new Synapse[newNeuron.getInputCount()]);
            varOutputs.put(newNeuron, new HashSet<>());
        }

        for (String key : neuronInputsOb.keySet()) {
            Neuron neuron = mapping.get(key);
            Synapse[] inputsArray = neuronInputs.get(neuron);
            Set<Variable> outputsSet = varOutputs.get(neuron);

            JSONArray inArray = neuronInputsOb.getJSONArray(key);
            for (int i = 0; i < inArray.length(); i++) {
                final JSONObject synapseOb = inArray.getJSONObject(i);
                final Neuron synapseNeuron = mapping.get(synapseOb.getString("neuron"));
                final double synapseStrength = synapseOb.getDouble("strength");
                inputsArray[i] = new Synapse(synapseNeuron, synapseStrength);
            }

            JSONArray outArray = varOutputOb.getJSONArray(key);
            for (int i = 0; i < outArray.length(); i++) {
                outputsSet.add((Variable) mapping.get(outArray.getString(i)));
            }
        }

        HashMap<Constant, Double> constValues = new HashMap<>();
        for (String constKey : constValuesOb.keySet()) {
            Constant constant = (Constant) mapping.get(constKey);
            constValues.put(constant, constValuesOb.getDouble(constKey));
        }

        return new Network(
                inputs,
                outputs,
                neuronInputs,
                varOutputs,
                constValues
        );
    }

    /**
     * Resets vars
     */
    public void reset() {
        for (Variable var : getVars(this.neuronInputs, inputs, outputs)) {
            setVar(var, 0);
        }
    }

    private Set<Neuron> getInputable(Neuron neuron, Map<Neuron, Synapse[]> neuronInputs) {
        return complement(traverseOutputs(neuron, neuronInputs), neuronInputs);
    }

    private Synapse randomInput(Neuron neuron, Map<Neuron, Synapse[]> neuronInputs) {
        final Neuron input = Utils.randomElement(getInputable(neuron, neuronInputs));
        return new Synapse(input);
    }
//    static private Neuron randomOutput(Neuron neuron) {
//        return randomElement(getOutputable(neuron, neuronInputs));
//    }
//    static private Set<Neuron> getOutputable(Neuron neurons, HashMap<Neuron, Neuron[]> neuronInputs) {
//        return complement(traverseInputs(neurons, neuronInputs), neuronInputs);

//    }

    private Set<Neuron> complement(Set<Neuron> neurons, Map<Neuron, Synapse[]> neuronInputs) {
        final Set<Neuron> universe = getNeurons(neuronInputs, inputs, outputs);
        universe.removeAll(neurons);
        return universe;
    }
// static    private Set<Neuron> traverseOutputs(Neuron output) {
//        return traverseOutputs(output, neuronInputs);

//    }

    private static Set<Neuron> traverseOutputs(Neuron target, Map<Neuron, Synapse[]> neuronInputs) {
        final HashMap<Neuron, Set<Synapse>> neuronOutputs = new HashMap<>();
        // TODO: GETNEUONS() NOT RETURNING RIGHT INPUTS (STATICALLY), NEEDING TO SEARCH INSTEAD
        Set<Neuron> neurons = getNeurons(neuronInputs);

        for (Neuron neuron : neurons) {
            neuronOutputs.put(neuron, new HashSet<>());
        }

        for (Neuron neuron : neurons)
            for (Synapse synapse : neuronInputs.get(neuron)) {
                final Neuron input = synapse.getNeuron();
                neuronOutputs.get(input).add(new Synapse(neuron, 0));
            }

        Map<Neuron, Synapse[]> invertedNeuronInputs = new HashMap<>();
        for (Map.Entry<Neuron, Set<Synapse>> entry : neuronOutputs.entrySet()) {
            invertedNeuronInputs.put(entry.getKey(), entry.getValue().toArray(new Synapse[0]));
        }

//        try {
            if (invertedNeuronInputs.containsKey(target)) return traverseInputs(target, invertedNeuronInputs);
            return new HashSet<>();
//        } catch (NullPointerException e) {
//            System.out.println("target = " + target);
//            System.out.println("DebugUtils.formatNeuronInputs(neuronInputs) = " + DebugUtils.formatNeuronInputs(neuronInputs));
//            System.out.println("DebugUtils.formatNeuronInputs(invertedNeuronInputs) = " + DebugUtils.formatNeuronInputs(invertedNeuronInputs));
//            throw e;
//        }
    }

    private static Set<Neuron> getNeurons(Map<Neuron, Synapse[]> neuronInputs) {
        return neuronInputs.keySet();
    }


    /**
     * @deprecated
     */
    private Set<Neuron> traverseOutputs2(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
        final Set<Neuron> deps = new HashSet<>();
        Stack<Neuron> calls = new Stack<>();
        calls.add(target);

        while (!calls.isEmpty()) {
            final Neuron call = calls.pop();
            deps.add(call);
            for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
                for (Neuron neuron : entry.getValue()) {
                    if (neuron == call) {
                        final Neuron output = entry.getKey();
                        if (!deps.contains(output)) {
                            calls.add(output);
                        }
                    }
                }
            }
        }
        return deps;
    }

    /**
     * @deprecated
     */
    private Set<Neuron> traverseOutputs1(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
        final Set<Neuron> deps = new HashSet<>();
        deps.add(target);

        for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
            for (Neuron neuron : entry.getValue()) {
                if (neuron == target) {
                    deps.addAll(traverseOutputs1(entry.getKey(), neuronInputs));
                    break;
                }
            }
        }
        return deps;
    }
//    static private Set<Neuron> traverseOutputs(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> deps = new HashSet<>();
//        final Set<Neuron> visited = new HashSet<>();
//        Stack<Neuron> calls = new Stack<>();
//        calls.add(target);
//
//        while (!calls.isEmpty()) {
//            final Neuron call = calls.pop();
//            for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
//                final Neuron neuron = entry.getKey();
//                if (!visited.contains(neuron)) {
//                    visited.add(neuron);
//                    deps.add(target);
//
//                    for (Neuron input : entry.getValue()) {
//                        if (input == target) {
////                    deps.addAll(traverseOutputs(entry.getKey(), neuronInputs));
//                            break;
//                        }
//                    }
//                }
//            }
//        }

//        return deps;
//    }
    /*   public Network(Variable[] inputs, Neuron[] neurons, Output[] outputs, HashMap<Neuron, Variable[]> variableLinks) {
        links = new HashMap<>();
        this.inputs = inputs;
        this.neurons = neurons;
        this.outputs = outputs;
        this.variableLinks = variableLinks;
//        outputSize = outputs.length;
//        inputSize = inputs.length;
        registerNeuron(Neuron.NULL);
    }*/

    public double[] compute(double... inputs) {
        if (inputs.length != this.inputs.length)
            throw new IllegalArgumentException("Invalid input size: " + inputs.length + ". Expected: " + this.inputs.length);

        for (int i = 0; i < inputs.length; i++)
            setVar(this.inputs[i], inputs[i]);

        double[] result = new double[outputs.length];
        for (int i = 0; i < outputs.length; i++) {
            result[i] = outputs[i].compute(this);
        }
        return result;
    }

    public Synapse[] getInputs(Neuron neuron) {
        return neuronInputs.get(neuron);
    }

    public Network clone() {
        return copy(0);
    }

    public Network copy() {
        return copy(Main.MUTATION_RATE);
    }

    public Network copy(double mutationRate) {
        // TODO: Experiment with re-mutating newly introduced neurons within this
        //  copy as opposed to using the neuronInputs of *this* (old) instance.

        // TODO: reuse get neurons / get vars / get consts

        // TODO: EXPERIMENT WITH ALLOWING VARIABLES TO VARIABLES LINKS

        final HashMap<Constant, Double> constValues = new HashMap<>();
        final HashMap<Neuron, Set<Variable>> varOutputs = new HashMap<>();
        final HashMap<Neuron, Synapse[]> neuronInputs = new HashMap<>();

        for (Constant constant : getConsts(this.neuronInputs, inputs, outputs)) {
            // Copy constants
            final double value = this.constValues.get(constant);
            if (Utils.shouldMutate(mutationRate)) {
                constValues.put(constant, Utils.mutateValue(value));
            } else {
                constValues.put(constant, value);
            }
        }

        final Set<Neuron> neurons = getNeurons(this.neuronInputs, inputs, outputs);
        for (Neuron neuron : neurons) {
            // copy links
            Synapse[] inputs = getInputs(neuron);
            neuronInputs.put(neuron, inputs.clone());

            // copy variable outputs
            final HashSet<Variable> outputs = new HashSet<>(this.varOutputs.get(neuron));

            if (!(neuron instanceof Variable || neuron instanceof Constant)) {
                while (Utils.shouldMutate(mutationRate)) {
                    // add existing var as output
                    final Set<Variable> vars = getVars(this.neuronInputs, this.inputs, this.outputs);
//                    vars.remove(Arrays.asList(this.inputs));
                    outputs.add(Utils.randomElement(vars));
                }
                while (Utils.shouldMutate(mutationRate)) {
                    // remove a var output
                    if (outputs.size() != 0) outputs.remove(Utils.randomElement(outputs));
                }
            }

            varOutputs.put(neuron, outputs);
        }

        for (Neuron neuron : neurons) {
            final Synapse[] inputs = neuronInputs.get(neuron);

            if (inputs.length != 0) {
                //TODO: rework for multi-mutation
                while (Utils.shouldMutate(mutationRate)) {
                    // Set random input location to a newly created node
                    final Neuron newNeuron = Utils.generateRandomNeuron();
                    if (newNeuron instanceof Constant) constValues.put((Constant) newNeuron, Utils.randomValue());

                    final int randomIndex = Utils.random.nextInt(inputs.length);
                    Synapse oldInput = inputs[randomIndex];

                    final int newInputCount = newNeuron.getInputCount();
                    final Synapse[] newNeuronsInputs = new Synapse[newInputCount];
                    if (newInputCount != 0) {
                        final int newRandomIndex = Utils.random.nextInt(newInputCount);
                        newNeuronsInputs[newRandomIndex] = oldInput;

                        for (int i = 0; i < newRandomIndex; i++)
                            newNeuronsInputs[i] = randomInput(neuron, neuronInputs);
                        for (int i = newRandomIndex + 1; i < newInputCount; i++)
                            newNeuronsInputs[i] = randomInput(neuron, neuronInputs);
                    }

                    inputs[randomIndex] = new Synapse(newNeuron);
                    neuronInputs.put(newNeuron, newNeuronsInputs);
                    varOutputs.put(newNeuron, new HashSet<>());
                }
                while (Utils.shouldMutate(mutationRate)) {
                    // Set random input location to an existing node
                    final int randomIndex = Utils.random.nextInt(inputs.length);
                    inputs[randomIndex] = randomInput(neuron, neuronInputs);
                }
            }
        }

        for (Map.Entry<Neuron, Synapse[]> entry : neuronInputs.entrySet()) {
            final Synapse[] synapses = entry.getValue();
            for (int i = 0; i < synapses.length; i++) {
                if (Utils.shouldMutate(mutationRate)) {
                    Synapse synapse = synapses[i];
                    double newStrength = Utils.mutateValue(synapse.getStrength());
                    synapses[i] = new Synapse(synapse.getNeuron(), newStrength);
                }
            }
        }

        return new Network(
                this.inputs,
                this.outputs,
                neuronInputs,
                varOutputs,
                constValues
        );
    }


    public Set<Neuron> getNeurons() {
        return getNeurons(neuronInputs);
    }

    static public Set<Neuron> getNeurons(Map<Neuron, Synapse[]> neuronInputs, Input[] inputs, Output[] outputs) {
        // TODO: GETNEUONS() NOT RETURNING RIGHT INPUTS (STATICALLY), NEEDING TO SEARCH INSTEAD
        final Set<Neuron> foundNeurons = new HashSet<>();

        for (Output output : outputs) {
            foundNeurons.addAll(traverseInputs(output, neuronInputs, foundNeurons));
        }

        // Inputs may not be used (but should not be removed - due to being redundant)
        foundNeurons.addAll(Arrays.asList(inputs));

        return foundNeurons;
    }

    static public Set<Variable> getVars(Map<Neuron, Synapse[]> neuronInputs, Input[] inputs, Output[] outputs) {
        final Set<? extends Neuron> neurons = getNeurons(neuronInputs, inputs, outputs);
        neurons.removeIf(x -> !(x instanceof Variable));
        return (Set<Variable>) neurons;
    }

    static public Set<Constant> getConsts(Map<Neuron, Synapse[]> neuronInputs, Input[] inputs, Output[] outputs) {
        final Set<? extends Neuron> neurons = getNeurons(neuronInputs, inputs, outputs);
        neurons.removeIf(x -> !(x instanceof Constant));
        return (Set<Constant>) neurons;
    }

//    public Set<Neuron> traverseInputs(Neuron neuron) {
//        return traverseInputs(neuron, neuronInputs);
//    }

    static public Set<Neuron> traverseInputs(Neuron target, Map<Neuron, Synapse[]> neuronInputs) {
        return traverseInputs(target, neuronInputs, new HashSet<>());
    }

    static public Set<Neuron> traverseInputs(Neuron target, Map<Neuron, Synapse[]> neuronInputs, Set<Neuron> found) {
        final Set<Neuron> deps = new HashSet<>(found);
        Stack<Neuron> calls = new Stack<>();
        calls.add(target);

        while (!calls.isEmpty()) {
            final Neuron call = calls.pop();
            if (deps.contains(call)) continue;
            deps.add(call);
            for (Synapse synapse : neuronInputs.get(call)) {
                calls.add(synapse.getNeuron());
            }
        }
        return deps;
    }

    public void updateVarOutputs(Neuron neuron, double value) {
        for (Variable variable : getVarOutputs(neuron))
            setVar(variable, value);
    }

    public Set<Variable> getVarOutputs(Neuron neuron) {
        return varOutputs.get(neuron);
    }


    public double getVar(Variable variable) {
        return varValues.get(variable);
    }

    private void setVar(Variable variable, double value) {
        varValues.put(variable, value);
    }

    public double getConst(Constant constant) {
        return constValues.get(constant);
    }

    @Override
    public String toString() {
        return Utils.join(outputs, o -> o.serialise(this));
    }

    public String serialise() {
        JSONObject neuronInputsJSON = new JSONObject();
        JSONObject varOutputsJSON = new JSONObject();
        JSONObject constValuesJSON = new JSONObject();
        for (Map.Entry<Neuron, Synapse[]> entry : neuronInputs.entrySet()) {
            final JSONArray inputsArray = new JSONArray();
            for (Synapse synapse : entry.getValue()) {
                inputsArray.put(synapse.serialise());
            }
            neuronInputsJSON.put(entry.getKey().toString(), inputsArray);
        }
        for (Map.Entry<Neuron, Set<Variable>> entry : varOutputs.entrySet()) {
            varOutputsJSON.put(entry.getKey().toString(), new JSONArray(Utils.toString(entry.getValue())));
        }
        for (Map.Entry<Constant, Double> entry : constValues.entrySet()) {
            constValuesJSON.put(entry.getKey().toString(), entry.getValue());
        }
        return new JSONObject()
                .put("inputs", inputs.length)
                .put("outputs", outputs.length)
                .put("neuronInputs", neuronInputsJSON)
                .put("varOutputs", varOutputsJSON)
                .put("constValues", constValuesJSON)
                .toString();
    }

    public int countSynapses() {
//        count var outputs
//        count neuron inputs
//        TODO: Implement
        throw new RuntimeException("UnImplemented");
//        return -1;
    }

    public static Network crossover(Network a, Network b, double aFitness, double bFitness) {
        double maxFitness = Math.max(aFitness, bFitness);
        double aWeight = aFitness / maxFitness;
        double bWeight = bFitness / maxFitness;

        HashMap<Neuron, Synapse[]> newNeuronInputs = new HashMap<>();
        HashMap<Neuron, Set<Variable>> newVarOutputs = new HashMap<>();
        HashMap<Constant, Double> newConstValues = new HashMap<>();

        Output[] outputs = a.outputs;
        Input[] inputs = a.inputs;

        for (Output output : outputs) {
            selectInputs(output, a, b, aWeight, bWeight, newNeuronInputs);
        }

        for (Constant constant : getConsts(newNeuronInputs, inputs, outputs)) {
            final double newConstValue;
            if (a.constValues.containsKey(constant)) {
                if (b.constValues.containsKey(constant)) {
                    newConstValue = aWeight > bWeight ? a.constValues.get(constant) : b.constValues.get(constant);
                } else {
                    newConstValue = a.constValues.get(constant);
                }
            } else {
                newConstValue = b.constValues.get(constant);
            }
            newConstValues.put(constant, newConstValue);
        }
        Network[] parents = {a, b};

        Set<Neuron> neurons = getNeurons(newNeuronInputs, inputs, outputs);
        for (Neuron neuron : neurons) {
            HashSet<Variable> varOutput = new HashSet<>();
            // TODO: INVESTIGATE WHICH VAR OUTPUTS SHOULD BE SELECTED
            //    CURRENTLY , SELECTED FROM ALL PARENTS (WHERE POSSIBLE)
            for (Network parent : parents) {
                if (parent.varOutputs.containsKey(neuron)) {
                    for (Variable variable : parent.varOutputs.get(neuron)) {
                        if (neurons.contains(variable)) varOutput.add(variable);
                    }
                }
            }
            newVarOutputs.put(neuron, varOutput);


        }

        for (Input input : inputs) {
            newNeuronInputs.put(input, new Synapse[]{});
        }

        return new Network(
                inputs,
                outputs,
                newNeuronInputs,
                newVarOutputs,
                newConstValues
        );
    }

    private static void selectInputs(
            Neuron subject,
            Network a,
            Network b,
            double aWeight,
            double bWeight,
            HashMap<Neuron, Synapse[]> newNeuronInputs
    ) {
        Synapse[] aInputs = a.neuronInputs.get(subject);
        Synapse[] bInputs = b.neuronInputs.get(subject);
        int inputCount = subject.getInputCount();
        Synapse[] newInputs = new Synapse[inputCount];

        // TODO: UNION VAR OUTPUTS AND CHECK ALLLLLL LDL DL SDL SLS DL
        // Variable outputs need to be decided on whether there's a variable output

        if (a.neuronInputs.containsKey(subject)) {
            if (b.neuronInputs.containsKey(subject)) {
                for (int i = 0; i < inputCount; i++) {
                    // TODO: >>>>>>>>>>>>>>>>>>>>>>>>>>>> CHECK WHETHER THIS CAN INTRODUCE CYCLES

                    Set<Neuron> traversedOutputs = traverseOutputs(subject, newNeuronInputs);
                    final Synapse aSynapse = aInputs[i];
                    final Synapse bSynapse = bInputs[i];
                    Synapse newSynapse;
                    if (traversedOutputs.contains(aSynapse.getNeuron()))
                        newSynapse = bSynapse;
                    else if (traversedOutputs.contains(bSynapse.getNeuron()))
                        newSynapse = aSynapse;
                    else
                        newSynapse = aWeight * aSynapse.getStrength() > bWeight * bSynapse.getStrength() ? aSynapse : bSynapse;

                    newInputs[i] = newSynapse;
                    selectInputs(newSynapse.getNeuron(), a, b, aWeight, bWeight, newNeuronInputs);
                }
            } else {
//            if (aWeight > bWeight) { // TODO: EXPERIMENT WITH CHOoSING RANDOM new random input (is this is excess of weakling)
                for (int i = 0; i < inputCount; i++) {
                    // TODO: >>>>>>>>>>>>>>>>>>>>>>>>>>>> CHECK WHETHER THIS CAN INTRODUCE CYCLES
                    final Synapse synapse = aInputs[i];
                    newInputs[i] = synapse;
                    selectInputs(synapse.getNeuron(), a, b, aWeight, bWeight, newNeuronInputs);
//                }
                }
            }
        } else {
//            { // TODO: EXPERIMENT WITH CHOoSING RANDOM new random input (is  this is excess of weakling)
            for (int i = 0; i < inputCount; i++) {
                // TODO: >>>>>>>>>>>>>>>>>>>>>>>>>>>> CHECK WHETHER THIS CAN INTRODUCE CYCLES
                final Synapse synapse = bInputs[i];
                newInputs[i] = synapse;
                selectInputs(synapse.getNeuron(), a, b, aWeight, bWeight, newNeuronInputs);
            }
//            }
        }
        newNeuronInputs.put(subject, newInputs);
    }
}
