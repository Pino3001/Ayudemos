package gui;

import datatypes.*;
import excepciones.CamposIncompletosExeption;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;
import interfaces.Fabrica;
import interfaces.IAltaUsuario;
import objects.AltaUsuario;
import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.List;

public class ModificarUsuarioGUI extends JFrame {
    private IAltaUsuario iAltaUsuario;
    private JPanel background;
    private JButton buttonBeneficiario;
    private JButton buttonRepartidor;
    private JPanel cardBeneficiarioRepartidor;
    private JPanel panelBeneficiario;
    private JPanel panelRepartidor;
    private JComboBox<DtBeneficiario> comboBuscarBeneficiario;
    private JTextField textNombreBenef;
    private JTextField texteMailBenef;
    private JTextField textFechaNaciBenef;
    private JTextField textdireccionBenef;
    private JComboBox<EstadoBeneficiario> comboEstadoBenef;
    private JComboBox<Barrio> comboBarrioBenef;
    private JButton buttonCancelarBenef;
    private JButton buttonAceptarBenef;
    private JLabel textSeleccionadoBenefi;
    private JComboBox<DtRepartidor> comboBuscarReparti;
    private JLabel textSeleccionadoReparti;
    private JTextField textNombreReparti;
    private JTextField texteMailReparti;
    private JTextField textNumeroLicenciaRep;
    private JButton buttonAceptarReparti;
    private JButton buttonCancelarReparti;
    private CardLayout cardLayout;
    private DtRepartidor aEditarRepartidor;
    private DtBeneficiario aEditarBeneficiario;
    private Barrio barrio = null;
    private EstadoBeneficiario estadoBeneficiario = null;

    public ModificarUsuarioGUI(IAltaUsuario iAltaUsuario) {
        this.iAltaUsuario = iAltaUsuario;
        cardLayout = new CardLayout();
        cardBeneficiarioRepartidor.setLayout(cardLayout);
        // Cargo los paneles opcionales.
        cardBeneficiarioRepartidor.add(panelBeneficiario, "beneficiario");
        cardBeneficiarioRepartidor.add(panelRepartidor, "repartidor");

        aplicarEstiloscomponentes();
        cambiarTipoRepartidorBeneficiario();
        cargarComboBox();
        actionListenerComboBox();
        obtenerDatosCombo();
        actionAceptarCancelar();


    }

