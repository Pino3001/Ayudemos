package gui;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.CamposIncompletosExeption;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import gui.componentes.AlertaGUI;
import gui.componentes.ComponenteCalendario;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.IControladorUsuario;
import types.Barrio;
import types.EstadoBeneficiario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AltaUsuarioUI extends JFrame {
    private IControladorUsuario controladorUsuario;
    private JPanel background;
    private JButton buttonBeneficiario;
    private JButton buttonRepartidor;
    private JPanel cardRepartidorBeneficiario;
    private JPanel panelBeneficiario;
    private JTextField textNombreBenef;
    private JTextField texteMailBenef;
    private JTextField textFechaNaci;
    private JTextField textDireccion;
    private JComboBox<Barrio> comboBarrio;
    private JButton buttonAceptarBenef;
    private JButton buttonCancelarBenef;
    private JPanel panelRepartidor;
    private JTextField textNombreReparti;
    private JTextField texteMailReparti;
    private JTextField textNumeroLicencia;
    private JButton buttonAceptarReparti;
    private JButton buttonCancelarReparti;
    private JButton buttonCalendar;
    private CardLayout cardLayout;
    private Barrio barrio = null;

    public AltaUsuarioUI(IControladorUsuario iAltaUsuario) {
        this.controladorUsuario = iAltaUsuario;
        cardLayout = new CardLayout();
        cardRepartidorBeneficiario.setLayout(cardLayout);
        // Cargo los paneles opcionales.
        cardRepartidorBeneficiario.add(panelBeneficiario, "beneficiario");
        cardRepartidorBeneficiario.add(panelRepartidor, "repartidor");
        aplicarEstilosComponentes();
        cambiarTipoUsuario();
        actionButtonCalendario();
        botonesAceptarCancelar();
        cargarComboBarrio();
        comboBarrio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ítem seleccionado
                barrio = (Barrio) comboBarrio.getSelectedItem();
            }
        });

    }

    //Infla el componente principal
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 500);
    }

    //Aplica Estilos a los componentes
    private void aplicarEstilosComponentes() {
        new ComponenteTextField(textNombreBenef, "Ingrese el Nombre...");
        new ComponenteTextField(texteMailBenef, "Ingrese el eMail...");
        new ComponenteTextField(textFechaNaci, "Ingrese la Fecha...");
        new ComponenteTextField(textDireccion, "Ingrese la Direccion...");
        new ComponenteTextField(textNombreReparti, "Ingrese el Nombre...");
        new ComponenteTextField(texteMailReparti, "Ingrese el Email...");
        new ComponenteTextField(textNumeroLicencia, "Ingrese la Licencia...");
        new ComponenteComboBox(comboBarrio);
    }

    // Agregar items al combo
    private void cargarComboBarrio() {

        for (Barrio barrio : Barrio.values()) {
            comboBarrio.addItem(barrio);
        }
        comboBarrio.setSelectedItem(Barrio.CIUDAD_VIEJA);
        barrio = (Barrio) comboBarrio.getSelectedItem();
    }

    //Maneja el CardLayaut para mostrar los imputs segun el tipo de usuario a ingresar
    private void cambiarTipoUsuario() {
        buttonBeneficiario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardRepartidorBeneficiario, "beneficiario");
            }
        });
        buttonRepartidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardRepartidorBeneficiario, "repartidor");
            }
        });
    }

    // Comportamiento de los botones Aceptar y Cancelar
    private void botonesAceptarCancelar() {
        buttonAceptarReparti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controladorUsuario.validarEmail(texteMailReparti.getText());

                    if (textNombreReparti.getText().equals("Ingrese el Nombre...") || textNombreReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailReparti.getText().equals("Ingrese el eMail...") || texteMailReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textNumeroLicencia.getText().equals("Ingrese la Licencia...") || textNumeroLicencia.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (controladorUsuario.existeUsuario(texteMailReparti.getText())) {
                        throw new CamposIncompletosExeption("Un Usuario con el email " + texteMailReparti.getText() + " ya existe");
                    } else {
                        controladorUsuario.validarEmail(texteMailBenef.getText());
                        DtUsuario dt = new DtRepartidor(null, textNombreReparti.getText(), texteMailReparti.getText(), textNumeroLicencia.getText());
                        controladorUsuario.agregarUsuario(dt);
                        limpiarCampos();
                        new AlertaGUI(false, "Se ha creado el Repartidor Exitosamente").mostrarAlerta();
                    }
                } catch (CamposIncompletosExeption | EmailIncorrectoExeption | IngresoIncorrectoExeption ex) {
                    new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
                }
            }
        });
        buttonCancelarReparti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonAceptarBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fecha = null;
                try {
                    if (textNombreBenef.getText().equals("Ingrese el Nombre...") || textNombreBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailBenef.getText().equals("Ingrese el eMail...") || texteMailBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textDireccion.getText().equals("Ingrese la Direccion...") || textDireccion.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (barrio == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (controladorUsuario.existeUsuario(texteMailBenef.getText())) {
                        throw new CamposIncompletosExeption("Un Usuario con el email " + texteMailBenef.getText() + " ya existe");
                    } else {
                        fecha = controladorUsuario.parseFecha(textFechaNaci.getText());
                        controladorUsuario.validarEmail(texteMailBenef.getText());
                        DtUsuario dt = new DtBeneficiario(null, textNombreBenef.getText(), texteMailBenef.getText(), textDireccion.getText(), fecha, EstadoBeneficiario.ACTIVO, barrio);
                        controladorUsuario.agregarUsuario(dt);
                        limpiarCampos();
                        new AlertaGUI(false, "Se ha creado el Beneficiario Exitosamente").mostrarAlerta();
                    }
                } catch (CamposIncompletosExeption | FormatoFechaIExeption | EmailIncorrectoExeption |
                         IngresoIncorrectoExeption ex) {
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

    private  void limpiarCampos(){
        textNombreBenef.setText("Ingrese el Nombre...");
        texteMailBenef.setText("Ingrese el eMail...");
        textFechaNaci.setText("Ingrese la Fecha...");
        textDireccion.setText("Ingrese la Direccion...");
        textNombreReparti.setText("Ingrese el Nombre...");
        texteMailReparti.setText("Ingrese el Email...");
        textNumeroLicencia.setText("Ingrese la Licencia...");
        cargarComboBarrio();
    }

    // Comportamiento de los botones del calendario
    private void actionButtonCalendario() {

        buttonCalendar.addActionListener(e -> {
            if (textFechaNaci == null) {
                System.out.println("textFechaPrepara es null");
                return;
            }
            // Crear una instancia del componente de calendario
            ComponenteCalendario calendario = new ComponenteCalendario();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
            int x = textFechaNaci.getLocationOnScreen().x;
            int y = textFechaNaci.getLocationOnScreen().y + buttonCalendar.getHeight();

            // Mostrar el calendario y obtener la fecha seleccionada
            String fechaSeleccionada = calendario.mostrarYObtenerFechaSeleccionada(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechaSeleccionada != null) {
                // Actualizar un campo de texto con la fecha seleccionada
                textFechaNaci.setText(fechaSeleccionada);
            }
        });
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }

/*    public static void main(String[] args) {
        JFrame frame = new JFrame("AltaUsuarioUI");
        frame.setContentPane(new AltaUsuarioUI().background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/
}

