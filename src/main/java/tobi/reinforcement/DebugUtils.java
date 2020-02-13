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
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import tobi.reinforcement.network.Network;
import tobi.reinforcement.network.neuron.Neuron;
import tobi.reinforcement.problems.Approximate;

import javax.swing.*;
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

    private static Graph<Neuron, DefaultEdge> buildGraph(Network network) {
        Graph<Neuron, DefaultEdge> graph = GraphTypeBuilder
                .<Neuron, DefaultEdge>directed()
                .allowingMultipleEdges(true)
                .allowingSelfLoops(false)
                .weighted(false)
                .edgeClass(DefaultEdge.class)
                .buildGraph();
        Set<Neuron> neurons = network.getNeurons();
        for (Neuron neuron : neurons) {
            graph.addVertex(neuron);
        }
        for (Neuron neuron : neurons) {
            for (Neuron input : network.getInputs(neuron)) {
                graph.addEdge(input, neuron);
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

    private static void showGraph(Graph<Neuron, DefaultEdge> graph) {
        final JFrame frame = new JFrame("Neural Network Topology");
        // configure window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.validate();

        JGraphXAdapter<Neuron, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);
        new mxCircleLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        frame.add(new mxGraphComponent(graphAdapter), 0);
        frame.validate();
    }
}
