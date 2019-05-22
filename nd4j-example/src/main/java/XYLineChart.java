import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class XYLineChart extends JFrame {
	
	public XYLineChart(){
		super("XY Line Chart Example with JFreechart");
		
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() {
		// creates a line chart object
		// returns the chart panel
		
		String chartTitle = "Objects Movement Chart";
		String xAxisLabel = "X";
		String yAxisLabel = "Y";
		
		XYDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,false,true,true);
		
		return new ChartPanel(chart);
	}
	
	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Object 1");
		XYSeries series2 = new XYSeries("Object 2");
		XYSeries series3 = new XYSeries("Object 3");
		
		series1.add(1.0, 2.0);
		series1.add(2.0, 3.0);
		series1.add(3.0, 2.5);
		series1.add(3.5, 2.8);
		series1.add(4.2, 6.0);
		
		series2.add(2.0, 1.0);
		series2.add(2.5, 2.4);
		series2.add(3.2, 1.2);
		series2.add(3.9, 2.8);
		series2.add(4.6, 3.0);
		
		series3.add(1.2, 4.0);
		series3.add(2.5, 4.4);
		series3.add(3.8, 4.2);
		series3.add(4.3, 3.8);
		series3.add(4.5, 4.0);
		
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		
		return dataset;
	}
	
	
}
