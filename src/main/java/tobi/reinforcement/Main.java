package tobi.reinforcement;

import org.apache.commons.cli.*;
import tobi.reinforcement.network.Network;
import tobi.reinforcement.problems.Memorise;
import tobi.reinforcement.problems.SequenceProblem;
import tobi.reinforcement.problems.XORProblem;
import tobi.reinforcement.problems.gym.BoxBoxGym;
import tobi.reinforcement.problems.gym.BoxDiscreteGym;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static final String INTERPRETER_ARG_KEY = "i";
    //    private static final DoubleUnaryOperator GOAL_FUNC = x -> x * x - 50;
//    private static final Approximate PROBLEM = new Approximate(GOAL_FUNC);
//    private static final Memorise PROBLEM = new Memorise(5);
//        private static final Problem PROBLEM = new SequenceProblem();
    //        private static final Problem PROBLEM = new Square();
//    private static final Problem PROBLEM = new EqualsProblem(1);
//    public static final double MUTATION_RATE = 0.1;
//    public static final int GENERATION_SIZE = 250;
//    private static final SortMethod SORT_METHOD = SortMethod.TIE_MIN_NEURONS_SORT;
    public static final long MAX_GENERATION_COUNT = 1000000;
    //        public static final int GENERATION_SIZE = 10000;
//    private static final double PARENT_RATIO = 1;
    private static final double PARENT_RATIO = 0.1;
    private static final boolean ASEXUAL_REPRODUCTION = true;
    private static final boolean COPY_CROSSOVERS = true;
    private static final boolean CLONE_PARENTS = true;

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
     * <p>
     * CHANGE VARIABLES FOR TIME STEP
     * COMPARE EFFECTIVENESS OF BOTH
     */
    public static void main(String... args) throws InterruptedException, IOException, URISyntaxException, ExecutionException {
// TODO: MAKE OPTIONAL PARAMS FOR HARD-CODED ALGORITHM ARGUMENTS
        Options options = new Options();

        options.addOption(INTERPRETER_ARG_KEY, "interpreter", true, "Python interpreter (executable) to be used.");

        options.addOption("b", "base-network", true, "Based network used to generate initial population");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }
        System.out.println(Arrays.toString((args)));

