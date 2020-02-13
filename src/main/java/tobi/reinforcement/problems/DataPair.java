package tobi.reinforcement.problems;

public interface DataPair<Instance,Label> {
    Instance getInstance();
    Label getLabel();
}
