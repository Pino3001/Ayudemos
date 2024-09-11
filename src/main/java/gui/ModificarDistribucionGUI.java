package gui;

import com.toedter.calendar.JDateChooser;
import datatypes.*;
import gui.componentes.ColorUtil;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.IControladorDistribucion;
import interfaces.IControladorDonacion;
import interfaces.IControladorUsuario;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ModificarDistribucionGUI extends JFrame {

    private IControladorDistribucion controladorDistribucion;
    private IControladorUsuario controladorUsuario;
    private IControladorDonacion controladorDonacion;

    // Componentes generados desde el archivo .form
    private JPanel background;
    private JComboBox<DtDistribucion> comboDistribuciones;
    private JButton buttonCalendarioEntrega;
    private JLabel labelBeneficiario;
    private JLabel labelDonacion;
    private JLabel labelFechaPrepara;
    private JTextField textFechaEntrega;
    private JComboBox<EstadoDistribucion> comboEstado;
    private JButton buttonAceptarDistribucion;
    private JButton buttonCancelarDistribucion;
    private JLabel textSeleccionadoDistribucion;

    // Formato de fecha
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private DtDistribucion distribucionOriginal;  // Para guardar la distribución original

    public ModificarDistribucionGUI(IControladorDistribucion controladorDistribucion, IControladorUsuario controladorUsuario, IControladorDonacion controladorDonacion) {
        this.controladorDistribucion = controladorDistribucion;
        this.controladorUsuario = controladorUsuario;
        this.controladorDonacion = controladorDonacion;

        // Vincula el formulario al contenido de la ventana
        setContentPane(background);
        setTitle("Modificar Distribución");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        // Cargar los datos (incluyendo estado y fechas) cuando se seleccione una distribución del comboDistribuciones
        comboDistribuciones.addActionListener(e -> {
            DtDistribucion distribucionSeleccionada = (DtDistribucion) comboDistribuciones.getSelectedItem();
            if (distribucionSeleccionada != null) {
                distribucionOriginal = distribucionSeleccionada; // Guardar la distribución original para referencia
                cargarFechasDistribucion(distribucionSeleccionada.getFechaPreparacion(), distribucionSeleccionada.getFechaEntrega());
                comboEstado.setSelectedItem(distribucionSeleccionada.getEstado()); // Cargar el estado actual
                DtUsuario dtUsuario = controladorUsuario.obtenerUsuarioPorId(distribucionSeleccionada.getIdUsuario());// Busco el usuario para mostrar su nombre
                labelBeneficiario.setText(dtUsuario.getMail()); // Mostrar beneficiario
                DTDonacion dtDonacion = controladorDonacion.buscarDonacionID(distribucionSeleccionada.getIdDonacion());
                if (dtDonacion instanceof DTAlimento) labelDonacion.setText(((DTAlimento) dtDonacion).getCantElementos() + "  " + ((DTAlimento) dtDonacion).getDescripcionProductos());
                else if (dtDonacion instanceof  DTArticulo) labelDonacion.setText(((DTArticulo) dtDonacion).getDescripcion());
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
        aplicarEstilos();
        cargarComboBox();
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
        labelFechaPrepara.setText(fechaPreparacion.format(formatter));
        textFechaEntrega.setText(fechaEntrega.format(formatter));
    }

    private void cargarComboBox(){
        comboDistribuciones.removeAllItems();
        comboDistribuciones.removeAllItems();
        // Población de combo boxes con datos
        for (DtDistribucion distribucion : controladorDistribucion.obtenerDistribuciones()) {
            comboDistribuciones.addItem(distribucion);
        }
        comboDistribuciones.setSelectedItem(controladorDistribucion.obtenerDistribuciones().get(0));
        // Poblar comboEstado con los valores del enum EstadoDistribucion
        for (EstadoDistribucion estado : EstadoDistribucion.values()) {
            comboEstado.addItem(estado);
        }
    }

    private void aplicarEstilos(){
        new ComponenteComboBox(comboEstado);
        new ComponenteComboBox(comboDistribuciones);
        new ComponenteTextField(textFechaEntrega,"");
        labelBeneficiario.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelBeneficiario.setForeground(ColorUtil.getColor("primaryColor"));
        labelDonacion.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelDonacion.setForeground(ColorUtil.getColor("primaryColor"));
        labelFechaPrepara.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelFechaPrepara.setForeground(ColorUtil.getColor("primaryColor"));

    }
}
