package gui;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import excepciones.CamposIncompletosExeption;
import gui.componentes.*;
import interfaces.IControladorDonacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ModificarDonacionGUI extends JFrame {
    private IControladorDonacion altaDonacion;
    private JPanel background;
    private JButton buttonModificarArticulo;
    private JButton buttonModificarAlimento;
    private JComboBox comboBuscarArticulo;
    private JTextField textDescripcionArticulo;
    private JSpinner spinnerPeso;
    private JTextField textDimensiones;
    private JButton buttonAceptarArticulo;
    private JButton buttonCancelarArticulo;
    private JPanel cardAlimentoArticulo;
    private JPanel panelAlimento;
    private JPanel panelArticulo;
    private JComboBox comboBuscarAlimento;
    private JButton botonaceptarAlimento;
    private JButton botonCancelarAlimento;
    private JLabel textoArticuloSeleccionado;
    private JLabel textoAlimentoSeleccionado;
    private JSpinner spinnerCantidadAlimento;
    private JTextField textDescripAlimento;
    private JPanel paneltitulo;
    private JPanel panelArtiAli;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel15;
    private CardLayout cardLayout;
    private DTAlimento aEditarAlimento;
    private DTArticulo aEditarArticulo;

    public ModificarDonacionGUI(IControladorDonacion altaDonacion) {
        this.altaDonacion = altaDonacion;
        cardLayout = new CardLayout();
        cardAlimentoArticulo.setLayout(cardLayout);
        // Cargo los paneles opcionales.
        cardAlimentoArticulo.add(panelAlimento, "alimento");
        cardAlimentoArticulo.add(panelArticulo, "articulo");
        aplicarEstilos();
        cambiarTipoDonacion(cardLayout);
        cargarComboBox();
        actionListenerComboBox();
        actionListenerbotonesConfirmacion();

    }

    // Infla el panel principal
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 500);
    }

    // Aplica estilos personalizados que no están en él .form
    private void aplicarEstilos() {

        // Crear un modelo de SpinnerNumberModel con valores flotantes-- Ver de mejorar --
        SpinnerNumberModel modelFloat = new SpinnerNumberModel(0.0f, 0.0f, 100.0f, 0.1f);
        // Configurar el formato del JSpinner para mostrar valores flotantes
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerPeso, "0.00");
        spinnerPeso.setEditor(editor);

        paneltitulo.setBackground(ColorUtil.getColor("primaryColor"));
        background.setBackground(ColorUtil.getColor("backgroundColor"));
        panelArtiAli.setBackground(ColorUtil.getColor("backgroundColor"));
        buttonModificarAlimento.setBackground(ColorUtil.getColor("backgroundColor"));
        buttonModificarArticulo.setBackground(ColorUtil.getColor("backgroundColor"));
        cardAlimentoArticulo.setBackground(ColorUtil.getColor("backgroundColor"));
        panelAlimento.setBackground(ColorUtil.getColor("backgroundColor"));
        panelArticulo.setBackground(ColorUtil.getColor("backgroundColor"));
        panel1.setBackground(ColorUtil.getColor("backgroundColor"));
        panel2.setBackground(ColorUtil.getColor("backgroundColor"));
        panel3.setBackground(ColorUtil.getColor("backgroundColor"));
        panel5.setBackground(ColorUtil.getColor("backgroundColor"));
        panel6.setBackground(ColorUtil.getColor("backgroundColor"));
        panel7.setBackground(ColorUtil.getColor("backgroundColor"));
        panel11.setBackground(ColorUtil.getColor("backgroundColor"));
        panel12.setBackground(ColorUtil.getColor("backgroundColor"));
        panel13.setBackground(ColorUtil.getColor("backgroundColor"));
        panel14.setBackground(ColorUtil.getColor("backgroundColor"));
        panel15.setBackground(ColorUtil.getColor("backgroundColor"));

        //Los componentes creados en el .Form los edito con estas clases.
        new ComponenteTextField(textDescripAlimento, "");
        new ComponenteTextField(textDescripcionArticulo, "");
        new ComponenteTextField(textDimensiones, "");
        new ComponenteSpinner(spinnerCantidadAlimento);
        new ComponenteSpinner(spinnerPeso);
        new ComponenteComboBox(comboBuscarAlimento);
        new ComponenteComboBox(comboBuscarArticulo);
    }

    // ActionListener de los botones que cambian entre modificar Alimento y Articulo
    private void cambiarTipoDonacion(CardLayout cardLayout) {
        // Muestra el cardLayaut correspondiente a la edicion del Alimento
        buttonModificarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "alimento");
            }
        });

        // Muestra el cardLayaut correspondiente a la edicion del Alimento
        buttonModificarArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardAlimentoArticulo, "articulo");
            }
        });
    }

    // Cargar comboBoxs
    private void cargarComboBox() {

        // Limpiar el contenido existente de los JComboBox
        comboBuscarAlimento.removeAllItems();
        comboBuscarArticulo.removeAllItems();

        java.util.List<DTAlimento> dtAlimentoList = altaDonacion.listarAlimentos();
        List<DTArticulo> dtArticulosList = altaDonacion.listarArticulos();

        // Carga los combobox con todas las donaciones de tipo alimento que hay en el manejadordonaciones
        for (DTAlimento dtAlimento : dtAlimentoList) {
            comboBuscarAlimento.addItem(dtAlimento);
        }
        // Carga los combobox con todas las donaciones de tipo articulo que hay en el manejadordonaciones
        for (DTArticulo dtArticulo : dtArticulosList) {
            comboBuscarArticulo.addItem(dtArticulo);
        }
    }

    // Maneja las interacciones de los ComboBox
    private void actionListenerComboBox() {

        // Muestra los datos que tiene el item seleccionado
        comboBuscarArticulo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DTArticulo seleccionado = (DTArticulo) e.getItem();
                    aEditarArticulo = seleccionado;
                    textoArticuloSeleccionado.setText(seleccionado.getDescripcion());
                    textDescripcionArticulo.setText(seleccionado.getDescripcion());
                    spinnerPeso.setValue(seleccionado.getPeso());
                    textDimensiones.setText(seleccionado.getDimensiones());
                }
            }
        });

        // Muestra los datos que tiene el item seleccionado
        comboBuscarAlimento.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DTAlimento seleccionado = (DTAlimento) e.getItem();
                    aEditarAlimento = seleccionado;
                    textoAlimentoSeleccionado.setText(seleccionado.getDescripcionProductos());
                    textDescripAlimento.setText(seleccionado.getDescripcionProductos());
                    spinnerCantidadAlimento.setValue(seleccionado.getCantElementos());
                }
            }
        });
    }

    // Acciones de los botones de Aceptar Y Cancelar
    private void actionListenerbotonesConfirmacion() {

        // Edito la donacion de tipo Alimento al aceptar
        botonaceptarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {//Manejo las exepciones -- Mejorar los dialogMessage y crear Alertas personalizadas.
                    Object valor = spinnerCantidadAlimento.getValue();
                    Integer cantidad = (Integer) valor;
                    if (cantidad == null || cantidad <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDescripAlimento.getText().equals("") || textDescripAlimento.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (aEditarAlimento == null) {
                        throw new CamposIncompletosExeption("No se a seleccionado un item a modificar!");
                    } else if (cantidad == aEditarAlimento.getCantElementos() && textDescripAlimento.getText().equals(aEditarAlimento.getDescripcionProductos())) {
                        throw new CamposIncompletosExeption("No se ha modificado ningun campo!");
                    } else {
                        DTAlimento dtAlimento = new DTAlimento(-1, null, textDescripAlimento.getText(), cantidad);
                        altaDonacion.editarDonacion(dtAlimento, aEditarAlimento.getId());
                        new AlertaGUI(false, "Modificacion Realizada exitosamente!!!");
                        cargarComboBox();
                    }
                } catch (CamposIncompletosExeption ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });

        // Edito la donacion de tipo Artículo al aceptar
        buttonAceptarArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float peso = ((Number) spinnerPeso.getValue()).floatValue();//¡¡¡Ver si se puede mejorar esta línea!!!
                try {//Manejo las exepciones -- Mejorar los dialogMessage y crear Alertas personalizadas.
                    if (peso <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDescripAlimento.getText().equals("") || textDescripAlimento.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (textDimensiones.getText().equals("") || textDimensiones.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Por favor, complete todos los campos!");
                    } else if (aEditarArticulo == null) {
                        throw new CamposIncompletosExeption("No se a seleccionado un item a modificar!");
                    } else if (textDescripAlimento.getText().equals(aEditarArticulo.getDescripcion()) && peso.equals(aEditarArticulo.getPeso()) &&
                            textDimensiones.getText().equals(aEditarArticulo.getDimensiones())) {
                        throw new CamposIncompletosExeption("No se ha modificado ningun campo!");
                    } else {
                        DTArticulo dtArticulo = new DTArticulo(-1, null, textDescripcionArticulo.getText(), peso, textDimensiones.getText());
                        altaDonacion.editarDonacion(dtArticulo, aEditarArticulo.getId());
                        new AlertaGUI(false, "Modificacion Realizada exitosamente!!!");
                        cargarComboBox();
                    }
                } catch (CamposIncompletosExeption ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });

        // Cierra las ventanas -Ver si es necesario limpiar campos
        botonCancelarAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonCancelarArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }
}
