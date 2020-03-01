package tobi.reinforcement;

import tobi.reinforcement.network.Network;
import tobi.reinforcement.problems.Approximate;
import tobi.reinforcement.problems.gym.BoxBoxGym;
import tobi.reinforcement.problems.gym.BoxDiscreteGym;
import tobi.reinforcement.problems.gym.DiscreteBoxGym;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.DoubleUnaryOperator;

public class Main {
    private static MyRandom random = new MyRandom();
    //    private static final DoubleUnaryOperator GOAL_FUNC = x -> x * x - 50;
//    private static final Approximate PROBLEM = new Approximate(GOAL_FUNC);
    //    private static final Memorise PROBLEM = new Memorise(4);
    //    private static final Problem PROBLEM = new SequenceProblem();
//        private static final Problem PROBLEM = new Square();
//    private static final Problem PROBLEM = new EqualsProblem(1);
    public static final double MUTATION_RATE = 0.1;
    public static final int GENERATION_SIZE = 10000;
    //    public static final int GENERATION_SIZE = 1000;
    private static final double PARENT_RATIO = 0.1;
//    private static final double PARENT_RATIO = 0.1;
    public static final int PARENT_COUNT = (int) Math.ceil(GENERATION_SIZE * PARENT_RATIO);
    public static final long MAX_GENERATION_COUNT = Long.MAX_VALUE;
    private static final boolean ASEXUAL_REPRODUCTION = false;
    private static final boolean COPY_CROSSOVERS = true;
    private static final boolean CLONE_PARENTS = true;
    private static final SortMethod SORT_METHOD = SortMethod.TIE_MIN_NEURONS_SORT;
    private static final boolean VARIABLE_ENVIRONMENT = false;

