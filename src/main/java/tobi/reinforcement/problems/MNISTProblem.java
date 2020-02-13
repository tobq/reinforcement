package tobi.reinforcement.problems;//package tobi.reinforcement.problems;
//
//import tobi.reinforcement.Problem;
//import tobi.reinforcement.network.Network;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//
//public class MNISTProblem implements Problem<Byte[], Integer> {
//    private static final int IMAGE_SIZE = 28;
//    private static final int INSTANCE_LENGTH = IMAGE_SIZE * IMAGE_SIZE;
//    private static final int TRAINING_INSTANCES_MAGIC_NUMBER = 2051;
//    private static final int TRAINING_LABELS_MAGIC_NUMBER = 2049;
//    private static MNISTPair[] TRAINING_DATA;
//
//    static {
//        try {
//            TRAINING_DATA = readData("train");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//    private static MNISTPair[] readData(String type) throws IOException {
//        try (DataInputStream labelStream = new DataInputStream(MNISTProblem.class.getResourceAsStream("datasets/MNIST/" + type + "/labels"))) {
//            try (DataInputStream instanceStream = new DataInputStream(MNISTProblem.class.getResourceAsStream("datasets/MNIST/" + type + "/instances"))) {
//
//                int firstIntInstances = instanceStream.readInt();
//                if (firstIntInstances != TRAINING_INSTANCES_MAGIC_NUMBER)
//                    throw new RuntimeException("Invalid instance data. Expected magic number: " + TRAINING_INSTANCES_MAGIC_NUMBER + ", but got: " + firstIntInstances);
//                int firstIntLabels = labelStream.readInt();
//                if (firstIntLabels != TRAINING_LABELS_MAGIC_NUMBER)
//                    throw new RuntimeException("Invalid label data. Expected magic number: " + TRAINING_LABELS_MAGIC_NUMBER + ", but got: " + firstIntLabels);
//
//                int instanceCount = instanceStream.readInt();
//                int labelCount = labelStream.readInt();
//
//                if (labelCount != instanceCount)
//                    throw new RuntimeException("Mismatched label / instance count: " + instanceCount + " instances, " + labelCount + " labels");
//
//
//                int rowCount = instanceStream.readInt();
//                if (rowCount != IMAGE_SIZE)
//                    throw new RuntimeException("Unexpected row count. Expected: " + IMAGE_SIZE + ", but got: " + rowCount);
//                int columnCount = instanceStream.readInt();
//                if (columnCount != IMAGE_SIZE)
//                    throw new RuntimeException("Unexpected column count. Expected: " + IMAGE_SIZE + ", but got: " + columnCount);
//
//                MNISTPair[] data = new MNISTPair[instanceCount];
//                for (int i = 0; i < data.length; i++) {
//                    Byte[] instance = new Byte[INSTANCE_LENGTH];
//                    for (int p = 0; p < instance.length; p++) {
//                        instance[p] = instanceStream.readByte();
//                    }
//                    int label = labelStream.readByte();
//                    data[i] = new MNISTPair(instance, label);
//                }
//                return data;
//            }
//        }
//    }
//
//    public double test(Network network) {
//        double fitness = 0;
//        for (MNISTPair data : TRAINING_DATA) {
//            double[] outputs = network.compute(data.instance);
//            for (int i = 0; i < data.label; i++) {
//                fitness -= Math.abs(outputs[i]);
//            }
//            fitness -= Math.abs(1 - outputs[data.label]);
//            for (int i = data.label + 1; i < outputs.length; i++) {
//                fitness -= Math.abs(outputs[i]);
//            }
//        }
//        if (Double.isNaN(fitness)) return Double.NEGATIVE_INFINITY;
//        return fitness;
//    }
//
//    public int getLabelLength() {
//        return 10;
//    }
//
//    public int getInstanceLength() {
//        return INSTANCE_LENGTH;
//    }
//
//    public static class MNISTPair implements DataPair<Byte[], Integer> {
//        private final Byte[] instance;
//        private final Integer label;
//
//        MNISTPair(Byte[] instance, Integer label) {
//            this.instance = instance;
//            this.label = label;
//        }
//
//        @Override
//        public Byte[] getInstance() {
//            return instance;
//        }
//
//        @Override
//        public Integer getLabel() {
//            return label;
//        }
//    }
//}
//
