package tobi.reinforcement.network;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tobi.reinforcement.network.neuron.Neuron;

import java.util.Set;

public class NetworkTest {

    private static final int INPUT_SIZE = 3;
    private static final int OUTPUT_SIZE = 3;
    private static final Network BUILT_NETWORK = Network.parse("{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"ADD#544e713e\":[{\"strength\":18.4330568016355,\"neuron\":\"IN#c\"},{\"strength\":1.42068926951858,\"neuron\":\"OUT#1\"}],\"ADD#6e3ba10a\":[{\"strength\":1.6897780550855153,\"neuron\":\"RELU#7cf70513\"},{\"strength\":5.373521905966998,\"neuron\":\"TANH#5a75ec37\"}],\"TANH#5a75ec37\":[{\"strength\":4.190618967077511,\"neuron\":\"SIN#32620f7e\"}],\"IN#10\":[],\"IN#11\":[],\"ABS#2034d6ee\":[{\"strength\":0.8338895650294053,\"neuron\":\"IN#11\"}],\"SIN#32620f7e\":[{\"strength\":1.3167106250232796,\"neuron\":\"ADD#544e713e\"}],\"IN#12\":[],\"OUT#0\":[{\"strength\":1.6264192122387808,\"neuron\":\"ABS#2034d6ee\"}],\"OUT#2\":[{\"strength\":2.361678365255745,\"neuron\":\"IN#3\"}],\"OUT#1\":[{\"strength\":0.4369462118603916,\"neuron\":\"T1#18660909\"}],\"OUT#3\":[{\"strength\":36.54727035778841,\"neuron\":\"ADD#6e3ba10a\"}],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"TANH#cadae8d\":[{\"strength\":2.4302345916318036,\"neuron\":\"IN#c\"}],\"IN#8\":[],\"IN#9\":[],\"RELU#7cf70513\":[{\"strength\":0.527033469005664,\"neuron\":\"TANH#cadae8d\"}],\"IN#6\":[],\"IN#7\":[],\"T1#18660909\":[{\"strength\":0.499282206414027,\"neuron\":\"IN#0\"}]},\"varOutputs\":{\"ADD#544e713e\":[],\"ADD#6e3ba10a\":[],\"TANH#5a75ec37\":[],\"IN#10\":[],\"IN#11\":[],\"ABS#2034d6ee\":[],\"SIN#32620f7e\":[],\"IN#12\":[],\"OUT#0\":[],\"OUT#2\":[],\"OUT#1\":[],\"OUT#3\":[],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#f\":[],\"IN#15\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"TANH#cadae8d\":[],\"IN#8\":[],\"IN#9\":[],\"RELU#7cf70513\":[],\"IN#6\":[],\"IN#7\":[],\"T1#18660909\":[]},\"inputs\":24}");
    private static Network generatedNetwork = new Network(INPUT_SIZE, OUTPUT_SIZE);
    private static Network generatedNetwork2 = new Network(INPUT_SIZE, OUTPUT_SIZE);
    private static final double[][] RANDOM_INPUTS;

    static {
        RANDOM_INPUTS = new double[1000][INPUT_SIZE];
        for (int i = 0; i < RANDOM_INPUTS.length; i++) {
            double[] input = new double[INPUT_SIZE];
            for (int j = 0; j < input.length; j++) {
                input[j] = Math.random() * 10 - 5;
            }
            RANDOM_INPUTS[i] = input;
        }

        for (int i = 0; i < 10000; i++) {
            generatedNetwork = generatedNetwork.copy();
            generatedNetwork2 = generatedNetwork2.copy();
        }
        System.out.println("Neurons in mutated network: " + generatedNetwork.getNeurons().size());
        System.out.println("Neurons in mutated network 2: " + generatedNetwork2.getNeurons().size());
    }

//    @Test
//    public void MNISTTraining() throws IOException {
//        for (var pair : new MNISTProblem().streamTrainingData()) {
//            System.out.println("pair.instance = " + pair.getInstance());
//            System.out.println("pair.label = " + pair.getLabel());
//        }
//    }

    @Test
    public void benchmarkCopy() {
        double avg = benchmark(() -> generatedNetwork.copy());
        System.out.println("Average copy time: " + avg + "ms");
    }

    @Test
    public void benchmarkClone() {
        double avg = benchmark(() -> generatedNetwork.clone());
        System.out.println("Average clone time: " + avg + "ms");
    }

    @Test
    public void crossOverSelfTest() {
        Network clone = generatedNetwork.clone();
        Assertions.assertEquals(
                clone.toString(),
                Network.crossover(clone, clone, 1, 1).toString()
        );

        assertNetworksEqual(clone, Network.crossover(clone, clone, 1, 1));
    }

    @Test
    public void crossOverTest() {
        Network clone = generatedNetwork.clone();
        Network clone2 = generatedNetwork2.clone();

        for (int i = 0; i < 2000; i++)
            for (double[] input : RANDOM_INPUTS) {
                clone = Network.crossover(clone, clone2, 1, 1).copy();
                clone2 = Network.crossover(clone2, clone, 1, 1).copy();

                clone.compute(input);
                clone2.compute(input);
            }
    }

    @Test
    public void determinism() {
        for (double[] input : RANDOM_INPUTS) {
            double[] result = generatedNetwork.compute(input);
            for (int n = 0; n < 100; n++)
                Assertions.assertArrayEquals(result, generatedNetwork.compute(input));
        }
    }


    @Test
    public void benchmarkCompute() {
        double avg = benchmark(() -> generatedNetwork.compute(1, 2, 3));
        System.out.println("Average compute time: " + avg + "ms");
    }

    private double benchmark(Runnable runnable) {
        int TRIALS = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < TRIALS; i++) {
            runnable.run();
        }
        long end = System.currentTimeMillis();
        return (end - start) / (double) TRIALS;
    }


