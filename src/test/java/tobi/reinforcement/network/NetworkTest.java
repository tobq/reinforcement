package tobi.reinforcement.network;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tobi.reinforcement.network.neuron.Neuron;

import java.util.Set;

public class NetworkTest {

    private static final int INPUT_SIZE = 3;
    private static final int OUTPUT_SIZE = 3;
    private static final Network BUILT_NETWORK = Network.parse("{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"RELU#498d318c\":[{\"strength\":2.8568960357516247,\"neuron\":\"OUT#0\"}],\"SUBTRACT#353d0772\":[{\"strength\":2.161830536505008,\"neuron\":\"RELU#6a400542\"},{\"strength\":1.180698278314701,\"neuron\":\"IN#3\"}],\"DIVIDE#3c130745\":[{\"strength\":5.0263806107548135,\"neuron\":\"IN#c\"},{\"strength\":-0.03253776870503604,\"neuron\":\"RELU#498d318c\"}],\"ADD#418e7838\":[{\"strength\":0.5854168947334735,\"neuron\":\"ADD#19bb07ed\"},{\"strength\":1.8201315281070485,\"neuron\":\"RELU#34b7ac2f\"}],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[{\"strength\":5.034677246632704,\"neuron\":\"IN#12\"}],\"ADD#19bb07ed\":[{\"strength\":0.7802661409689796,\"neuron\":\"IN#3\"},{\"strength\":0.49289282844546933,\"neuron\":\"IN#3\"}],\"OUT#2\":[{\"strength\":2.5286263074913577,\"neuron\":\"ADD#418e7838\"}],\"OUT#1\":[{\"strength\":2.1787765434657818,\"neuron\":\"IN#c\"}],\"MAX#61230f6a\":[{\"strength\":0.25197377404193855,\"neuron\":\"SUBTRACT#353d0772\"},{\"strength\":1.3528391467539735,\"neuron\":\"IN#e\"}],\"OUT#3\":[{\"strength\":1.189553805621409,\"neuron\":\"MAX#61230f6a\"}],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"RELU#34b7ac2f\":[{\"strength\":-6.468498524776489E-4,\"neuron\":\"ADD#19bb07ed\"}],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"RELU#6a400542\":[{\"strength\":0.8714365597538649,\"neuron\":\"DIVIDE#3c130745\"}]},\"varOutputs\":{\"RELU#498d318c\":[],\"SUBTRACT#353d0772\":[\"IN#2\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#11\",\"IN#5\"],\"DIVIDE#3c130745\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\",\"IN#12\",\"IN#1\"],\"ADD#418e7838\":[\"IN#15\",\"IN#4\",\"IN#b\",\"IN#a\",\"IN#3\",\"IN#11\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#12\"],\"ADD#19bb07ed\":[],\"OUT#2\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#1\"],\"OUT#1\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\"],\"MAX#61230f6a\":[],\"OUT#3\":[\"IN#13\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#10\",\"IN#5\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#12\",\"IN#1\",\"IN#0\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#11\",\"IN#6\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"RELU#34b7ac2f\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#1\"],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"RELU#6a400542\":[\"IN#12\",\"IN#5\",\"IN#1\"]},\"inputs\":24}");
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
}