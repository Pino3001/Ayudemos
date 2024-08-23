package Ayudemos.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarDonacionGUI extends JFrame {
    private JPanel background;
    private JPanel panelTitulo;
    private JPanel panelBotAliArti;
    private JPanel cardAlimentoArticulo;
    private JButton buttonAlimento;
    private JButton buttonArticulo;
    private JPanel panelAlimento;
    private JComboBox comboBuscarAlimento;
    private JTextField textFieldDescAlimento;
    private JSpinner spinnerPesoAlimento;
    private JButton aceptarAlimento;
    private JButton cancelarAlimento;
    private JPanel panelArticulo;
    private JComboBox comboBoxBuscarArticulo;
    private JTextField fieldDescArticulo;
    private JSpinner spinnerCantidadArticulos;
    private JButton buttonAceptarArticulo;
    private JButton buttonCancelarArticulo;
    private JTextField fieldDimensionesArticulo;
    private CardLayout cardLayout;

    public ModificarDonacionGUI() {
        cardLayout = new CardLayout();
        cardAlimentoArticulo.setLayout(cardLayout);
        cardAlimentoArticulo.add(panelAlimento, "alimento");
        cardAlimentoArticulo.add(panelArticulo, "articulo");

        //Color del estilo de la vista
        Color customColor = new Color(27, 69, 26);
        // Color y grosor del borde
        Border border = BorderFactory.createLineBorder(customColor, 1);
        // Asignar el borde
        comboBuscarAlimento.setBorder(border);
        fieldDescArticulo.setBorder(border);
        spinnerPesoAlimento.setBorder(border);
        textFieldDescAlimento.setBorder(border);
        comboBoxBuscarArticulo.setBorder(border);
        fieldDimensionesArticulo.setBorder(border);
        spinnerCantidadArticulos.setBorder(border);

        buttonAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "alimento");
            }
        });
        buttonArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "articulo");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.background = new JPanel();
        setContentPane(background);
        setSize(700,500);
    }
}
