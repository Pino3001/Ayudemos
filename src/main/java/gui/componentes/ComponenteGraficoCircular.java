package gui.componentes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import types.Barrio;

import javax.swing.*;
        import java.awt.*;
        import java.util.Map;

public class ComponenteGraficoCircular extends JComponent {

    public ComponenteGraficoCircular(Map<String, Integer> data, String title) {
        // Configurar el layout del panel
        setLayout(new BorderLayout());
        setBackground(ColorUtil.getColor("backgroundColor"));
        // Crear el gráfico de pastel
        JFreeChart pieChart = createPieChart(data, title);

        // Crear un panel de gráfico (ChartPanel) y añadirlo a este panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(150, 150));  // Tamaño preferido del gráfico
        add(chartPanel, BorderLayout.CENTER);  // Agregar el panel del gráfico al centro del JPanel
    }

    private JFreeChart createPieChart(Map<String, Integer> data, String title) {
        // Crear un conjunto de datos para el gráfico de pastel
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Crear el gráfico de pastel
        JFreeChart pieChart = ChartFactory.createPieChart(
                title,    // Título del gráfico
                dataset,  // Conjunto de datos
                true,     // Incluir leyenda
                true,
                false
        );
        pieChart.setBackgroundPaint(ColorUtil.getColor("backgroundColor"));
        pieChart.getLegend().setBackgroundPaint(ColorUtil.getColor("backgroundColor"));
        pieChart.getTitle().setFont(new Font("Roboto Light", Font.PLAIN, 16));
        pieChart.getTitle().setPaint(ColorUtil.getColor("primaryColor"));


        // Configurar el gráfico
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setOutlineVisible(false);  // Quitar el borde del gráfico de pastel
        plot.setBackgroundPaint(ColorUtil.getColor("backgroundColor"));  // Fondo blanco
        plot.setLabelFont(new Font("Roboto Light", Font.PLAIN, 11));
        plot.setLabelBackgroundPaint(null);  // Quitar fondo de etiqueta
        // Quitar borde de etiqueta
        plot.setLabelShadowPaint(null);      // Quitar sombra de etiqueta
        // Personalizar los colores de cada sección del gráfico de pastel
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            // Castear el String de vuelta al tipo Barrio
            try {
                Barrio barrio = Barrio.valueOf(entry.getKey());
                Color color = barrio.getColor();  // Obtener el color asociado con el barrio
                plot.setSectionPaint(barrio.toString(), color);  // Establecer el color de la sección
            } catch (IllegalArgumentException e) {
                System.err.println("Barrio no válido: " + entry.getKey());
            }
        }
        return pieChart;
    }
}
