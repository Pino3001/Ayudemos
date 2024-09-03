package gui;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import excepciones.CamposIncompletosExeption;
import gui.componentes.*;
import interfaces.Fabrica;
import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AltaDistribucionGUI extends JFrame {
    private final IAltaDonacion altaDonacion;
    private final IAltaUsuario altaUsuario;
    private final IAltaDistribucion altaDistribucion;
    private JPanel background;
    private JButton buttonAceptarBenef;
    private JButton buttonCancelarBenef;
    private JButton buttonCalendarioEntrega;
    private JComboBox<DTDonacion> comboDonacion;
    private JComboBox<EstadoDistribucion> comboEstado;
    private JComboBox<DtBeneficiario> comboBeneficiario;
    private JTextField textFechaEntrega;
    private JButton buttonCalendarioPrepara;
    private JTextField textFechaPrepara;


    public AltaDistribucionGUI(IAltaUsuario altaUsuario, IAltaDonacion altaDonacion, IAltaDistribucion altaDistribucion) {
        this.altaDonacion = altaDonacion;
        this.altaUsuario = altaUsuario;
        this.altaDistribucion = altaDistribucion;
        aplicarEstilosComponentes();
        cargarComboBox();
        actionButtonCalendario();
        actionAceptarCancelar();
        // Verificar si los JTextField están inicializados correctamente
        if (textFechaPrepara == null) {
            System.out.println("textFechaPrep es null en la parte del código donde se usa.");
        } else {
            System.out.println("textFechaPrep está inicializado.");
        }

        if (textFechaEntrega == null) {
            System.out.println("textFechaEntrega es null en la parte del código donde se usa.");
        } else {
            System.out.println("textFechaEntrega está inicializado.");
        }
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
        new ComponenteTextField(textFechaEntrega, "--/--/----");
        new ComponenteTextField(textFechaPrepara, "--/--/----");
    }

    // Cargar comboBox
    private void cargarComboBox() {
        List<DtBeneficiario> dtBeneficiarioList = altaUsuario.listarBeneficiarios();
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
        buttonAceptarBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboBeneficiario.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboDonacion.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (comboEstado.getSelectedItem() == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textFechaPrepara.getText().equals("--/--/----") && textFechaPrepara.getText().isEmpty()) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textFechaEntrega.getText().equals("--/--/----") && textFechaEntrega.getText().isEmpty()) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else {
                        // Define el formato de la cadena
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
                        // Parsear la cadena a un objeto LocalDateTime
                        System.out.println("En fecha prep hay"+ textFechaPrepara.getText());
                        System.out.println("En fecha entre hay"+ textFechaEntrega.getText());

                        LocalDateTime fechaHoraPrep = LocalDateTime.parse(textFechaPrepara.getText(), formatter);
                        LocalDateTime fechaEntrega = LocalDateTime.parse(textFechaEntrega.getText(), formatter);

                        altaDistribucion.crearDistribucion((DtBeneficiario) comboBeneficiario.getSelectedItem(), (DTDonacion) comboDonacion.getSelectedItem(), fechaHoraPrep, fechaEntrega, (EstadoDistribucion) comboEstado.getSelectedItem());
                        new AlertaGUI(false, "Se ha creado La Distribucion Exitosamente").mostrarAlerta();
                    }
                } catch (CamposIncompletosExeption | DateTimeParseException ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });
        buttonCancelarBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    // Comportamiento de los botones del calendario
    private void actionButtonCalendario() {

        buttonCalendarioPrepara.addActionListener(e -> {
            if (textFechaPrepara == null) {
                System.out.println("textFechaPrepara es null");
                return;
            }
            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
            int x = textFechaPrepara.getLocationOnScreen().x;
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
            if (textFechaEntrega == null) {
                System.out.println("textFechaPrepara es null");
                return;
            }
            // Crear una instancia del componente de calendario
            ComponenteCalFechaHora calendario = new ComponenteCalFechaHora();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textfield
            int x = textFechaEntrega.getLocationOnScreen().x;
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

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IAltaUsuario usuarios = fabrica.getAltaUsuario();
        IAltaDonacion donacion = fabrica.getAltaDonacion();
        IAltaDistribucion distribucion = fabrica.getIAltaDistribucion();
        JFrame frame = new JFrame("AltaDistribucionGUI");
        frame.setContentPane(new AltaDistribucionGUI(usuarios, donacion, distribucion).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
