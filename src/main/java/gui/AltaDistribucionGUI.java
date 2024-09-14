package gui;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import excepciones.CamposIncompletosExeption;
import gui.componentes.AlertaGUI;
import gui.componentes.ComponenteCalFechaHora;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.*;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AltaDistribucionGUI extends JFrame {
    private final IControladorDonacion altaDonacion;
    private final IControladorUsuario controladorUsuario;
    private final IControladorDistribucion iControladorDistribucion;
    private JPanel background;
    private JTextField textFechaPrep;
    private JButton buttonAceptar;
    private JButton buttonCancelar;
    private JButton buttonCalendarioEntrega;
    private JComboBox<DTDonacion> comboDonacion;
    private JComboBox<EstadoDistribucion> comboEstado;
    private JComboBox<DtBeneficiario> comboBeneficiario;
    private JTextField textFechaEntrega;
    private JButton buttonCalendarioPrepara;
    private JTextField textFechaPrepara;
    private String predFecha = "--/--/---- hh:mm";


    public AltaDistribucionGUI(IControladorUsuario altaUsuario, IControladorDonacion altaDonacion, IControladorDistribucion iControladorDistribucion) {
        this.altaDonacion = altaDonacion;
        this.controladorUsuario = altaUsuario;
        this.iControladorDistribucion = iControladorDistribucion;
        aplicarEstilosComponentes();
        cargarComboBox();
        actionButtonCalendario();
        actionAceptarCancelar();
    }

    // Infla en componente creado en el form
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 500);
    }

    //Aplica Estilos a los componentes
    private void aplicarEstilosComponentes() {
        new ComponenteComboBox(comboDonacion);
        new ComponenteComboBox(comboEstado);
        new ComponenteComboBox(comboBeneficiario);
        new ComponenteTextField(textFechaEntrega, predFecha);
        new ComponenteTextField(textFechaPrepara, predFecha);
    }

    // Cargar comboBox
    private void cargarComboBox() {
        java.util.List<DtBeneficiario> dtBeneficiarioList = controladorUsuario.listarBeneficiarios();
        List<DTDonacion> dtDonacionList = altaDonacion.listarDonaciones();

        // Cargo con los beneficiarios
        for (DtBeneficiario dtBeneficiario : dtBeneficiarioList) {
            comboBeneficiario.addItem(dtBeneficiario);
        }

        // Cargo con las donaciones
        for (DTDonacion dtDonacion : dtDonacionList) {
            comboDonacion.addItem(dtDonacion);
        }

        // Cargo con los posibles estados
        comboEstado.setSelectedItem(EstadoDistribucion.PENDIENTE);
        for (EstadoDistribucion estadoDistribucion : EstadoDistribucion.values()) {
            comboEstado.addItem(estadoDistribucion);
        }
    }

    // Comportamiento Boton Aceptar-Cancelar
    private void actionAceptarCancelar() {
        buttonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboBeneficiario.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboDonacion.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboEstado.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textFechaPrepara.getText().equals(predFecha) || textFechaPrepara.getText().isEmpty()) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else {
                        LocalDateTime fechaEntrega;
                        LocalDateTime fechaHoraPrep;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
                        if (textFechaEntrega.getText().equals(predFecha) || textFechaEntrega.getText().isEmpty()) {
                            // Parsear la cadena a un objeto LocalDateTime
                            fechaHoraPrep = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
                            fechaEntrega = null;
                        } else {
                            // Parsear la cadena a un objeto LocalDateTime
                            fechaHoraPrep = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
                            fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
                            if (!fechaEntrega.isAfter(fechaHoraPrep)) {
                                throw new CamposIncompletosExeption("La fecha de entrega no puede ser anterior a la de preparacion!");
                            }
                        }
                        iControladorDistribucion.crearDistribucion((DtBeneficiario) comboBeneficiario.getSelectedItem(), (DTDonacion) comboDonacion.getSelectedItem(), fechaHoraPrep, fechaEntrega, (EstadoDistribucion) comboEstado.getSelectedItem());
                        new AlertaGUI(false, "Se ha creado La Distribucion Exitosamente").mostrarAlerta();
                        limpiarCampos();
                    }
                } catch (CamposIncompletosExeption | DateTimeParseException ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });
        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    // Comportamiento de los botones del calendario
    private void actionButtonCalendario() {

        buttonCalendarioPrepara.addActionListener(e -> {
            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
            int x = textFechaPrepara.getLocationOnScreen().x - 130;
            int y = textFechaPrepara.getLocationOnScreen().y + buttonCalendarioPrepara.getHeight();

            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaHora(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaPrepara.setText(fechaSeleccionada);
            }
        });

        buttonCalendarioEntrega.addActionListener(e -> {
            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textfield
            int x = textFechaEntrega.getLocationOnScreen().x - 130;
            int y = textFechaEntrega.getLocationOnScreen().y + buttonCalendarioEntrega.getHeight();

            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaHora(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaEntrega.setText(fechaSeleccionada);
            }
        });
    }

    private  void limpiarCampos(){
        new ComponenteComboBox(comboDonacion);
        new ComponenteComboBox(comboEstado);
        new ComponenteComboBox(comboBeneficiario);
        textFechaEntrega.setText(predFecha);
        textFechaPrepara.setText(predFecha);
        comboDonacion.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboBeneficiario.setSelectedIndex(0);
    }

    public void setPosicion(int x, int y) {
        this.setLocation(x, y);
    }

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IControladorUsuario usuarios = fabrica.getIControladorUsuario();
        IControladorDonacion donacion = fabrica.getAltaDonacion();
        IControladorDistribucion distribucion = fabrica.getIControladorDistribucion();
        JFrame frame = new JFrame("AltaDistribucionGUI");
        frame.setContentPane(new AltaDistribucionGUI(usuarios, donacion, distribucion).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
