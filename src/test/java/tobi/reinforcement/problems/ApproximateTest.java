package tobi.reinforcement.problems;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tobi.reinforcement.network.Network;

import javax.swing.*;
import java.util.function.DoubleUnaryOperator;

class ApproximateTest {

    public static void main(String[] args) {
        final ChartPanel chartPanel = new ChartPanel(ChartFactory.createXYLineChart(
                "",
                "x",
                "h(x)",
                new XYSeriesCollection()
        ));
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(chartPanel);
        jFrame.pack();
        jFrame.setVisible(true);

        Network network = Network.parse("{\"outputs\":1,\"constValues\":{\"CONST#6ab1a338\":-25.00475042626254},\"neuronInputs\":{\"IN#0\":[],\"ADD#71dedee5\":[\"CONST#6ab1a338\",\"CONST#6ab1a338\"],\"MULTIPLY#2eaf5d7d\":[\"IN#0\",\"IN#0\"],\"MAX#7be7ec20\":[\"MULTIPLY#2eaf5d7d\",\"CONST#6ab1a338\"],\"SUBTRACT#1cc21ce5\":[\"ADD#d87b6b1\",\"SIN#45d046ab\"],\"ADD#d87b6b1\":[\"MAX#7be7ec20\",\"MIN#7f8c504d\"],\"OUT#0\":[\"SUBTRACT#1cc21ce5\"],\"CONST#6ab1a338\":[],\"SUBTRACT#70875ab9\":[\"MULTIPLY#2eaf5d7d\",\"ADD#71dedee5\"],\"MIN#7f8c504d\":[\"RELU#76e1bc05\",\"IN#0\"],\"RELU#76e1bc05\":[\"SIN#45d046ab\"],\"SIN#45d046ab\":[\"SUBTRACT#70875ab9\"]},\"varOutputs\":{\"IN#0\":[],\"ADD#71dedee5\":[\"IN#0\"],\"MULTIPLY#2eaf5d7d\":[],\"MAX#7be7ec20\":[\"IN#0\"],\"SUBTRACT#1cc21ce5\":[],\"ADD#d87b6b1\":[],\"OUT#0\":[\"IN#0\"],\"CONST#6ab1a338\":[],\"SUBTRACT#70875ab9\":[],\"MIN#7f8c504d\":[\"IN#0\"],\"RELU#76e1bc05\":[],\"SIN#45d046ab\":[]},\"inputs\":1}\n");
        plotChart(chartPanel, network, x -> x * x - 50D);
    }

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
}