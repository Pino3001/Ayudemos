package gui;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import excepciones.CamposIncompletosExeption;
import excepciones.FormatoFechaIExeption;
import gui.componentes.ComponenteCalendario;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.Fabrica;
import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import types.DTFecha;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AltaDistribucionGUI extends JFrame {
    private IAltaDonacion altaDonacion;
    private IAltaUsuario altaUsuario;
    private IAltaDistribucion altaDistribucion;
    private JPanel background;
    private JTextField textFechaPrep;
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
        actionBotonCalendario();
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
        new ComponenteTextField(textFechaEntrega, "--/--/----");
        new ComponenteTextField(textFechaPrepara, "--/--/----");
    }

    // Cargar comboBox
    private void cargarComboBox() {
        java.util.List<DtBeneficiario> dtBeneficiarioList = altaUsuario.listarBeneficiarios();
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
                        LocalDate fechaPrepara = altaUsuario.parseFecha(textFechaPrep.getText());
                        LocalDate fechaEntrega = altaUsuario.parseFecha(textFechaEntrega.getText());
                        altaDistribucion.crearDistribucion((DtBeneficiario) comboBeneficiario.getSelectedItem(), (DTDonacion) comboDonacion.getSelectedItem(), fechaPrepara, fechaEntrega, (EstadoDistribucion) comboEstado.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Se ha creado La Distribucion Exitosamente", "LISTO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (CamposIncompletosExeption | FormatoFechaIExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
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
    private void actionBotonCalendario() {
        buttonCalendarioPrepara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia del componente de calendario
                ComponenteCalendario calendario = new ComponenteCalendario();
                // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
                int x = textFechaPrepara.getLocationOnScreen().x - 130;
                int y = textFechaPrepara.getLocationOnScreen().y + buttonCalendarioPrepara.getHeight();

                // Mostrar el calendario y obtener la fecha seleccionada
                String fechaSeleccionada = calendario.mostrarYObtenerFechaSeleccionada(x, y);

                // Verificar si se seleccionó una fecha o si se canceló la selección
                if (fechaSeleccionada != null) {
                    // Actualizar un campo de texto con la fecha seleccionada
                    textFechaPrepara.setText(fechaSeleccionada);
                }
            }
        });
        buttonCalendarioEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia del componente de calendario
                ComponenteCalendario calendario = new ComponenteCalendario();
                // Calcular la posición del calendario para que aparezca justo debajo del botón y el textfield
                int x = textFechaEntrega.getLocationOnScreen().x - 130;
                int y = textFechaEntrega.getLocationOnScreen().y + buttonCalendarioEntrega.getHeight();

                // Mostrar el calendario y obtener la fecha seleccionada
                String fechaSeleccionada = calendario.mostrarYObtenerFechaSeleccionada(x, y);

                // Verificar si se seleccionó una fecha o si se canceló la selección
                if (fechaSeleccionada != null) {
                    // Actualizar un campo de texto con la fecha seleccionada
                    textFechaEntrega.setText(fechaSeleccionada);
                }
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
