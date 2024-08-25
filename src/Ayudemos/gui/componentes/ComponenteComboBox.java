package Ayudemos.gui.componentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class ComponenteComboBox {
    private JComboBox comboBox;
    private final Color colorBorde = new Color(9, 35, 48);
    private final Color colorFondo = new Color(230, 224, 230);
    private final Color colorSelected = new Color(110, 126, 227);
    private final Border border = new LineBorder(colorBorde, 2);
    private final Color colorFlecha = new Color(232, 225, 232);

    public ComponenteComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
        Font currentFont = comboBox.getFont();
        // Cambiamos la flecha del combo
        comboBox.setUI(new MyArrow());

        // Cambiamos el render y los colores por defecto.
        comboBox.setRenderer(new MyComboBoxRender());
        comboBox.setBorder(border);
        comboBox.setBackground(colorFondo);
        comboBox.setForeground(colorBorde);
        comboBox.setFont(currentFont);
    }
        // Renderiza estilos del combo.
        public class MyComboBoxRender extends BasicComboBoxRenderer {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // La llamada a super hace que coja los colores defecto según esté seleccionado, tenga foco, etc.
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Cambiamos color de fondo y letra del item del combo desplegado.
                if (isSelected) {
                    this.setForeground(colorBorde);
                    this.setBackground(colorSelected);
                } else {
                    this.setBackground(colorFondo);
                    this.setForeground(colorBorde);
                }
                // list dibuja el elemento visible del combo cuando no está desplegado.
                list.setSelectionBackground(colorFondo);
                list.setSelectionForeground(colorBorde);
                return this;
            }
        }

        // Crea el botón para el combo.
        public  class MyArrow extends BasicComboBoxUI {
            @Override
            protected JButton createArrowButton() {
                // Cambio el estilo del boton del combo
                JButton button = new BasicArrowButton(SwingConstants.SOUTH, colorBorde, colorBorde, colorFlecha, Color.WHITE);
                button.setSize(200,200);
                return button;
            }
        }
}
