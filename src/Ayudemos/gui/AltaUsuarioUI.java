package Ayudemos.gui;

import Ayudemos.datatypes.DtBeneficiario;
import Ayudemos.datatypes.DtRepartidor;
import Ayudemos.datatypes.DtUsuario;
import Ayudemos.excepciones.CamposIncompletosExeption;
import Ayudemos.excepciones.EmailIncorrectoExeption;
import Ayudemos.excepciones.FormatoFechaIExeption;
import Ayudemos.excepciones.IngresoIncorrectoExeption;
import Ayudemos.gui.componentes.ComponenteComboBox;
import Ayudemos.gui.componentes.ComponenteTextField;
import Ayudemos.interfaces.IAltaUsuario;
import Ayudemos.types.Barrio;
import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaUsuarioUI extends JFrame {
    private IAltaUsuario altaUsuario;
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
    private CardLayout cardLayout;
    private Barrio barrio = null;

    public AltaUsuarioUI(IAltaUsuario iAltaUsuario) {
        this.altaUsuario = iAltaUsuario;
        cardLayout = new CardLayout();
        cardRepartidorBeneficiario.setLayout(cardLayout);
        // Cargo los paneles opcionales.
        cardRepartidorBeneficiario.add(panelBeneficiario, "beneficiario");
        cardRepartidorBeneficiario.add(panelRepartidor, "repartidor");
        aplicarEstilosComponentes();
        cargarComboBarrio();
        cambiarTipoUsuario();
        botonesAceptarCancelar();

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
        setSize(600, 500);
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
        comboBarrio.setSelectedItem(Barrio.CIUDAD_VIEJA);
        for (Barrio barrio : Barrio.values()) {
            comboBarrio.addItem(barrio);
        }
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
                    altaUsuario.validarEmail(texteMailReparti.getText());

                    if (textNombreReparti.getText().equals("Ingrese el Nombre...") || textNombreReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailReparti.getText().equals("Ingrese el eMail...") || texteMailReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textNumeroLicencia.getText().equals("Ingrese la Licencia...") || textNumeroLicencia.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else {
                        DtUsuario dt = new DtRepartidor(textNombreReparti.getText(), texteMailReparti.getText(), textNumeroLicencia.getText());
                        altaUsuario.agregarUsuario(dt);
                        JOptionPane.showMessageDialog(null, "Se ha creado el Repartidor Exitosamente", "LISTO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (CamposIncompletosExeption | EmailIncorrectoExeption | IngresoIncorrectoExeption ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
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
                DTFecha fecha = null;
                try {
                    if (textNombreBenef.getText().equals("Ingrese el Nombre...") || textNombreBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailBenef.getText().equals("Ingrese el eMail...") || texteMailBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textDireccion.getText().equals("Ingrese la Direccion...") || textDireccion.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (barrio == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else {
                        fecha = altaUsuario.parseFecha(textFechaNaci.getText());
                        altaUsuario.validarEmail(texteMailBenef.getText());
                        DtUsuario dt = new DtBeneficiario(textNombreBenef.getText(), texteMailBenef.getText(), textDireccion.getText(), fecha, EstadoBeneficiario.ACTIVO, barrio);
                        altaUsuario.agregarUsuario(dt);
                        JOptionPane.showMessageDialog(null, "Se ha creado el Beneficiario Exitosamente", "LISTO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (CamposIncompletosExeption | FormatoFechaIExeption | EmailIncorrectoExeption |
                         IngresoIncorrectoExeption ex) {
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

/*    public static void main(String[] args) {
        JFrame frame = new JFrame("AltaUsuarioUI");
        frame.setContentPane(new AltaUsuarioUI().background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/
}

