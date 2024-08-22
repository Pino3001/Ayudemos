package Ayudemos.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class AltaDonacionGUI extends JFrame {
    private JPanel panelAltaDonacion;
    private JButton botonArticulo;
    private JPanel panelAlimento;
    private JButton botonAlimento;
    private JPanel cardAlimentoArticulo;
    private JButton aceptarAlimento;
    private JLabel descripciontitle;
    private JLabel cantidadTxt;
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
        Color customColor = new Color(27, 69, 26);
        // Asignar el borde al JTextField
        Border border = BorderFactory.createLineBorder(customColor, 1); // Color y grosor del borde
        txtDescrAlimento.setBorder(border);

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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.panelAltaDonacion = new JPanel();
        setContentPane(panelAltaDonacion);
        setSize(700,500);

    }
}
