package gui;

import com.toedter.calendar.JDateChooser;
import datatypes.DtBeneficiario;
import datatypes.DTDonacion;
import interfaces.IControladorDistribucion;
import types.EstadoDistribucion;
import datatypes.DtDistribucion;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ModificarDistribucionGUI extends JFrame {

    private IControladorDistribucion controladorDistribucion;

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

    public ModificarDistribucionGUI(IControladorDistribucion controladorDistribucion) {
        this.controladorDistribucion = controladorDistribucion;

        // Vincula el formulario al contenido de la ventana
        setContentPane(background);
        setTitle("Modificar Distribución");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Deshabilitar campos no editables
        comboBeneficiario.setEnabled(false);
        comboDonacion.setEnabled(false);
        textFechaPrepara.setEnabled(false);

        // Población de combo boxes con datos
        for (DtBeneficiario beneficiario : controladorDistribucion.obtenerBeneficiarios()) {
            comboBeneficiario.addItem(beneficiario);
        }
        for (DTDonacion donacion : controladorDistribucion.obtenerDonaciones()) {
            comboDonacion.addItem(donacion);
        }
        for (DtDistribucion distribucion : controladorDistribucion.obtenerDistribuciones()) {
            comboDistribuciones.addItem(distribucion);
        }

        // Poblar comboEstado con los valores del enum EstadoDistribucion
        for (EstadoDistribucion estado : EstadoDistribucion.values()) {
            comboEstado.addItem(estado);
        }

        // Cargar los datos (incluyendo estado y fechas) cuando se seleccione una distribución del comboDistribuciones
        comboDistribuciones.addActionListener(e -> {
            DtDistribucion distribucionSeleccionada = (DtDistribucion) comboDistribuciones.getSelectedItem();
            if (distribucionSeleccionada != null) {
                distribucionOriginal = distribucionSeleccionada; // Guardar la distribución original para referencia
                cargarFechasDistribucion(distribucionSeleccionada.getFechaPreparacion(), distribucionSeleccionada.getFechaEntrega());
                comboEstado.setSelectedItem(distribucionSeleccionada.getEstado()); // Cargar el estado actual
                comboBeneficiario.setSelectedItem(distribucionSeleccionada.getIdUsuario()); // Mostrar beneficiario
                comboDonacion.setSelectedItem(distribucionSeleccionada.getIdDonacion()); // Mostrar donación
            }
        });

        // Acción del botón "Aceptar"
        buttonAceptarDistribucion.addActionListener(e -> {
            DtDistribucion nuevaDistribucion = getDistribucionFromFields();
            if (!nuevaDistribucion.equals(distribucionOriginal)) {
                controladorDistribucion.modificarDistribucion(nuevaDistribucion);
                JOptionPane.showMessageDialog(null, "Distribución modificada correctamente.");
            }
            dispose();  // Cerrar la ventana después de aceptar los cambios
        });

        // Acción del botón "Cancelar"
        buttonCancelarDistribucion.addActionListener(e -> dispose());

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
        LocalDateTime fechaPreparacion = distribucionOriginal.getFechaPreparacion();  // Mantener la fecha de preparación original
        LocalDateTime fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
        EstadoDistribucion estado = (EstadoDistribucion) comboEstado.getSelectedItem();
        return new DtDistribucion(fechaPreparacion, fechaEntrega, estado, distribucionOriginal.getIdDonacion(), distribucionOriginal.getIdUsuario());
    }

    public void cargarFechasDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega) {
        // Método para cargar fechas existentes en los JTextFields
        textFechaPrepara.setText(fechaPreparacion.format(formatter));
        textFechaEntrega.setText(fechaEntrega.format(formatter));
    }
}
