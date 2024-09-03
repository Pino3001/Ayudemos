package gui;

import datatypes.*;
import excepciones.CamposIncompletosExeption;
import gui.componentes.AlertaGUI;
import gui.componentes.ComponenteCalFechaHora;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.Fabrica;
import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ModificarDistribucionGUI extends JFrame {
    private final IAltaDonacion altaDonacion;
    private final IAltaUsuario altaUsuario;
    private final IAltaDistribucion altaDistribucion;
    private JPanel background;
    private JComboBox<DtDistribucion> comboDistribuciones;
    private JTextField textFechaPrepara;
    private JButton buttonCalendarioPrepara;
    private JLabel textSeleccionadoDistribu;
    private JButton buttonAceptarDonacion;
    private JButton buttonCancelarDonacion;
    private JComboBox<DtUsuario> comboBeneficiario;
    private JComboBox<DTDonacion> comboDonacion;
    private JTextField textFechaEntrega;
    private JComboBox<EstadoDistribucion> comboEstado;
    private JButton buttonFechaEntrega;

    private DtDistribucion distrSeleccionada;
    private final String textFechaEntregaDefecto = "";
    private final String textFechaPreparaDefecto = "";

    public ModificarDistribucionGUI(IAltaUsuario altaUsuario, IAltaDonacion altaDonacion, IAltaDistribucion altaDistribucion) {
        this.altaDonacion = altaDonacion;
        this.altaUsuario = altaUsuario;
        this.altaDistribucion = altaDistribucion;
        aplicarEstilos();
        cargarComboBoxDistribuciones();
        actionListenerComboBox();
        actionButtonCalendario();
    }

    // Aplica estilos predeterminados
    public void aplicarEstilos() {
        new ComponenteTextField(textFechaPrepara, textFechaPreparaDefecto);
        new ComponenteTextField(textFechaEntrega, textFechaEntregaDefecto);
        new ComponenteComboBox(comboDistribuciones);
        new ComponenteComboBox(comboBeneficiario);
        new ComponenteComboBox(comboDonacion);
        new ComponenteComboBox(comboEstado);
    }

    // Infla en componente creado en el form
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 550);
    }

    // Carga el combobox de las distribuciones
    private void cargarComboBoxDistribuciones() {
        List<DtDistribucion> distributions = altaDistribucion.obtenerListaDistribuciones();
        if (distributions != null) {
            comboDistribuciones.setSelectedItem(distributions.get(0));
            actualizarCampos(distributions.get(0));
            for (DtDistribucion distribucion : distributions) {
                comboDistribuciones.addItem(distribucion);
            }
        }

    }

    // Carga los combo box al seleccionar una distribucion
    private void cargarComboAlSeleccionar(DTDonacion dtDonacion, DtUsuario dtUsuario, EstadoDistribucion estadoD) {
        List<DTDonacion> donaciones = altaDonacion.listarDonaciones();
        List<DtUsuario> usuarios = altaUsuario.listarUsuarios();

        for (DTDonacion dtDonacion1 : donaciones) {
            if (dtDonacion.equals(dtDonacion1)) {
                comboDonacion.addItem(dtDonacion1);
                comboDonacion.setSelectedItem(dtDonacion1);
            } else {
                comboDonacion.addItem(dtDonacion1);
            }
        }

        for (DtUsuario dtUsuario1 : usuarios) {
            if (dtUsuario.equals(dtUsuario1)) {
                comboBeneficiario.addItem(dtUsuario1);
                comboBeneficiario.setSelectedItem(dtUsuario1);
            } else {
                comboBeneficiario.addItem(dtUsuario1);
            }
        }

        for (EstadoDistribucion estadoDistribucion : EstadoDistribucion.values()) {
            if (estadoDistribucion.equals(estadoD)) {
                comboEstado.addItem(estadoDistribucion);
                comboEstado.setSelectedItem(estadoDistribucion);
            } else {
                comboEstado.addItem(estadoDistribucion);
            }
        }
    }

    // Maneja las interacciones de los ComboBox
    private void actionListenerComboBox() {
        // Muestra los datos que tiene el item seleccionado
        comboDistribuciones.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DtDistribucion seleccionado = (DtDistribucion) e.getItem();
                    distrSeleccionada = seleccionado;
                    actualizarCampos(seleccionado);
                }
            }
        });
    }

    // Actualiza los campos del panel
    private void actualizarCampos(DtDistribucion distribu) {
        DtUsuario dtben = altaUsuario.obtenerUsuarioPorId(distribu.getIdBeneficiario());
        DTDonacion dtDonacion = altaDonacion.obtenerDonacionPorId(distribu.getIdDonacion());
        cargarComboAlSeleccionar(dtDonacion, dtben, distribu.getEstado());

        // Crear un formateador para el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Formatear la fecha en el formato deseado
        String fechaPrep = distribu.getFechaPreparacion().format(formatter);
        String fechaEntrega = distribu.getFechaEntrega().format(formatter);
        textFechaPrepara.setText(fechaPrep);
        textFechaEntrega.setText(fechaEntrega);
    }

    // Comportamiento de los botones del calendario
    private void actionButtonCalendario() {
        buttonCalendarioPrepara.addActionListener(e -> {
            if (textFechaPrepara == null) {
                System.out.println("textFechaPrepara es null");
                return;
            }
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
            int x = textFechaPrepara.getLocationOnScreen().x;
            int y = textFechaPrepara.getLocationOnScreen().y + buttonCalendarioPrepara.getHeight();

            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora(textFechaPrepara.getText());
            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaHora(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaPrepara.setText(fechaSeleccionada);
            }
        });

        buttonFechaEntrega.addActionListener(e -> {
            if (textFechaEntrega == null) {
                System.out.println("textFechaPrepara es null");
                return;
            }

            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textfield
            int x = textFechaEntrega.getLocationOnScreen().x;
            int y = textFechaEntrega.getLocationOnScreen().y + buttonFechaEntrega.getHeight();

            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora(textFechaEntrega.getText());
            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaHora(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaEntrega.setText(fechaSeleccionada);
            }
        });
    }

    // Comportamiento de los botones aceptar y cancelar
    private void actionAceptarCancelar() {
        buttonAceptarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboBeneficiario.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboDonacion.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboEstado.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textFechaPrepara.getText().equals(textFechaPreparaDefecto) && textFechaPrepara.getText().isEmpty()) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textFechaEntrega.getText().equals(textFechaEntregaDefecto) && textFechaEntrega.getText().isEmpty()) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else {
                        // Define el formato de la cadena
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
                        // Parsear la cadena a un objeto LocalDateTime
                        System.out.println("En fecha prep hay" + textFechaPrepara.getText());
                        System.out.println("En fecha entre hay" + textFechaEntrega.getText());

                        LocalDateTime fechaHoraPrep = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
                        LocalDateTime fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);
////Colocar la funcion de modificar Distribucion
                        new AlertaGUI(false, "Se ha creado La Distribucion Exitosamente").mostrarAlerta();
                    }
                } catch (CamposIncompletosExeption | DateTimeParseException ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });
        buttonCancelarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IAltaUsuario usuarios = fabrica.getAltaUsuario();
        IAltaDonacion donacion = fabrica.getAltaDonacion();
        IAltaDistribucion distribucion = fabrica.getIAltaDistribucion();
        JFrame frame = new JFrame("ModificarDistribucionGUI");
        frame.setContentPane(new ModificarDistribucionGUI(usuarios, donacion, distribucion).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
