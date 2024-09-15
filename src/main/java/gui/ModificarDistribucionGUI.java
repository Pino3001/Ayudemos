package gui;

import datatypes.*;
import excepciones.CamposIncompletosExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import gui.componentes.*;
import interfaces.IControladorDistribucion;
import interfaces.IControladorDonacion;
import interfaces.IControladorUsuario;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModificarDistribucionGUI extends JFrame {

    private IControladorDistribucion controladorDistribucion;

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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private DtDistribucion distribucionOriginal;  // Para guardar la distribución original

    public ModificarDistribucionGUI(IControladorDistribucion controladorDistribucion, IControladorUsuario controladorUsuario, IControladorDonacion controladorDonacion) {
        this.controladorDistribucion = controladorDistribucion;

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
                if (dtDonacion instanceof DTAlimento)
                    labelDonacion.setText(((DTAlimento) dtDonacion).getCantElementos() + "  " + ((DTAlimento) dtDonacion).getDescripcionProductos());
                else if (dtDonacion instanceof DTArticulo)
                    labelDonacion.setText(((DTArticulo) dtDonacion).getDescripcion());
            }
        });

        // Acción del botón "Aceptar"
        buttonAceptarDistribucion.addActionListener(e -> {
            DtDistribucion nuevaDistribucion = getDistribucionFromFields();
            try {
                if (nuevaDistribucion == null) {
                    throw new IngresoIncorrectoExeption("Ocurrio un problema al modificar!");
                } else if (nuevaDistribucion.equals(distribucionOriginal)) {
                    throw new CamposIncompletosExeption("No se ha modificado ningun campo!");
                } else if (comboEstado.getSelectedItem() == null) {
                    throw new CamposIncompletosExeption("No se ha seleccionado ningun estado\n para la Distribucion!");
                } else {
                    controladorDistribucion.modificarDistribucion(nuevaDistribucion);
                    new AlertaGUI(false, "Distribución modificada correctamente!").mostrarAlerta();
                }
            } catch (CamposIncompletosExeption | IngresoIncorrectoExeption ex) {
                new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
            }
        });

        // Acción del botón "Cancelar"
        buttonCancelarDistribucion.addActionListener(e -> dispose());

        buttonCalendarioEntrega.addActionListener(e -> {
            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora(textFechaEntrega.getText());
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textfield
            int x = textFechaEntrega.getLocationOnScreen().x - 50;
            int y = textFechaEntrega.getLocationOnScreen().y;

            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaHora(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaEntrega.setText(fechaSeleccionada);
            }
        });
        aplicarEstilos();
        cargarComboBox();
    }

    private DtDistribucion getDistribucionFromFields() {
        try {
            // Convertir las fechas de los campos de texto
            LocalDateTime fechaEntrega;
            if (textFechaEntrega.getText() == null || textFechaEntrega.getText().isEmpty()) {
                fechaEntrega = null;
            } else {
                fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
                if (!fechaEntrega.isAfter(distribucionOriginal.getFechaPreparacion())) {
                    throw new FormatoFechaIExeption("La fecha de entrega no puede ser anterior\n a la de la preparacion!");
                }
            }
            EstadoDistribucion estado = (EstadoDistribucion) comboEstado.getSelectedItem();
            return new DtDistribucion(distribucionOriginal.getFechaPreparacion(), fechaEntrega, estado, distribucionOriginal.getIdDonacion(), distribucionOriginal.getIdUsuario());

        } catch (FormatoFechaIExeption ex) {
            new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
            return null;
        }
    }

    public void cargarFechasDistribucion(LocalDateTime fechaPreparacion, LocalDateTime fechaEntrega) {
        // Método para cargar fechas existentes en los JTextFields
        if (fechaEntrega == null) {
            labelFechaPrepara.setText(fechaPreparacion.format(formatter));
            textFechaEntrega.setText("");
        } else {
            labelFechaPrepara.setText(fechaPreparacion.format(formatter));
            textFechaEntrega.setText(fechaEntrega.format(formatter));
        }
    }

    private void cargarComboBox() {
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

    private void aplicarEstilos() {
        new ComponenteComboBox(comboEstado);
        new ComponenteComboBox(comboDistribuciones);
        new ComponenteTextField(textFechaEntrega, "");
        labelBeneficiario.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelBeneficiario.setForeground(ColorUtil.getColor("primaryColor"));
        labelDonacion.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelDonacion.setForeground(ColorUtil.getColor("primaryColor"));
        labelFechaPrepara.setFont(new Font("Roboto light", Font.PLAIN, 16));
        labelFechaPrepara.setForeground(ColorUtil.getColor("primaryColor"));

    }

    public void setPosicion(int x, int y) {
        this.setLocation(x, y);
    }
}
