package gui;

import com.toedter.calendar.JDateChooser;
import datatypes.DtBeneficiario;
import datatypes.DTDonacion;
import types.EstadoDistribucion;
import datatypes.DtDistribucion;
import interfaces.IModificarDistribucion;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ModificarDistribucionGUI extends JFrame {

    private IModificarDistribucion modificarDistribucionInterface;

    // Componentes generados desde el archivo .form
    private JPanel background;
    private JComboBox<DtDistribucion> comboDistribuciones;
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

    // Formato de fecha
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private DtDistribucion distribucionOriginal;  // Para guardar la distribución original

    public ModificarDistribucionGUI(IModificarDistribucion modificarDistribucionInterface) {
        this.modificarDistribucionInterface = modificarDistribucionInterface;

        // Vincula el formulario al contenido de la ventana
        setContentPane(background);
        setTitle("Modificar Distribución");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Población de combo boxes con datos
        for (DtBeneficiario beneficiario : modificarDistribucionInterface.obtenerBeneficiarios()) {
            comboBeneficiario.addItem(beneficiario);
        }
        for (DTDonacion donacion : modificarDistribucionInterface.obtenerDonaciones()) {
            comboDonacion.addItem(donacion);
        }
        for (DtDistribucion distribucion : modificarDistribucionInterface.obtenerDistribuciones()) {
            comboDistribuciones.addItem(distribucion);
        }

        // Poblar comboEstado con los valores del enum EstadoDistribucion
        for (EstadoDistribucion estado : EstadoDistribucion.values()) {
            comboEstado.addItem(estado);
        }

        // Cargar los datos (incluyendo estado) cuando se seleccione una distribución del comboDistribuciones
        comboDistribuciones.addActionListener(e -> {
            DtDistribucion distribucionSeleccionada = (DtDistribucion) comboDistribuciones.getSelectedItem();
            if (distribucionSeleccionada != null) {
                distribucionOriginal = distribucionSeleccionada; // Guardar la distribución original para referencia
                cargarFechasDistribucion(distribucionSeleccionada.getFechaPreparacion(), distribucionSeleccionada.getFechaEntrega());
                comboEstado.setSelectedItem(distribucionSeleccionada.getEstado()); // Cargar el estado actual
            }
        });

        // Acción del botón "Aceptar"
        buttonAceptarDistribucion.addActionListener(e -> {
            DtDistribucion nuevaDistribucion = getDistribucionFromFields();
            // Aquí comparas con los valores originales para ver si han cambiado
            if (!nuevaDistribucion.equals(distribucionOriginal)) {
                // Llamar a la interfaz para guardar los cambios
                modificarDistribucionInterface.modificarDistribucion(nuevaDistribucion);
            }
            dispose();  // Cerrar la ventana después de aceptar los cambios
        });

        // Acción del botón "Cancelar"
        buttonCancelarDistribucion.addActionListener(e -> {
            dispose();  // Simplemente cerrar la ventana sin guardar nada
        });

        // Acción del botón para el calendario para seleccionar la fecha de preparación
        buttonCalendarioPrepara.addActionListener(e -> {
            JDateChooser dateChooser = new JDateChooser();
            int result = JOptionPane.showConfirmDialog(null, dateChooser, "Seleccione la Fecha de Preparación", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    LocalDateTime ldt = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
                    textFechaPrepara.setText(ldt.format(formatter));
                }
            }
        });

        // Acción del botón para el calendario para seleccionar la fecha de entrega
        buttonCalendarioEntrega.addActionListener(e -> {
            JDateChooser dateChooser = new JDateChooser();
            int result = JOptionPane.showConfirmDialog(null, dateChooser, "Seleccione la Fecha de Entrega", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    LocalDateTime ldt = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
                    textFechaEntrega.setText(ldt.format(formatter));
                }
            }
        });
    }

    private DtDistribucion getDistribucionFromFields() {
        // Convertir las fechas de los campos de texto
        LocalDateTime fechaPreparacion = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
        LocalDateTime fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
        EstadoDistribucion estado = (EstadoDistribucion) comboEstado.getSelectedItem();
        DtBeneficiario beneficiario = (DtBeneficiario) comboBeneficiario.getSelectedItem();
        DTDonacion donacion = (DTDonacion) comboDonacion.getSelectedItem();
        return new DtDistribucion(fechaPreparacion, fechaEntrega, estado, donacion.getId(), beneficiario.getNombre(), beneficiario.getMail());
    }

    public void cargarFechasDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega) {
        // Método para cargar fechas existentes en los JTextFields
        textFechaPrepara.setText(fechaPreparacion.format(formatter));
        textFechaEntrega.setText(fechaEntrega.format(formatter));
    }
}
