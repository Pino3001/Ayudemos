package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import gui.componentes.ComponenteGraficoCircular;

import javax.swing.*;
        import java.awt.*;
        import java.util.HashMap;
import java.util.Map;

public class Temporal {
    public static void main(String[] args) {
        // Crear datos para el gráfico
        Map<String, Integer> data = new HashMap<>();
        data.put("Barrio A", 40);
        data.put("Barrio B", 25);
        data.put("Barrio C", 35);

        // Crear un JFrame para contener el gráfico
        JFrame frame = new JFrame("Ejemplo de Gráfico de Pastel");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 150);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        // Crear una instancia de PieChartPanel con los datos y el título
        //ComponenteGraficoCircular pieChartPanel = new ComponenteGraficoCircular(data, "Distribución por Barrio");

        // Agregar el componente del gráfico al JFrame
       // panel.add(pieChartPanel, BorderLayout.CENTER);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
}
