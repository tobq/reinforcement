package tobi.reinforcement;

import tobi.reinforcement.network.neuron.*;

import java.util.*;
import java.util.function.Function;

public class Utils {
    public static final Random random = new Random();

    static Neuron[] getSupportedNeurons() {
        return new Neuron[]{
                /* NON ESSENTIAL FUNCTIONS */
                new Sin(),
                new Cos(),
                new Abs(),
                new Divide(),
                new Subtract(),
                new Log(),
                new Min(),
//                new Round(),

                new Add(),
                new Exp(),
                new Multiply(),
                new Relu(),
                new Sigmoid(),
                new Max(),
                new Variable(), // TODO: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UNCOMMENT RNN
                new Constant()
        };
    }


    static public Neuron parseNeuronSymbol(String symbol) {
        Neuron[] supportedNeurons = getSupportedNeurons();

        for (Neuron neuron : supportedNeurons) {
            if (symbol.equals(neuron.getSymbol())) return neuron;
        }

        throw new IllegalArgumentException("Unsupported symbol: " + symbol);
    }

    public static <T> Iterator<T> getNullIterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    public static double random(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static double random(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }


    public static double logRandom() {
        return logRandom(0, 1);
    }

    public static double logRandom(double min, double max) {
        return logRandom(min, max, 1000);
    }

    public static double logRandom(double max) {
        return logRandom(0, max);
    }

    public static <T> int logRandom(T... array) {
        return (int) Math.floor(logRandom(array.length));
    }

    public static double logRandom(double min, double max, int growth) {
        double n = random.nextDouble();
        double logRandom = (Math.pow(growth, n) - 1) / (growth - 1);
        return logRandom * (max - min) + min;
    }

    public static <T> T randomElement(T... array) {
        return array[random.nextInt(array.length)];
    }

    public static <T> T logRandomElement(T... array) {
        return array[logRandom(array)];
    }

    public static <E> E randomElement(Collection<E> collection) {
        int index = random.nextInt(collection.size());
        if (collection instanceof List) {
            // optimization
            return ((List<E>) collection).get(index);
        } else {
            Iterator<E> iter = collection.iterator();
            for (int i = 0; i < index; i++) iter.next();
            return iter.next();
        }
    }

    public static Neuron generateRandomNeuron() {
        return randomElement(getSupportedNeurons());
    }

    public static String join(String[] strings) {
        return String.join(",", strings);
    }

    public static <T> String join(T[] objects, Function<T, String> mapper) {
        return join(",", objects, mapper);
    }

    public static <T> String join(String delimiter, T[] objects) {
        return join(delimiter, objects, Object::toString);
    }

    public static <T> String join(String delimiter, T[] objects, Function<T, String> mapper) {
        return String.join(delimiter, toString(objects, mapper));
    }

    public static double randomValue() {
//        if (Utils.random.nextDouble() < 0.5) {
        if (Utils.random.nextBoolean()) {
            return randomElement(-1, 0, 1);
        }

        return random(-2D, 2D);
    }

    public static boolean shouldMutate(double mutationRate) {
        return random.nextDouble() < mutationRate;
    }

    static public <T> String[] toString(T[] objects) {
        return toString(objects, Object::toString);
    }


    static public <T> String[] toString(Collection<T> objects) {
        return toString(objects, Object::toString);
    }

    static public <T> String[] toString(T[] objects, Function<T, String> mapper) {
        return Arrays.stream(objects).map(mapper).toArray(String[]::new);
    }

    static public <T> String[] toString(Collection<T> objects, Function<T, String> mapper) {
        return objects.stream().map(mapper).toArray(String[]::new);
    }

    public static double mutateValue(double value) {
        return value * random(0.5, 2) + random(-1D, 1D);
    }
}