    //Infla el panel principal
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 600);
    }

    //Aplica estilos predeterminados a los componentes
    private void aplicarEstiloscomponentes() {
        new ComponenteTextField(textNombreBenef, "");
        new ComponenteTextField(texteMailBenef, "");
        new ComponenteTextField(textFechaNaciBenef, "");
        new ComponenteTextField(textdireccionBenef, "");
        new ComponenteTextField(textNombreReparti, "");
        new ComponenteTextField(texteMailReparti, "");
        new ComponenteTextField(textNumeroLicenciaRep, "");
        new ComponenteComboBox(comboBarrioBenef);
        new ComponenteComboBox(comboEstadoBenef);
        new ComponenteComboBox(comboBuscarBeneficiario);
        new ComponenteComboBox(comboBuscarReparti);
    }

    //Cambia entre el tipo de usuario Beneficiario-Repartidor
    private void cambiarTipoRepartidorBeneficiario() {
        buttonBeneficiario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardBeneficiarioRepartidor, "beneficiario");
            }
        });
        buttonRepartidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardBeneficiarioRepartidor, "repartidor");
            }
        });
    }

    // Cargar comboBoxs
    private void cargarComboBox() {

        // Limpiar el contenido existente de los JComboBox
        comboBuscarBeneficiario.removeAllItems();
        comboBuscarReparti.removeAllItems();

        List<DtBeneficiario> dtBeneficiarioList = iAltaUsuario.listarBeneficiarios();
        List<DtRepartidor> dtRepartidorList = iAltaUsuario.listarRepartidores();

        // Carga los combobox con todas las donaciones de tipo alimento que hay en el manejadordonaciones
        for (DtBeneficiario dtBeneficiario : dtBeneficiarioList) {
            comboBuscarBeneficiario.addItem(dtBeneficiario);
        }
        // Carga los combobox con todas las donaciones de tipo articulo que hay en el manejadordonaciones
        for (DtRepartidor dtRepartidor : dtRepartidorList) {
            comboBuscarReparti.addItem(dtRepartidor);
        }
    }

    // Maneja las interacciones de los ComboBox -- Cuando se selecciona un item mostrara en los componentes los datos del Usuario para su posterior modificacion --
    private void actionListenerComboBox() {
        comboBuscarBeneficiario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DtBeneficiario seleccionado = (DtBeneficiario) e.getItem();
                    aEditarBeneficiario = seleccionado;
                    comboBarrioBenef.removeAllItems();
                    comboEstadoBenef.removeAllItems();

                    // Agregar el barrio del beneficiario en primera instancia para mostrarse
                    if (seleccionado.getBarrio() != null) {
                        comboBarrioBenef.addItem(seleccionado.getBarrio());
                        barrio = seleccionado.getBarrio();
                    }

                    // Agregar el resto de los ítems del enum Barrio
                    for (Barrio barrio : Barrio.values()) {
                        // Evitar duplicar el ítem si ya fue agregado
                        if (!barrio.equals(seleccionado.getBarrio())) {
                            comboBarrioBenef.addItem(barrio);
                        }
                    }
                    // Agregar el Estado del beneficiario en primer lugar
                    if (seleccionado.getEstado() != null) {
                        comboEstadoBenef.addItem(seleccionado.getEstado());
                        estadoBeneficiario = seleccionado.getEstado();
                    }

                    // Agregar el resto de los ítems del enum estado
                    for (EstadoBeneficiario estado : EstadoBeneficiario.values()) {
                        // Evitar duplicar el ítem si ya fue agregado
                        if (!estado.equals(seleccionado.getEstado())) {
                            comboEstadoBenef.addItem(estado);
                        }
                    }

                    // Muestro los datos en el TextField
                    textNombreBenef.setText(seleccionado.getNombre());
                    texteMailBenef.setText(seleccionado.getMail());
                    textdireccionBenef.setText(seleccionado.getDireccion());
                    textFechaNaciBenef.setText(seleccionado.getFechaNacimiento().toString());
                }
            }
        });

        comboBuscarReparti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DtRepartidor seleccionado = (DtRepartidor) e.getItem();
                    aEditarRepartidor = seleccionado;
                    textNombreReparti.setText(seleccionado.getNombre());
                    texteMailBenef.setText(seleccionado.getMail());
                    textNumeroLicenciaRep.setText(seleccionado.getNumeroLicencia());
                }
            }
        });
    }

    // Obtener los datos del combo al modificar la seleccion
    private void obtenerDatosCombo() {
        //Obtener el barrio de la seleccion del combo
        comboBarrioBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ítem seleccionado
                barrio = (Barrio) comboBarrioBenef.getSelectedItem();
            }
        });
        // Obtener el estado de la seleccion del combo
        comboEstadoBenef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ítem seleccionado
                estadoBeneficiario = (EstadoBeneficiario) comboEstadoBenef.getSelectedItem();
            }
        });
    }

    // Maneja el comportamiento al aceptaro o cancelar una modificacion
    private void actionAceptarCancelar() {
        buttonAceptarReparti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textNombreReparti.getText().equals("") || textNombreReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailReparti.getText().equals("") || texteMailReparti.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textNumeroLicenciaRep.getText().equals("") || textNumeroLicenciaRep.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (aEditarRepartidor == null) {
                        throw new CamposIncompletosExeption("Seleccione un Usuario!");
                    } else {
                        iAltaUsuario.validarEmail(texteMailReparti.getText());
                        DtUsuario dt = new DtRepartidor(textNombreReparti.getText(), texteMailBenef.getText(), textNumeroLicenciaRep.getText());
                        iAltaUsuario.modificarUsuario(dt, aEditarRepartidor.getMail());
                        JOptionPane.showMessageDialog(null, "Se ha modificado el Beneficiario Exitosamente", "LISTO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (CamposIncompletosExeption | EmailIncorrectoExeption ex) {
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
                Date fecha = null;
                try {
                    if (textNombreBenef.getText().equals("") || textNombreBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (texteMailBenef.getText().equals("") || texteMailBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (textdireccionBenef.getText().equals("") || textdireccionBenef.getText().length() <= 0) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (barrio == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (estadoBeneficiario == null) {
                        throw new CamposIncompletosExeption("Complete todos los campos!");
                    } else if (aEditarBeneficiario == null) {
                        throw new CamposIncompletosExeption("Seleccione un Usuario!");
                    } else {
                        fecha = iAltaUsuario.parseFecha(textFechaNaciBenef.getText());
                        iAltaUsuario.validarEmail(texteMailBenef.getText());
                        DtUsuario dt = new DtBeneficiario(textNombreBenef.getText(), texteMailBenef.getText(), textdireccionBenef.getText(), fecha, estadoBeneficiario, barrio);
                        iAltaUsuario.modificarUsuario(dt, aEditarBeneficiario.getMail());
                        JOptionPane.showMessageDialog(null, "Se ha modificado el Beneficiario Exitosamente", "LISTO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (CamposIncompletosExeption | FormatoFechaIExeption | EmailIncorrectoExeption ex) {
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

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IAltaUsuario altaUsuario = fabrica.getAltaUsuario();
        JFrame frame = new JFrame("ModificarUsuarioGUI");
        frame.setContentPane(new ModificarUsuarioGUI(altaUsuario).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
