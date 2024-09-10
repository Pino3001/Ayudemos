package gui;

import javax.swing.*;


public class RegistroUsuarioGUI extends JFrame {

/*    private IAltaUsuario altaUsuario;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtDireccion;
    private JTextField txtFechaNacimiento;
    private JComboBox<String> comboEstado;
    private JComboBox<String> comboBarrio;
    private JTextField txtNumeroLicencia;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JRadioButton radioBeneficiario;
    private JRadioButton radioRepartidor;
    private JPanel panelBeneficiario;
    private JPanel panelRepartidor;

    public RegistroUsuarioGUI(IAltaUsuario altaUsuario) {
        this.altaUsuario = altaUsuario;
        setTitle("Registro de Usuario");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);


        // Crear campos comunes
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        // Crear botones de radio para seleccionar tipo de usuario
        radioBeneficiario = new JRadioButton("Beneficiario");
        radioRepartidor = new JRadioButton("Repartidor");
        ButtonGroup grupoRadio = new ButtonGroup();
        grupoRadio.add(radioBeneficiario);
        grupoRadio.add(radioRepartidor);

        // Crear paneles para Beneficiario y Repartidor
        panelBeneficiario = crearPanelBeneficiario();
        panelRepartidor = crearPanelRepartidor();

        // Añadir listeners a los botones de radio
        radioBeneficiario.addActionListener(e -> mostrarPanelBeneficiario());
        radioRepartidor.addActionListener(e -> mostrarPanelRepartidor());

        // Añadir componentes al panel principal

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(radioBeneficiario, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(radioRepartidor, gbc);

        // Añadir campos de Nombre y Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtEmail, gbc);

        // Panel de Beneficiario y Repartidor
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(panelBeneficiario, gbc);
        panelPrincipal.add(panelRepartidor, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        // Añadir paneles a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Inicialmente mostrar panel de Beneficiario
        mostrarPanelBeneficiario();
    }

    private JPanel crearPanelBeneficiario() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 5);

        txtDireccion = new JTextField(20);
        txtFechaNacimiento = new JTextField(10);
        comboEstado = new JComboBox<>(new String[]{"ACTIVO", "SUSPENDIDO"});
        comboBarrio = new JComboBox<>(new String[]{"CIUDAD_VIEJA", "CORDON", "PARQUE_RODO", "CENTRO", "PALERMO"});

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Fecha de Nacimiento (dd/MM/yyyy):"), gbc);

        gbc.gridx = 1;
        panel.add(txtFechaNacimiento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Estado:"), gbc);

        gbc.gridx = 1;
        panel.add(comboEstado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Barrio:"), gbc);

        gbc.gridx = 1;
        panel.add(comboBarrio, gbc);

        return panel;
    }

    private JPanel crearPanelRepartidor() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtNumeroLicencia = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Número de Licencia:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        panel.add(txtNumeroLicencia, gbc);

        return panel;
    }

    private void mostrarPanelBeneficiario() {
        panelBeneficiario.setVisible(true);
        panelRepartidor.setVisible(false);
        limpiarCamposRepartidor();
    }

    private void mostrarPanelRepartidor() {
        panelBeneficiario.setVisible(false);
        panelRepartidor.setVisible(true);
        limpiarCamposBeneficiario();
    }

    private void limpiarCamposBeneficiario() {
        txtDireccion.setText("");
        txtFechaNacimiento.setText("");
        comboEstado.setSelectedIndex(0);
        comboBarrio.setSelectedIndex(0);
        txtNombre.setText("");
        txtEmail.setText("");
    }

    private void limpiarCamposRepartidor() {
        txtNumeroLicencia.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
    }

    private void limpiarCamposGenerales() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtFechaNacimiento.setText("");
        txtNumeroLicencia.setText("");
        comboEstado.setSelectedIndex(0);
        comboBarrio.setSelectedIndex(0);
        radioBeneficiario.setSelected(true);
        mostrarPanelBeneficiario();
    }

    private void registrarUsuario() {
        try {
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();
            if (nombre.isEmpty() || email.isEmpty()) {
                throw new Exception("Nombre y Email son obligatorios.");
            }

            Usuario usuario;
            if (radioBeneficiario.isSelected()) {
                String direccion = txtDireccion.getText().trim();
                if (direccion.isEmpty()) {
                    throw new Exception("La dirección es obligatoria para el tipo Beneficiario.");
                }
                String fechaNacimientoStr = txtFechaNacimiento.getText().trim();
                LocalDate fechaNacimiento = this.altaUsuario.parseFecha(fechaNacimientoStr);
                EstadoBeneficiario estado = EstadoBeneficiario.valueOf((String) comboEstado.getSelectedItem());
                Barrio barrio = Barrio.valueOf(((String) comboBarrio.getSelectedItem()).toUpperCase().replace(" ", "_"));

                usuario = this.altaUsuario.crearBeneficiario(nombre, email, direccion, fechaNacimiento, estado, barrio);

            } else if (radioRepartidor.isSelected()) {
                String numeroLicencia = txtNumeroLicencia.getText().trim();
                if (numeroLicencia.isEmpty()) {
                    throw new Exception("El número de licencia es obligatorio para el tipo Repartidor.");
                }
                usuario = altaUsuario.crearRepartidor(nombre, email, numeroLicencia);

            } else {
                throw new Exception("Debe seleccionar el tipo de usuario.");
            }

            //altaUsuario.agregarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            limpiarCamposGenerales();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage());
        }
    }*/
}