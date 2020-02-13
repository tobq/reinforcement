package tobi.reinforcement.network;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NetworkTest {

    private static final int INPUT_SIZE = 3;
    private static final int OUTPUT_SIZE = 3;
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

    private void assertNetworksEqual(Network generatedNetwork, Network parse) {
        for (double[] input : RANDOM_INPUTS) {
            Assertions.assertArrayEquals(generatedNetwork.compute(input), parse.compute(input));
        }
    }
}