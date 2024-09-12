package gui;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import excepciones.CamposIncompletosExeption;
import gui.componentes.AlertaGUI;
import gui.componentes.ComponenteSpinner;
import gui.componentes.ComponenteTextField;
import interfaces.IControladorDonacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaDonacionGUI extends JFrame {
    private IControladorDonacion controladorDonacion;
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
    private final String textoPorDefectoDescAlimento = "Ingrese la descripcion...";
    private final String textoPorDefectoDescArticulo = "Ingrese la descripcion...";
    private final String textoPorDefectoDimensiArticulos = "Ingrese las dimensiones...";


    public AltaDonacionGUI(IControladorDonacion controladorDonacion) {
        cardLayout = new CardLayout();
        this.controladorDonacion = controladorDonacion;
        cardAlimentoArticulo.setLayout(cardLayout);
        cardAlimentoArticulo.add(panelAlimento, "alimento");
        cardAlimentoArticulo.add(panelArticulo, "articulo");
        aplicarEstilos();
        actionBotonesNavDonaciones();
        actionBotonesAceptarCancelar();
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
                float peso = ((Number) spinnerPeso.getValue()).floatValue();// Ver de manejar esto mejor
                try {//Manejo las exepciones -- Mejorar los dialogMessage y crear Alertas personalizadas.
                    if (peso <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDescripcionArticulo.getText().equals(textoPorDefectoDescArticulo) || textDescripcionArticulo.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDimensiones.getText().equals(textoPorDefectoDimensiArticulos) || textDimensiones.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else {
                        DTArticulo dtArticulo = new DTArticulo(-1, null, textDescripcionArticulo.getText(), peso, textDimensiones.getText());
                        if (controladorDonacion.crearDonacion(dtArticulo)) {
                            new AlertaGUI(false, "Ingreso Realizado exitosamente!!!").mostrarAlerta();
                        } else {
                            throw new CamposIncompletosExeption("Ocurrio un problema!");
                        }
                    }
                } catch (CamposIncompletosExeption ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
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
                        if (controladorDonacion.crearDonacion(dtAlimento)) {
                            new AlertaGUI(false, "Ingreso Realizado exitosamente!!!").mostrarAlerta();
                        } else {
                            throw new CamposIncompletosExeption("Ocurrio un problema!");
                        }
                    }
                } catch (CamposIncompletosExeption ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
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

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }

    // Infla el panel principal.
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.background = new JPanel();
        setContentPane(background);
        setSize(400, 400);
    }
}
