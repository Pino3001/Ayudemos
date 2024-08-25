package Ayudemos.gui;

import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.excepciones.CamposIncompletosExeption;
import Ayudemos.gui.AlertasGUI.AlertaIngresoGUI;
import Ayudemos.gui.componentes.ComponenteComboBox;
import Ayudemos.gui.componentes.ComponenteSpinner;
import Ayudemos.gui.componentes.ComponenteTextField;
import Ayudemos.interfaces.IAltaDonacion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class AltaDonacionGUI extends JFrame {
    private IAltaDonacion altaDonacion;
    private JPanel background;
    private JButton buttonNuevoAlimento;
    private JButton buttonNuevoArticulo;
    private JTextField textDescripcionAlimento;
    private JSpinner spinnerCantidad;
    private JButton buttonCancelarAlimento;
    private JButton buttonAceptarAlimento;
    private JTextField textDescripcionArticulo;
    private JSpinner spinnerPeso;
    private JTextField textDimensiones;
    private JButton buttonCancelarArticulo;
    private JButton buttonAceptarArticulo;
    private JPanel cardAlimentoArticulo;
    private JPanel panelAlimento;
    private JPanel panelArticulo;
    private CardLayout cardLayout;
    private final String textoPorDefectoDescAlimento = "Ingrese la descripcion del Alimento...";
    private final String textoPorDefectoDescArticulo = "Ingrese la descripcion Articulo...";
    private final String textoPorDefectoDimensiArticulos = "Ingrese las dimensiones del Articulo...";
    private AlertaIngresoGUI alerta;

    public AltaDonacionGUI(IAltaDonacion altaDonacion) {
        cardLayout = new CardLayout();
        this.altaDonacion = altaDonacion;
        cardAlimentoArticulo.setLayout(cardLayout);
        cardAlimentoArticulo.add(panelAlimento, "alimento");
        cardAlimentoArticulo.add(panelArticulo, "articulo");
        aplicarEstilos();
    }

    // Aplica estilos personalizados que no están en él .form
    private void aplicarEstilos() {
        // Crear un modelo de SpinnerNumberModel con valores flotantes -- Ver de mejorar -
        SpinnerNumberModel modelFloat = new SpinnerNumberModel(0.0f, 0.0f, 100.0f, 0.1f);
        // Configurar el formato del JSpinner para mostrar valores flotantes
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerPeso, "0.00");
        spinnerPeso.setEditor(editor);

        //Los componentes creados en el .Form los edito con estas clases.
        new ComponenteTextField(textDescripcionAlimento, textoPorDefectoDescAlimento);
        new ComponenteTextField(textDescripcionArticulo, textoPorDefectoDescArticulo);
        new ComponenteTextField(textDimensiones, textoPorDefectoDimensiArticulos);
        new ComponenteSpinner(spinnerCantidad);
        new ComponenteSpinner(spinnerPeso);
        actionBotonesNavDonaciones();
        actionBotonesAceptarCancelar();
    }

    // Comportamiento de navegacion de los botones: Nuevo Artículo y Nuevo Alimento
    private void actionBotonesNavDonaciones() {
        // Inflar panel de nuevo alimento
        buttonNuevoAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "alimento");
            }
        });
        // Inflar panel de nuevo articulo
        buttonNuevoArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "articulo");
            }
        });
    }

    // Comportamiento de los botones aceptar y cancelar
    private void actionBotonesAceptarCancelar() {
        // Aceptar articulo
        buttonAceptarArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float peso = ((Number) spinnerPeso.getValue()).floatValue();// Ver de manejar esto mejor
                try {//Manejo las exepciones -- Mejorar los dialogMessage y crear Alertas personalizadas.
                    if (peso <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDescripcionArticulo.getText().equals(textoPorDefectoDescArticulo) || textDescripcionArticulo.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDimensiones.getText().equals(textoPorDefectoDimensiArticulos) || textDimensiones.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else {
                        DTArticulo dtArticulo = new DTArticulo(-1, null, textDescripcionArticulo.getText(), peso, textDimensiones.getText());
                        if (altaDonacion.crearDonacion(dtArticulo)) {
                            JOptionPane.showMessageDialog(null, "Ingreso Realizado exitosamente!!!", "¡Listo!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            throw new CamposIncompletosExeption("Ocurrio un problema!");
                        }
                    }
                } catch (CamposIncompletosExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Cancelar Articulo
        buttonCancelarArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        // Aceptar Alimento
        buttonAceptarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object valor = spinnerCantidad.getValue();
                Integer cantidad = (Integer) valor;
                try {//Manejo las exepciones -- Mejorar los dialogMessage y crear Alertas personalizadas.
                    if (cantidad <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDescripcionAlimento.getText().equals(textoPorDefectoDescAlimento) || textDescripcionAlimento.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else {
                        DTAlimento dtAlimento = new DTAlimento(-1, null, textDescripcionAlimento.getText(), cantidad);
                        if (altaDonacion.crearDonacion(dtAlimento)) {
                            JOptionPane.showMessageDialog(null, "Ingreso Realizado exitosamente!!!", "¡Listo!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            throw new CamposIncompletosExeption("Ocurrio un problema!");
                        }
                    }
                } catch (CamposIncompletosExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Cancelar Alimento
        buttonCancelarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    // Infla el panel principal.
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.background = new JPanel();
        setContentPane(background);
        setSize(600, 400);
    }
}
