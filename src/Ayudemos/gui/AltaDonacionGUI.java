package Ayudemos.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class AltaDonacionGUI extends JFrame {
    private JPanel panelAltaDonacion;
    private JButton botonArticulo;
    private JPanel panelAlimento;
    private JButton botonAlimento;
    private JPanel cardAlimentoArticulo;
    private JButton aceptarAlimento;
    private JSpinner spinnerCantidad;
    private JButton botonCancelarAlimento;
    private JLabel titulo;
    private JPanel paneltitulo;
    private JTextField txtDescrAlimento;
    private JPanel panelArticulo;
    private JTextField textfieldDescArticulo;
    private JSpinner spinerPesoArticulo;
    private JTextField textFieldDimensiones;
    private JButton botonAceptarArticulo;
    private JButton botonCancelarArticulo;
    private JLabel descripcionArticulotitle;
    private JLabel txtPeso;
    private JLabel txtdimensiones;
    private CardLayout cardLayout;


    public AltaDonacionGUI() {
        cardLayout = new CardLayout();
        cardAlimentoArticulo.setLayout(cardLayout);
        cardAlimentoArticulo.add(panelAlimento, "alimento");
        cardAlimentoArticulo.add(panelArticulo, "articulo");

        //Color del estilo de la vista
        Color customColor = new Color(9, 35, 48);
        // Asignar el borde al JTextField
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, customColor);
        txtDescrAlimento.setBorder(border);
        textfieldDescArticulo.setBorder(border);
        textFieldDimensiones.setBorder(border);

        botonAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "alimento");
            }
        });
        botonAlimento.addMouseListener(new MouseAdapter() {
        });
        botonArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "articulo");
            }
        });

        // Quitar el texto al hacer click
        textfieldDescArticulo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textfieldDescArticulo.getText().equals("Ingrese la descripcion aquí...")) {
                    textfieldDescArticulo.setText("");
                }
            }
        });
        txtDescrAlimento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDescrAlimento.getText().equals("Ingrese la descripcion aquí...")) {
                    txtDescrAlimento.setText("");
                }
            }
        });
        textFieldDimensiones.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldDimensiones.getText().equals("Ingrese la dimension aquí..")) {
                    textFieldDimensiones.setText("");
                }
            }
        });
        // Volver al colocarlo si esta vacion el textfield
        textfieldDescArticulo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textfieldDescArticulo.getText().isEmpty()) {
                    textfieldDescArticulo.setText("Ingrese la descripcion aquí...");
                }
            }
        });
        textFieldDimensiones.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldDimensiones.getText().isEmpty()) {
                    textFieldDimensiones.setText("Ingrese la dimension aquí..");
                }
            }
        });
        txtDescrAlimento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtDescrAlimento.getText().isEmpty()) {
                    txtDescrAlimento.setText("Ingrese la descripcion aquí...");
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.panelAltaDonacion = new JPanel();
        setContentPane(panelAltaDonacion);
        setSize(700,500);

    }
}
