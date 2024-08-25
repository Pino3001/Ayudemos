package Ayudemos.gui.componentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ComponenteSpinner {
    private JSpinner spinner;
    private final Color colorBorde = new Color(9, 35, 48);
    private final Color colorBotones = new Color(9, 35, 48);
    private final Color colorFlecha = new Color(249, 243, 249);
    private final Color colorFondo = new Color(230, 224, 230);
    private final Border border = new LineBorder(colorBorde, 2);


        // Constructor que toma un JTextField existente y lo modifica
    public ComponenteSpinner(JSpinner spinner) {
            this.spinner = spinner;
            this.spinner.setBorder(border);

        // Cambia el fondo por defecto del spinner
        this.spinner.getEditor().getComponent(0).setBackground(colorFondo);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER); // Centramos el contenido
            textField.setCaretColor(colorBorde); // Cambiamos el color del caret
        }
        // Cambiar el color de los botones de incremento y decremento
        for (Component component : spinner.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(colorBotones); // Cambia el color de fondo del botón
                button.setForeground(colorFlecha); // Cambia el color del texto/icono del botón
            }
        }

    }
}
