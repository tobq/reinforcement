package tobi.reinforcement.network;//package dentai.backward;
//
//import dentai.Utils;
//import dentai.backward.neuron.Constant;
//import dentai.backward.neuron.Neuron;
//import dentai.backward.neuron.Output;
//import dentai.backward.neuron.Variable;
//import org.jgrapht.Graph;
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.Multigraph;
//import org.jgrapht.graph.builder.GraphTypeBuilder;
//
//import java.util.*;
//
//import static dentai.Utils.*;
//
//public class NetworkJGraphT {
//    private static final double MUTATION_RATE = 0.05;
//
//    private final Variable[] inputs;
//    private final Output[] outputs;
//    private final HashMap<Neuron, Set<Variable>> varOutputs;
//    private final HashMap<Variable, Double> varValues;
//    private final HashMap<Constant, Double> constValues;
//    private Graph<Neuron, DefaultEdge> graph;
//    private Graph<Neuron, DefaultEdge> graph1;
//
////    private Neuron[] getNeurons() {
////        return Neuron.getNeurons(this);
////    }
//
//    public NetworkJGraphT(int inputSize, int outputSize) {
//        graph = GraphTypeBuilder
//                .<Neuron, DefaultEdge>directed()
//                .allowingMultipleEdges(true)
//                .allowingSelfLoops(false)
//                .weighted(false)
//                .edgeClass(DefaultEdge.class)
//                .buildGraph();
//
//        varOutputs = new HashMap<>();
//        varValues = new HashMap<>();
//        constValues = new HashMap<>();
//
////        this.inputSize = inputSize;
////        this.outputSize = outputSize;
//        inputs = new Variable[inputSize];
//        outputs = new Output[outputSize];
////        neurons = new Neuron[0];
////        Neuron.registerNetwork(this);
//
//        for (var i = 0; i < inputSize; i++) {
//            final var input = new Variable();
//            inputs[i] = input;
//            graph.addVertex(input);
//            varOutputs.put(input, Set.of());
//            //            registerNeuron(input);
//        }
//
//        for (var i = 0; i < outputSize; i++) {
//            final var output = new Output();
//            outputs[i] = output;
//            graph.addVertex(output);
//            varOutputs.put(output, new HashSet<>());
//        }
//
//        for (Output output : outputs) {
//            graph.addEdge(output);
//            graph.inco
//            graph.get(output)[0] = randomInput(output, neuronInputs);
//        }
//
//        resetVars();
//    }
//
//    public void resetVars() {
//        for (Variable var : getVars(this.neuronInputs)) {
//            setVar(var, 0);
//        }
//    }
//
//    private Set<Neuron> getInputable(Neuron neuron, HashMap<Neuron, Neuron[]> neuronInputs) {
//        return complement(traverseOutputs3(neuron, neuronInputs), neuronInputs);
//    }
//
//    private Neuron randomInput(Neuron neuron, HashMap<Neuron, Neuron[]> neuronInputs) {
//        return randomElement(getInputable(neuron, neuronInputs));
//    }
//
////    static private Neuron randomOutput(Neuron neuron) {
////        return randomElement(getOutputable(neuron, neuronInputs));
////    }
////    static private Set<Neuron> getOutputable(Neuron neurons, HashMap<Neuron, Neuron[]> neuronInputs) {
////        return complement(traverseInputs(neurons, neuronInputs), neuronInputs);
////    }
//
//    private Set<Neuron> complement(Set<Neuron> neurons, HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> universe = getNeurons(neuronInputs);
//        universe.removeAll(neurons);
//        return universe;
//    }
//
//// static    private Set<Neuron> traverseOutputs(Neuron output) {
////        return traverseOutputs(output, neuronInputs);
////    }
//
//    private Set<Neuron> traverseOutputs3(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
//        return traverseInputs(target, invertNeuronInputs(neuronInputs));
//    }
//
//    private Map<Neuron, Neuron[]> invertNeuronInputs(HashMap<Neuron, Neuron[]> neuronInputs) {
//        final HashMap<Neuron, Set<Neuron>> neuronOutputs = new HashMap<>();
//        for (Neuron neuron : neuronInputs.keySet()) {
//            neuronOutputs.put(neuron, new HashSet<>());
//        }
//
//        for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
//            final Neuron output = entry.getKey();
//            for (Neuron neuron : entry.getValue()) {
//                final Set<Neuron> outputs = neuronOutputs.get(neuron);
//                outputs.add(output);
//            }
//        }
//
//        Map<Neuron, Neuron[]> finalNeuronOutputs = new HashMap<>();
//        for (Map.Entry<Neuron, Set<Neuron>> entry : neuronOutputs.entrySet()) {
//            finalNeuronOutputs.put(entry.getKey(), entry.getValue().toArray(new Neuron[0]));
//        }
//        return finalNeuronOutputs;
//    }
//
//
//    /**
//     * @deprecated
//     */
//    private Set<Neuron> traverseOutputs2(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> deps = new HashSet<>();
//        Stack<Neuron> calls = new Stack<>();
//        calls.add(target);
//
//        while (!calls.isEmpty()) {
//            final Neuron call = calls.pop();
//            deps.add(call);
//            for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
//                for (Neuron neuron : entry.getValue()) {
//                    if (neuron == call) {
//                        final Neuron output = entry.getKey();
//                        if (!deps.contains(output)) {
//                            calls.add(output);
//                        }
//                    }
//                }
//            }
//        }
//        return deps;
//    }
//
//    /**
//     * @deprecated
//     */
//    private Set<Neuron> traverseOutputs(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> deps = new HashSet<>();
//        deps.add(target);
//
//        for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
//            for (Neuron neuron : entry.getValue()) {
//                if (neuron == target) {
//                    deps.addAll(traverseOutputs(entry.getKey(), neuronInputs));
//                    break;
//                }
//            }
//        }
//        return deps;
//    }
//
////    static private Set<Neuron> traverseOutputs(Neuron target, HashMap<Neuron, Neuron[]> neuronInputs) {
////        final Set<Neuron> deps = new HashSet<>();
////        final Set<Neuron> visited = new HashSet<>();
////        Stack<Neuron> calls = new Stack<>();
////        calls.add(target);
////
////        while (!calls.isEmpty()) {
////            final Neuron call = calls.pop();
////            for (Map.Entry<Neuron, Neuron[]> entry : neuronInputs.entrySet()) {
////                final Neuron neuron = entry.getKey();
////                if (!visited.contains(neuron)) {
////                    visited.add(neuron);
////                    deps.add(target);
////
////                    for (Neuron input : entry.getValue()) {
////                        if (input == target) {
//////                    deps.addAll(traverseOutputs(entry.getKey(), neuronInputs));
////                            break;
////                        }
////                    }
////                }
////            }
////        }
////        return deps;
////    }
//
//    public NetworkJGraphT(
//            Variable[] inputs,
//            Output[] outputs,
//            HashMap<Neuron, Neuron[]> neuronInputs,
//            HashMap<Neuron, Set<Variable>> varOutputs,
//            HashMap<Constant, Double> constValues
//    ) {
//        this.inputs = inputs;
//        this.outputs = outputs;
//        this.neuronInputs = neuronInputs;
//        this.varOutputs = varOutputs;
//        this.constValues = constValues;
//        varValues = new HashMap<>();
//        resetVars();
//    }
//    /*   public Network(Variable[] inputs, Neuron[] neurons, Output[] outputs, HashMap<Neuron, Variable[]> variableLinks) {
//        links = new HashMap<>();
//        this.inputs = inputs;
//        this.neurons = neurons;
//        this.outputs = outputs;
//        this.variableLinks = variableLinks;
////        outputSize = outputs.length;
////        inputSize = inputs.length;
//        registerNeuron(Neuron.NULL);
//    }*/
//
//    public double[] compute(double... inputs) {
//        if (inputs.length != this.inputs.length)
//            throw new IllegalArgumentException("Invalid input size");
//
//        for (var i = 0; i < inputs.length; i++)
//            setVar(this.inputs[i], inputs[i]);
//
//        double[] result = new double[outputs.length];
//        for (int i = 0; i < outputs.length; i++) {
//            result[i] = outputs[i].compute(this);
//        }
//        return result;
//    }
//
//    public Neuron[] getInputs(Neuron neuron) {
//        return neuronInputs.get(neuron);
//    }
//
//    public NetworkJGraphT clone() {
//        return copy(0);
//    }
//
//    public NetworkJGraphT copy() {
//        return copy(MUTATION_RATE);
//    }
//
//    protected NetworkJGraphT copy(double mutationRate) {
//        // TODO: Experiment with re-mutating newly introduced neurons within this
//        //  copy as opposed to using the neuronInputs of *this* (old) instance.
//
//        // TODO: reuse get neurons / get vars / get consts
//
//        // TODO: EXPERIMENT WITH ALLOWING VARIABLES TO VARIABLES LINKS
//
//        final HashMap<Constant, Double> constValues = new HashMap<>();
//        final HashMap<Neuron, Set<Variable>> varOutputs = new HashMap<>();
//        final HashMap<Neuron, Neuron[]> neuronInputs = new HashMap<>();
//
//        for (Constant constant : getConsts(this.neuronInputs)) {
//            // Copy constants
//            final double value = this.constValues.get(constant);
//            if (shouldMutate(mutationRate)) {
//                constValues.put(constant, value * Utils.logRandom(0.5, 2) + Utils.random(-1D, 1D));
//            } else {
//                constValues.put(constant, value);
//            }
//        }
//
//        final Set<Neuron> neurons = getNeurons(this.neuronInputs);
//        for (Neuron neuron : neurons) {
//            // copy links
//            neuronInputs.put(neuron, getInputs(neuron).clone());
//
//            // copy variable outputs
//            final Set<Variable> outputs = new HashSet<>(this.varOutputs.get(neuron));
//            if (!(neuron instanceof Variable || neuron instanceof Constant)) {
//                if (shouldMutate(mutationRate)) {
//                    // add existing var as output
//                    final Set<Variable> vars = getVars(this.neuronInputs);
////                    vars.remove(Arrays.asList(this.inputs));
//                    outputs.add(randomElement(vars));
//                }
//                if (shouldMutate(mutationRate)) {
//                    // remove a var output
//                    if (outputs.size() != 0) outputs.remove(randomElement(outputs));
//                }
//            }
//            varOutputs.put(neuron, outputs);
//        }
//
//        for (Neuron neuron : neurons) {
//            final Neuron[] inputs = neuronInputs.get(neuron);
//
//            if (inputs.length != 0) {
//                if (shouldMutate(mutationRate)) {
//                    // Set random input location to a newly created node
//                    final Neuron newNeuron = randomNeuronType();
//                    if (newNeuron instanceof Constant) constValues.put((Constant) newNeuron, random(-1D, 1D));
//
//
//                    final int inputCount = newNeuron.getInputCount();
//                    final Neuron[] newNeuronsInputs = new Neuron[inputCount];
//                    for (int i = 0; i < inputCount; i++) {
//                        newNeuronsInputs[i] = randomInput(neuron, neuronInputs);
//                    }
//                    final int randomIndex = random.nextInt(inputs.length);
//                    inputs[randomIndex] = newNeuron;
//                    neuronInputs.put(newNeuron, newNeuronsInputs);
//                    varOutputs.put(newNeuron, Set.of());
//                }
//                if (shouldMutate(mutationRate)) {
//                    // Set random input location to an existing node
//                    final int randomIndex = random.nextInt(inputs.length);
//                    final Neuron newInput = randomInput(neuron, neuronInputs);
//                    inputs[randomIndex] = newInput;
//                }
//            }
//        }
//
//        return new NetworkJGraphT(
//                this.inputs,
//                this.outputs,
//                neuronInputs,
//                varOutputs,
//                constValues
//        );
//    }
//
//
//    private static boolean shouldMutate(double mutationRate) {
//        return Utils.random.nextDouble() < mutationRate;
//    }
//
//    public Set<Neuron> getNeurons() {
//        return getNeurons(neuronInputs);
//    }
//
//    public Set<Neuron> getNeurons(HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> neurons = new HashSet<>();
//
//        for (Output output : outputs) {
//            neurons.addAll(traverseInputs(output, neuronInputs));
//        }
//        neurons.addAll(Arrays.asList(inputs));
//        return neurons;
//    }
//
//    public Set<Variable> getVars(HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<? extends Neuron> neurons = getNeurons(neuronInputs);
//        neurons.removeIf(x -> !(x instanceof Variable));
//        return (Set<Variable>) neurons;
//    }
//
//    public Set<Constant> getConsts(HashMap<Neuron, Neuron[]> neuronInputs) {
//        final Set<? extends Neuron> neurons = getNeurons(neuronInputs);
//        neurons.removeIf(x -> !(x instanceof Constant));
//        return (Set<Constant>) neurons;
//    }
//
////    public Set<Neuron> traverseInputs(Neuron neuron) {
////        return traverseInputs(neuron, neuronInputs);
////    }
//
//    static public Set<Neuron> traverseInputs(Neuron target, Map<Neuron, Neuron[]> neuronInputs) {
//        final Set<Neuron> deps = new HashSet<>();
//        Stack<Neuron> calls = new Stack<>();
//        calls.add(target);
//
//        while (!calls.isEmpty()) {
//            final Neuron call = calls.pop();
//            deps.add(call);
//            for (Neuron input : neuronInputs.get(call))
//                if (!deps.contains(input)) {
//                    calls.add(input);
//                }
//        }
//        return deps;
//    }
//
//    //    public Network copy() {
////        Network network = new Network(inputs.length, outputs.length);
////
////        return network;
////    }
//    public void updateVarOutputs(Neuron neuron, double value) {
//        for (var variable : varOutputs.get(neuron))
//            setVar(variable, value);
//    }
//
//    public double getVar(Variable variable) {
//        return varValues.get(variable);
//    }
//
//    private void setVar(Variable variable, double value) {
//        varValues.put(variable, value);
//    }
//
//    public double getConst(Constant constant) {
//        return constValues.get(constant);
//    }
//}
