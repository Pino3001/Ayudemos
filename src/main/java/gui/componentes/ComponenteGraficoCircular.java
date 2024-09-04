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

    public ComponenteGraficoCircular(Map<String, Integer> data, String title, Map<Barrio, Color> rowColors) {
        // Configurar el layout del panel
        setLayout(new BorderLayout());
        setBackground(ColorUtil.getColor("backgroundColor"));
        // Crear el gráfico de pastel
        JFreeChart pieChart = createPieChart(data, title, rowColors);

        // Crear un panel de gráfico (ChartPanel) y añadirlo a este panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(150, 150));  // Tamaño preferido del gráfico
        add(chartPanel, BorderLayout.CENTER);  // Agregar el panel del gráfico al centro del JPanel
    }

    private JFreeChart createPieChart(Map<String, Integer> data, String title, Map<Barrio, Color> rowColors) {
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

        // Configurar el gráfico
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(ColorUtil.getColor("backgroundColor"));  // Fondo blanco
        plot.setLabelFont(new Font("Roboto Light", Font.PLAIN, 11));
        plot.setLabelBackgroundPaint(null);  // Quitar fondo de etiqueta
        // Quitar borde de etiqueta
        plot.setLabelShadowPaint(null);      // Quitar sombra de etiqueta
        // Personalizar los colores de cada sección del gráfico de pastel
        for (Map.Entry<Barrio, Color> entry : rowColors.entrySet()) {
            String key = entry.getKey().toString();  // Convertir Barrio a String
            Color color = entry.getValue();
            plot.setSectionPaint(key, color);  // Establecer el color para cada sección
        }
        return pieChart;
    }
}
