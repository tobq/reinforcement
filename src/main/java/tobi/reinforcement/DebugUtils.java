package tobi.reinforcement;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import tobi.reinforcement.network.Network;
import tobi.reinforcement.network.Synapse;
import tobi.reinforcement.network.neuron.Neuron;
import tobi.reinforcement.problems.Approximate;

import javax.swing.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public class DebugUtils {

    private static void plotChart(ChartPanel chartPanel, Network network, DoubleUnaryOperator goal) {
        final XYSeries h = new XYSeries("Hypothesis");
        final XYSeries f = new XYSeries("Goal");
        for (int i = 0; i < Approximate.INSTANCES; i++) {
//            double x = Utils.random(MIN_X, MAX_X);
            double x = Approximate.MIN_X + i * Approximate.increment;
            double hx = network.compute(x)[0];
            h.add(x, hx);
            f.add(x, goal.applyAsDouble(x));
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(h);
        data.addSeries(f);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                "x",
                "h(x)",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chartPanel.setChart(chart);
    }

    public static Graph<Neuron, DefaultWeightedEdge> buildGraph(Network network) {
        Graph<Neuron, DefaultWeightedEdge> graph = GraphTypeBuilder
                .<Neuron, DefaultWeightedEdge>directed()
                .allowingMultipleEdges(true)
                .allowingSelfLoops(false)
                .weighted(true)
                .edgeClass(DefaultWeightedEdge.class)
                .buildGraph();

        Set<Neuron> neurons = network.getNeurons();
        for (Neuron neuron : neurons) {
            graph.addVertex(neuron);
        }
        for (Neuron neuron : neurons) {
            for (Synapse input : network.getInputs(neuron)) {
                DefaultWeightedEdge edge = graph.addEdge(input.getNeuron(), neuron);
                graph.setEdgeWeight(edge, input.getStrength());
            }
            for (Neuron output : network.getVarOutputs(neuron)) {
                graph.addEdge(neuron, output);
            }
        }
        return graph;
    }

    public static void showGraph(Network network) {
        showGraph(buildGraph(network));
    }

    private static void showGraph(Graph<Neuron, DefaultWeightedEdge> graph) {
        final JFrame frame = new JFrame("Neural Network Topology");
        // configure window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.validate();

        JGraphXAdapter<Neuron, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<>(graph);
        new mxCircleLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        frame.add(new mxGraphComponent(graphAdapter), 0);
        frame.validate();
    }

    public static String formatNeuronInputs(Map<Neuron, Synapse[]> neuronInputs) {
        return Arrays.toString(Utils.toString(neuronInputs.entrySet(), entry -> entry.getKey() + " => " + Arrays.toString(entry.getValue())));
    }

    public static void main(String[] args) {
        String json = "\n" +
                "{\"outputs\":5,\"constValues\":{},\"neuronInputs\":{\"IN#0\":[],\"T1#6e994843\":[{\"strength\":0.43186103849802926,\"neuron\":\"VAR#5afb93d8\"}],\"OUT#0\":[{\"strength\":0.6407256598892951,\"neuron\":\"T1#6e994843\"}],\"VAR#5afb93d8\":[],\"T1#4b5e7d0a\":[{\"strength\":-1.4971302987499076,\"neuron\":\"OUT#4\"}],\"OUT#2\":[{\"strength\":-0.4266100945313216,\"neuron\":\"VAR#80c495c\"}],\"VAR#80c495c\":[],\"OUT#1\":[{\"strength\":0.17648495090858723,\"neuron\":\"VAR#5afb93d8\"}],\"OUT#4\":[{\"strength\":0.7538182683407051,\"neuron\":\"IN#0\"}],\"OUT#3\":[{\"strength\":1.4611158207348893,\"neuron\":\"T1#4b5e7d0a\"}]},\"varOutputs\":{\"IN#0\":[],\"T1#6e994843\":[],\"TANH#35e8f749\":[],\"OUT#0\":[],\"VAR#5afb93d8\":[],\"T1#4b5e7d0a\":[],\"OUT#2\":[\"VAR#80c495c\",\"VAR#5afb93d8\"],\"VAR#80c495c\":[],\"OUT#1\":[],\"OUT#4\":[],\"OUT#3\":[\"VAR#80c495c\"]},\"inputs\":1}";
        Network net = Network.parse(json);
        DebugUtils.showGraph(net);
    }
}
