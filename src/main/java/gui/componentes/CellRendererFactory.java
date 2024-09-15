package gui.componentes;

import types.Barrio;
import types.EstadoBeneficiario;
import types.EstadoDistribucion;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRendererFactory {

    public CellRendererFactory() {
    }

    public TableCellRenderer getEstadoBeneficiarioRenderer() {
        return new EstadoBeneficiarioCellRenderer();
    }

    public TableCellRenderer getEstadoDistribucionRenderer() {
        return new EstadoDistribucionCellRenderer();
    }

    // Renderer personalizado para la columna "EstadoBrneficiario"
    public class EstadoBeneficiarioCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (EstadoBeneficiario.ACTIVO.equals(value)) {
                c.setBackground(new Color(0x62FF65));
            } else if (EstadoBeneficiario.SUSPENDIDO.equals(value)) {
                c.setBackground(new Color(0xFFF46464, true));
            } else {
                c.setBackground(ColorUtil.getColor("backgroundColor"));
            }

            return c;
        }
    }

    // Renderer personalizado para la columna "Estado" --Pinta el fondo de la fila estado si esta existe en la tabla
    private static class EstadoDistribucionCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (EstadoDistribucion.ENTREGADO.equals(value)) {
                c.setBackground(new Color(0x62FF65));
            } else if (EstadoDistribucion.PENDIENTE.equals(value)) {
                c.setBackground(new Color(0xFFF86868, true));
            } else if (EstadoDistribucion.EN_CAMINO.equals(value)) {
                c.setBackground(new Color(0x7777FF));
            } else {
                c.setBackground(ColorUtil.getColor("backgroundColor"));
            }

            return c;
        }
    }
}
