package tobi.reinforcement.problems;

import tobi.reinforcement.network.Network;

public abstract class LabeledProblem<Instance, Label> {

    public abstract double test(Network network);

    public abstract Instance[] getDataSet();
    public abstract Label getLabel(int instanceIndex);
}
