package gui;

import datatypes.DtBeneficiario;
import datatypes.DTDonacion;
import types.EstadoDistribucion;
import datatypes.DtDistribucion;
import interfaces.IModificarDistribucion;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModificarDistribucionGUI extends JFrame {

    private IModificarDistribucion modificarDistribucionInterface;

    // Componentes generados desde el archivo .form
    private JPanel background;
    private JComboBox<String> comboDistribuciones;
    private JLabel textSeleccionadoDistribu;
    private JButton buttonCalendarioPrepara;
    private JButton buttonCalendarioEntrega;
    private JComboBox<DtBeneficiario> comboBeneficiario;
    private JComboBox<DTDonacion> comboDonacion;
    private JTextField textFechaPrepara;
    private JTextField textFechaEntrega;
    private JComboBox<EstadoDistribucion> comboEstado;
    private JButton buttonAceptarDistribucion;
    private JButton buttonCancelarDistribucion;
    private JLabel textSeleccionadoDistribucion;

    public ModificarDistribucionGUI(IModificarDistribucion modificarDistribucionInterface) {
        this.modificarDistribucionInterface = modificarDistribucionInterface;

        // Vincula el formulario al contenido de la ventana
        setContentPane(background);
        setTitle("Modificar Distribución");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Población de combo boxes con datos
        for (DtBeneficiario beneficiario : modificarDistribucionInterface.obtenerBeneficiarios()) {
            comboBeneficiario.addItem(beneficiario);
        }
        for (DTDonacion donacion : modificarDistribucionInterface.obtenerDonaciones()) {
            comboDonacion.addItem(donacion);
        }

        // Acción del botón "Aceptar"
        buttonAceptarDistribucion.addActionListener(e -> {
            modificarDistribucionInterface.modificarDistribucion(getDistribucionFromFields());
            dispose();
        });

        // Acción del botón "Cancelar"
        buttonCancelarDistribucion.addActionListener(e -> dispose());

        // Acción del botón para el calendario (ejemplo para el botón de la fecha de preparación)
        buttonCalendarioPrepara.addActionListener(e -> {
            // Implementa tu lógica para abrir un calendario o seleccionar una fecha
        });

        buttonCalendarioEntrega.addActionListener(e -> {
            // Implementa tu lógica para abrir un calendario o seleccionar una fecha
        });
    }

    private DtDistribucion getDistribucionFromFields() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaPreparacion = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
        LocalDateTime fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
        EstadoDistribucion estado = (EstadoDistribucion) comboEstado.getSelectedItem();
        DtBeneficiario beneficiario = (DtBeneficiario) comboBeneficiario.getSelectedItem();
        DTDonacion donacion = (DTDonacion) comboDonacion.getSelectedItem();
        return new DtDistribucion(fechaPreparacion, fechaEntrega, estado, donacion.getId(), beneficiario.getNombre(), beneficiario.getMail());
    }
}