    @Test
    public void testClone() {
        assertNetworksEqual(generatedNetwork, generatedNetwork.clone());
    }


    @Test
    public void testCloneEquivalent() {
        assertNetworksEqual(generatedNetwork, Network.parse(generatedNetwork.serialise()));
    }

    private void assertNetworksEqual(Network a, Network b) {
        for (double[] input : RANDOM_INPUTS) {
            Assertions.assertArrayEquals(a.compute(input), b.compute(input));
        }
    }

    @Test
    public void reachability() {
        Set<Neuron> neurons = BUILT_NETWORK.getNeurons();
        for (Neuron neuron : neurons) {
            Set<Neuron> reachable = BUILT_NETWORK.getReachable(neuron);
            for (Neuron end : neurons) {
                boolean isReachable = reachable.contains(end);
                Assertions.assertEquals(Network.isReachable(neuron, end, BUILT_NETWORK.neuronInputs), isReachable);
                Assertions.assertEquals(Network.traverseInputs(end, BUILT_NETWORK.neuronInputs).contains(neuron), isReachable);
                Assertions.assertEquals(Network.isReachable2(neuron, end, BUILT_NETWORK.neuronInputs), isReachable);
            }
        }
    }

    @Test
    public void copyReachable() {
        Network net = BUILT_NETWORK;

        for (int i = 0; i < 10000; i++) {
            net = net.copy();

            Set<Neuron> neurons = net.getNeurons();
            for (Neuron neuron : neurons) {
                Set<Neuron> reachable = net.getReachable(neuron);
                for (Neuron end : neurons) {
                    Assertions.assertEquals(Network.isReachable(neuron, end, net.neuronInputs),reachable.contains(end));
                }
            }
        }
    }

    @Test
    public void copyReachable2() {
        Network net = BUILT_NETWORK;

        for (int i = 0; i < 10000; i++) {
            net = net.copy();

            Set<Neuron> neurons = net.getNeurons();
            for (Neuron neuron : neurons) {
                Set<Neuron> reachable = net.getReachable(neuron);
                for (Neuron end : neurons) {
                    Assertions.assertEquals(Network.isReachable2(neuron, end, net.neuronInputs),reachable.contains(end));
                }
            }
        }
    }

    @Test
    public void reachabilitySpeedTest() {
        Set<Neuron> neurons = BUILT_NETWORK.getNeurons();
        int iterations = 1000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                Set<Neuron> reachable = BUILT_NETWORK.getReachable(neuron);
                for (Neuron end : neurons) {
                    reachable.contains(end);
                }
            }
        System.out.println("REACHABLE() -> CONTAINS(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    BUILT_NETWORK.getReachable(neuron).contains(end);
                }
            }
        System.out.println("REACHABLE().CONTAINS(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.traverseInputs(end, BUILT_NETWORK.neuronInputs).contains(neuron);
                }
            }
        System.out.println("traverseInputs(end).contains(start): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.isReachable(neuron, end, BUILT_NETWORK.neuronInputs);
                }
            }
        System.out.println("isReachable(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.isReachable2(neuron, end, BUILT_NETWORK.neuronInputs);
                }
            }
        System.out.println("isReachable2(): " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) {

        Set<Neuron> neurons = BUILT_NETWORK.getNeurons();
        int iterations = 1000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                Set<Neuron> reachable = BUILT_NETWORK.getReachable(neuron);
                for (Neuron end : neurons) {
                    reachable.contains(end);
                }
            }
        System.out.println("REACHABLE() -> CONTAINS(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    BUILT_NETWORK.getReachable(neuron).contains(end);
                }
            }
        System.out.println("REACHABLE().CONTAINS(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.traverseInputs(end, BUILT_NETWORK.neuronInputs).contains(neuron);
                }
            }
        System.out.println("traverseInputs(end).contains(start): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.isReachable(neuron, end, BUILT_NETWORK.neuronInputs);
                }
            }
        System.out.println("isReachable(): " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
            for (Neuron neuron : neurons) {
                for (Neuron end : neurons) {
                    Network.isReachable2(neuron, end, BUILT_NETWORK.neuronInputs);
                }
            }
        System.out.println("isReachable2(): " + (System.currentTimeMillis() - start) + "ms");
    }
}