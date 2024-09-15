package gui.componentes;

import gui.ListarBeneficiariosGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableListCellRenderer extends JPanel implements ListCellRenderer<JTable> {

    // Renderer personalizado para JTable dentro de JList
    private final JPanel panel;
    private final JTable table;
    private final TableCellRenderer defaultTableCellRenderer;

    public TableListCellRenderer(TableCellRenderer defaultTableCellRenderer) {
        this.defaultTableCellRenderer = defaultTableCellRenderer;
        this.panel = new JPanel(new BorderLayout());
        this.table = new JTable(); // Crear un JTable reutilizable

        // Configuración inicial del panel
        setLayout(new BorderLayout());
        panel.add(table, BorderLayout.CENTER); // Añadir JTable al panel
        setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10)); // Margen entre tablas
        add(panel, BorderLayout.CENTER);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends JTable> list, JTable value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Configurar selección y fondo
        setBackground(isSelected ? ColorUtil.getColor("backgroundColor") : list.getBackground()); // No se muestra ningun borde al seleccionar
        panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());

        // Actualizar contenido del JTable
        table.setModel(value.getModel()); // Actualizar solo el modelo de datos
        table.setShowGrid(false);
        table.setRowHeight(18);
        table.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        table.setFont(new Font("Roboto light", Font.PLAIN, 14));
        table.setForeground(ColorUtil.getColor("primaryColor"));
        table.setPreferredScrollableViewportSize(new Dimension(450, 90));
        table.setFillsViewportHeight(true);
        // Configurar el renderer de la columna "Estado"
        if (table.getColumnCount() > 1 && defaultTableCellRenderer != null) {  // Asegura que la tabla tenga la columna "Estado"
            table.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
        }

        // Redibujar el componente
        revalidate();
        repaint();
        return this;
    }
}


