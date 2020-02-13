package tobi.reinforcement;

import tobi.reinforcement.network.Network;

public interface Problem<Instance, Label> {
//    protected abstract Instance[] getTrainingData();

    double test(Network network);

    default double testLog(Network network) {
        return test(network) * Math.log(network.getNeurons().size());
    }

//    protected abstract Label label(Instance instance);

    int getLabelLength();

    int getInstanceLength();
}