    /**
     * Started with forward-fed network
     * <p>
     * Trouble with recursion - cutting out loops
     * Trouble traversing outputs
     * - 3 different implementations
     * <p>
     * Built Genetic algorithm
     * <p>
     * Introduced jGraphT
     * <p>
     * Ran on VM
     * <p>
     * problems with crashing long term
     * <p>
     * Parallelised for performance improvements
     * <p>
     * worked on basic implementation of crossover
     * <p>
     * sequence problem able to acheive 98% fitness with introduction of cross over
     * <p>
     * MNIST problem all 0's resulting in best fitness of -60000 due to -1 loss for each instance
     * - very large input size against small output size making evolution tough
     * - single mutations don't suffice to break boundary of fitness, thus all mutations are discarded and stuff at a local minima
     * <p>
     * Implemented custom sorts for networks see {@link SortMethod}
     * <p>
     * Open Ai Gym used to extend problem set
     * - more generic problems, compare with other existing algorithms
     * - more complicated problems
     * <p>
     * implemented my own GYM wrapper
     * - implemented threading
     */
    public static void main(String... args) throws InterruptedException, IOException, URISyntaxException, ExecutionException {
        final String envId = "BipedalWalker-v3";
        try (BoxBoxGym PROBLEM = args.length < 1 ? new BoxBoxGym(envId) : new BoxBoxGym(envId, args[0])) {
//        {
//        try (BoxDiscreteGym PROBLEM = new BoxDiscreteGym("Pong-ram-v0")) {
//        try (BoxDiscreteGym PROBLEM = new BoxDiscreteGym("CartPole-v1")) {
//        try (BoxBoxGym PROBLEM = new BoxBoxGym("LunarLanderContinuous-v2")) {
//        try (DiscreteBoxGym PROBLEM = new DiscreteBoxGym("HotterColder-v0")) {
//        try (DiscreteDiscreteGym PROBLEM = new DiscreteDiscreteGym("FrozenLake-v0")) {
//        try (BoxBoxGym PROBLEM = new BoxBoxGym("CarRacing-v0")) {
//        final ChartPanel chartPanel = new ChartPanel(ChartFactory.createXYLineChart(
//                "",
//                "x",
//                "h(x)",
//                new XYSeriesCollection()
//        ));
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        JFrame jFrame = new JFrame();
//        jFrame.setContentPane(chartPanel);
            String[] CONFIG = {
                    "PROBLEM = " + PROBLEM.toString(),
                    "MUTATION_RATE = " + MUTATION_RATE,
                    "CLONE_PARENTS = " + CLONE_PARENTS,
                    "GENERATION_SIZE = " + GENERATION_SIZE,
                    "PARENT_COUNT = " + PARENT_COUNT,
                    "ASEXUAL_REPRODUCTION = " + ASEXUAL_REPRODUCTION,
                    "COPY_CROSSOVERS = " + COPY_CROSSOVERS,
                    "CLONE_PARENTS = " + CLONE_PARENTS,
                    "SORT_METHOD = " + SORT_METHOD,
                    "VARIABLE_ENVIRONMENT = " + VARIABLE_ENVIRONMENT
            };
            try (PrintWriter fileWriter = new PrintWriter(new FileWriter(System.currentTimeMillis() + ".csv"))) {
                for (String setting : CONFIG) {
                    System.out.println(setting);
                    fileWriter.println("#" + setting);
                }

                int inputCount = PROBLEM.getInstanceLength();
                int outputCount = PROBLEM.getLabelLength();

                Network[] generation = new Network[GENERATION_SIZE];
                for (int i = 0; i < generation.length; i++) {
                    generation[i] = new Network(inputCount, outputCount);
                }

                ExecutorService exec = Executors.newFixedThreadPool(GENERATION_SIZE);
                int seed = random.nextPositiveInt();
                for (int g = 0; g < MAX_GENERATION_COUNT; g++) {
                    double[] fitnesses = new double[generation.length];

                    ArrayList<Callable<Double>> tasks = new ArrayList<>();
                    for (Network network : generation) {
                        int finalSeed = seed;
                        tasks.add(() -> PROBLEM.test(network, false, /*1,*/ finalSeed));
//                        tasks.add(() -> PROBLEM.test(network));
                    }
                    List<Future<Double>> invokeAll = exec.invokeAll(tasks);
                    for (int i = 0; i < invokeAll.size(); i++) {
                        fitnesses[i] = invokeAll.get(i).get();
                    }

                    Integer[] order = new Integer[generation.length];
                    for (int i = 0; i < generation.length; i++) order[i] = i;

                    Network[] finalGeneration = generation;
                    Comparator<Integer> c;
                    switch (SORT_METHOD) {
                        case LOG_SORT:
                            c = Comparator
                                    .comparingDouble((Integer a) -> fitnesses[a] * Math.log(finalGeneration[a].getNeurons().size()))
                                    .reversed();
                            break;
                        case TIE_MIN_NEURONS_SORT:
                            c = Comparator
                                    .comparingDouble((Integer a) -> fitnesses[a])
                                    .reversed()
                                    .thenComparingInt(a -> finalGeneration[a].getNeurons().size());
                            break;
                        default:
                            c = Comparator
                                    .comparingDouble((Integer a) -> fitnesses[a])
                                    .reversed();
                            break;
                    }

                    Arrays.sort(order, c);

                    int fittestIndex = order[0];
                    double maxFitness = fitnesses[fittestIndex];
                    Network fittestNetwork = generation[fittestIndex];
                    int networkSize = fittestNetwork.getNeurons().size();
                    String networkString = fittestNetwork.serialise();
                    System.out.println(g + ": BEST FITNESS = " + maxFitness + "\t\tNode count = " + networkSize + "\t\t" + networkString);
                    fileWriter.println(maxFitness + "," + networkSize + "," + networkString);

                    final Network[] nextGen = new Network[generation.length];
                    if (CLONE_PARENTS) {
                        for (int i = 0; i < PARENT_COUNT; i++)
                            nextGen[i] = generation[order[i]].clone();
                    } else {
                        for (int i = 0; i < PARENT_COUNT; i++)
                            nextGen[i] = generation[order[i]].copy();
                    }
                    for (int i = PARENT_COUNT; i < generation.length; i++) {
                        if (ASEXUAL_REPRODUCTION) {
                            nextGen[i] = generation[order[i]].copy();
                        } else {
                            int randomIndexA = Utils.logRandom(generation);
                            int randomIndexB = Utils.logRandom(generation);
                            Network a = generation[randomIndexA];
                            Network b = generation[randomIndexB];
                            Network crossover = Network.crossover(a, b, fitnesses[randomIndexA], fitnesses[randomIndexB]);
                            if (COPY_CROSSOVERS) crossover = crossover.copy();
                            nextGen[i] = crossover;
                        }
                    }
                    generation = nextGen;
                    if (VARIABLE_ENVIRONMENT) seed = random.nextPositiveInt();
                    //            SwingUtilities.invokeLater(() -> showGraph(nextGen[0]));
                    //            plotChart(chartPanel, fittestNetwork, GOAL_FUNC);
                    //            jFrame.pack();
                    //            jFrame.setVisible(true);
                }
            }
        }
    }

    private static class MyRandom extends Random {
        public int nextPositiveInt() {
            return next(Integer.SIZE - 1);
        }
    }
}