package Ayudemos.gui.componentes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ComponenteTextField {
    private JTextField textField;
    private final Color colorBorde = new Color(9, 35, 48);
    private final Color colorFondo = new Color(230, 224, 230);
    private final Border border = new LineBorder(colorBorde, 2);

    // Constructor que toma un JTextField existente y lo modifica
    public ComponenteTextField(JTextField textField, String textoPorDefecto) {
        this.textField = textField;
        this.textField.setBorder(border);
        this.textField.setBackground(colorFondo);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setCaretColor(colorBorde);
        textField.setText(textoPorDefecto);

        // Quitar el texto por defecto al hacer click
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(textoPorDefecto)) {
                    textField.setText("");
                }
            }
        });
        // Volver al colocarlo si esta vacion el textfield
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(textoPorDefecto);
                }
            }
        });
    }
}