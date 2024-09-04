package gui;

import gui.componentes.ColorUtil;
import gui.componentes.ComponenteGraficoCircular;
import gui.componentes.ComponenteTextField;
import types.Barrio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ZonasMayorDistribucionGUI extends JFrame {
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JPanel background;
    private JTable tableZonas;
    private JPanel panelGrafico;
    private JPanel panelbotones;
    private JPanel panelReporte;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panle7;
    private JScrollPane scrolpanel1;
    private JPanel paneltitulo;
    private JPanel panelGeneral;
    private JPanel panel8;

    // Mapa para almacenar el color de cada barrio
    private Map<Barrio, Color> rowColors;

    public ZonasMayorDistribucionGUI() {

        aplicarEstilos();
        rowColors = new HashMap<>();  // Inicializamos el mapa

        // Crear modelo de tabla con dos columnas
        String[] columnNames = {"Barrio", "Cantidad"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableZonas.setModel(tableModel);
        tableZonas.setFont(new Font("Roboto ligth", Font.BOLD, 13));
        tableZonas.setShowGrid(false);
        tableZonas.getColumnModel().getColumn(0).setPreferredWidth(150);  // Ancho preferido para la columna de "Barrio"
        tableZonas.setRowHeight(20);  // Altura de cada fila
        tableZonas.setRowMargin(6);  // Margen entre filas

        // Renderizador personalizado para manejar color y alineación
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtener el color de la fila basado en el barrio
                Barrio barrio = (Barrio) table.getValueAt(row, 0);  // Obtenemos el barrio de la fila actual
                Color color = rowColors.get(barrio);  // Obtenemos el color asociado a ese barrio

                // Si no está seleccionado, usar el color personalizado
                if (!isSelected && color != null) {
                    cell.setForeground(color);
                } else {
                    cell.setBackground(table.getSelectionBackground());
                }

                // Alinear el texto en función de la columna
                if (column == 0) {  // Columna "Barrio"
                    setHorizontalAlignment(SwingConstants.LEFT);
                } else {  // Columna "Cantidad"
                    setHorizontalAlignment(SwingConstants.RIGHT);
                }

                return cell;
            }
        };

        // Aplicar el renderizador personalizado a todas las columnas
        tableZonas.setDefaultRenderer(Object.class, customRenderer);

        // Agregar datos a la tabla y establecer colores de filas
        // Agregar datos a la tabla y establecer colores de filas
        int cantidad = 300;
        Map<String, Integer> data = new HashMap<>();
        for (Barrio barrio : Barrio.values()) {
            tableModel.addRow(new Object[]{barrio, cantidad});
            data.put(barrio.toString(), cantidad);
            // Generar un color pastel aleatorio para cada barrio
            Color randomPastelColor = ColorUtil.generateRandomColor();

            // Establecer el color de la fila en el mapa basado en el barrio
            rowColors.put(barrio, randomPastelColor);
            cantidad -= 60;

        }


// Llamamos a createPieChart() después de inicializar y agregar el panel
        // Crear una instancia de PieChartPanel con los datos y el título
        ComponenteGraficoCircular pieChartPanel = new ComponenteGraficoCircular(data, "Distribución por Barrio", rowColors);
        panelGrafico.add(pieChartPanel);
        // Agregar panelGrafico al contenedor principal
        repaint();
        revalidate();
    }


    // Método para obtener el color de un barrio específico
    public Color getRowColor(Barrio barrio) {
        return rowColors.get(barrio);
    }

    private void createUIComponents() {
        this.background = new JPanel();

        background.setBackground(ColorUtil.getColor("primaryColor"));
        setContentPane(background);
        setSize(500, 600);
    }

    private void aplicarEstilos() {
        button1.setBackground(ColorUtil.getColor("backgroundColor"));
        background.setBackground(ColorUtil.getColor("backgroundColor"));
        panelbotones.setBackground(ColorUtil.getColor("backgroundColor"));
        panelGrafico.setBackground(ColorUtil.getColor("backgroundColor"));
        panel1.setBackground(ColorUtil.getColor("backgroundColor"));
        panel2.setBackground(ColorUtil.getColor("backgroundColor"));
        panel3.setBackground(ColorUtil.getColor("backgroundColor"));
        panel4.setBackground(ColorUtil.getColor("backgroundColor"));
        panelReporte.setBackground(ColorUtil.getColor("backgroundColor"));
        panel5.setBackground(ColorUtil.getColor("backgroundColor"));
        panel6.setBackground(ColorUtil.getColor("backgroundColor"));
        panle7.setBackground(ColorUtil.getColor("backgroundColor"));
        scrolpanel1.setBackground(ColorUtil.getColor("backgroundColor"));
        paneltitulo.setBackground(ColorUtil.getColor("primaryColor"));
        panelGeneral.setBackground(ColorUtil.getColor("backgroundColor"));
        scrolpanel1.setBackground(ColorUtil.getColor("backgroundColor"));
        panel8.setBackground(ColorUtil.getColor("backgroundColor"));
        new ComponenteTextField(textField1, "");
        new ComponenteTextField(textField2, "");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ZonasMayorDistribucionGUI");
        frame.setContentPane(new ZonasMayorDistribucionGUI().background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