//        final String ENV_ID = "BipedalWalker-v3";
//        final String ENV_ID = "LunarLanderContinuous-v2";
//        try (BoxBoxGym PROBLEM = cmd.hasOption(INTERPRETER_ARG_KEY) ? new BoxBoxGym(ENV_ID, cmd.getOptionValue(INTERPRETER_ARG_KEY)) : new BoxBoxGym(ENV_ID)) {
//        XORProblem PROBLEM = new XORProblem();
//        {
        try (BoxDiscreteGym PROBLEM = new BoxDiscreteGym("CartPole-v1")) {
//        try (BoxDiscreteGym PROBLEM = new BoxDiscreteGym("Pong-ram-v0")) {
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

            int TRIALS = 10;
            for (int GENERATION_SIZE_I = 0; GENERATION_SIZE_I < 7; GENERATION_SIZE_I++) {
                int GENERATION_SIZE = (int) (100 * Math.pow(2, GENERATION_SIZE_I));
                final int PARENT_COUNT = (int) Math.ceil(GENERATION_SIZE * PARENT_RATIO);
                for (int MURATION_RATE_I = 0; MURATION_RATE_I < 5; MURATION_RATE_I++) {
                    double MUTATION_RATE = 0.01 * Math.pow(2, MURATION_RATE_I);
                    for (SortMethod SORT_METHOD : SortMethod.values()) {
                        MyRandom random = new MyRandom(0);


                        String[] CONFIG = {
                                "PROBLEM = " + PROBLEM.toString(),
                                "MUTATION_RATE = " + MUTATION_RATE,
                                "CLONE_PARENTS = " + CLONE_PARENTS,
                                "GENERATION_SIZE = " + GENERATION_SIZE,
                                "PARENT_COUNT = " + PARENT_COUNT,
                                "ASEXUAL_REPRODUCTION = " + ASEXUAL_REPRODUCTION,
                                "COPY_CROSSOVERS = " + COPY_CROSSOVERS,
                                "CLONE_PARENTS = " + CLONE_PARENTS,
                                "SORT_METHOD = " + SORT_METHOD
                        };
                        long TRAINING_START = System.currentTimeMillis();
                        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(TRAINING_START + ".csv"))) {
                            for (String setting : CONFIG) {
                                System.out.println(setting);
                                fileWriter.println("#" + setting);
                            }

                            int inputCount = PROBLEM.getInstanceLength();
                            int outputCount = PROBLEM.getLabelLength();

                            Network[] generation = new Network[GENERATION_SIZE];

                            if (cmd.hasOption('b')) {
                                Network baseNetwork = Network.parse(cmd.getOptionValue('b'));
                                generation[0] = baseNetwork.clone();
                                for (int i = 1; i < generation.length; i++) {
                                    generation[i] = baseNetwork.copy(MUTATION_RATE);
                                }
                            } else {
                                for (int i = 0; i < generation.length; i++) {
                                    generation[i] = new Network(inputCount, outputCount);
                                }
                            }

                            ExecutorService exec = Executors.newFixedThreadPool(GENERATION_SIZE);
                            int seed = random.nextPositiveInt();

                            int FINISHED = 0;
                            for (int TRIAL = 0; TRIAL < TRIALS; TRIAL++) {
                                for (int g = 0; g < MAX_GENERATION_COUNT; g++) {
                                    double[] fitnesses = new double[generation.length];

                                    ArrayList<Callable<Double>> tasks = new ArrayList<>();
                                    for (Network network : generation) {
                                        int finalSeed = seed;
                                        tasks.add(() -> PROBLEM.test(network, false, /*1,*/ finalSeed));
//                                    tasks.add(() -> PROBLEM.test(network, finalSeed));
//                        tasks.add(() -> PROBLEM.test(network));
                                    }
                                    List<Future<Double>> invokeAll = exec.invokeAll(tasks);
                                    for (int i = 0; i < invokeAll.size(); i++) {
                                        fitnesses[i] = invokeAll.get(i).get();
                                    }
//                                for (int i = 0; i < generation.length; i++) {
//                                    Network network = generation[i];
//                                    fitnesses[i] = PROBLEM.test(network, false, seed);
////                        fitnesses[i] = PROBLEM.test(network, seed);
////                        fitnesses[i] = PROBLEM.test(network);
//                                }

                                    Integer[] order = new Integer[generation.length];
                                    for (int i = 0; i < generation.length; i++) order[i] = i;

                                    Network[] finalGeneration = generation;
                                    Comparator<Integer> c;
                                    switch (SORT_METHOD) {
                                        case LOG_SORT:
                                            c = Comparator
                                                    .comparingDouble((Integer a) -> fitnesses[a] * Math.log(finalGeneration[a].getComplexity()))
                                                    .reversed();
                                            break;
                                        case TIE_MIN_NEURONS_SORT:
                                            c = Comparator
                                                    .comparingDouble((Integer a) -> fitnesses[a])
                                                    .reversed()
                                                    .thenComparingInt(a -> finalGeneration[a].getComplexity());
                                            break;
                                        case FITNESS:
                                        default:
                                            c = Comparator
                                                    .comparingDouble((Integer a) -> fitnesses[a])
                                                    .reversed();

                                    }

                                    Arrays.sort(order, c);

                                    int fittestIndex = order[0];
                                    double maxFitness = fitnesses[fittestIndex];
                                    Network fittestNetwork = generation[fittestIndex];
                                    int networkSize = fittestNetwork.getNeurons().size();
                                    String networkString = fittestNetwork.serialise();
//                                System.out.println(g + ": BEST FITNESS = " + maxFitness + "\t\thidden layer size = " + fittestNetwork.getHiddenLayerSize() + "\t\t" + networkString);
                                    if (maxFitness == 500D) {
                                        FINISHED++;
                                        break;
                                    }
                                    fileWriter.println(maxFitness + "," + networkSize + "," + networkString);

                                    final Network[] nextGen = new Network[generation.length];
                                    if (CLONE_PARENTS) {
                                        for (int i = 0; i < PARENT_COUNT; i++)
                                            nextGen[i] = generation[order[i]].clone();
                                    } else {
                                        for (int i = 0; i < PARENT_COUNT; i++)
                                            nextGen[i] = generation[order[i]].copy(MUTATION_RATE);
                                    }
                                    for (int i = PARENT_COUNT; i < generation.length; i++) {
                                        if (ASEXUAL_REPRODUCTION) {
                                            nextGen[i] = generation[order[i]].copy(MUTATION_RATE);
                                        } else {
                                            int randomIndexA = Utils.logRandom(generation);
                                            int randomIndexB = Utils.logRandom(generation);
                                            Network a = generation[randomIndexA];
                                            Network b = generation[randomIndexB];
                                            Network crossover;
//                            try {
                                            crossover = Network.crossover(a, b, fitnesses[randomIndexA], fitnesses[randomIndexB]);
//                            } catch (StackOverflowError e) {
//                                System.out.println("ERROR CROSSING OVER: " + a.serialise() + " AND " + b.serialise());
//                                crossover = random.nextBoolean() ? a : b;
//                                DebugUtils.showGraph(a);
//                                DebugUtils.showGraph(b);
//                                throw e;
//                            }
                                            if (COPY_CROSSOVERS) crossover = crossover.copy(MUTATION_RATE);
                                            nextGen[i] = crossover;
                                        }
                                    }
                                    generation = nextGen;
                                    seed = random.nextPositiveInt();
                                    //            SwingUtilities.invokeLater(() -> showGraph(nextGen[0]));
                                    //            plotChart(chartPanel, fittestNetwork, GOAL_FUNC);
                                    //            jFrame.pack();
                                    //            jFrame.setVisible(true);
                                }
                            }
                            long TRAINING_TIME = System.currentTimeMillis() - TRAINING_START;
                            System.out.println("FINISHED = " + FINISHED / TRIALS * 100 + "%");
                            System.out.println("AVERAGE TRAINING TIME = " + TRAINING_TIME / TRIALS + "ms\n\n\n");
                        }

                    }
                }
            }
        }
    }

    private static class MyRandom extends Random {
        public MyRandom() {
            super();
        }

        public MyRandom(long seed) {
            super(seed);
        }

        public int nextPositiveInt() {
            return next(Integer.SIZE - 1);
        }
    }
}