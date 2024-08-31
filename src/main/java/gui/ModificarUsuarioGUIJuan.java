package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import interfaces.IAltaUsuario;
import objects.Usuario;

public class ModificarUsuarioGUIJuan extends JPanel {

/*    private JComboBox<String> userComboBox;
    private JTextField nombreField;
    private JTextField emailField;
    private JButton updateButton;

    private IAltaUsuario altaUsuario;

    public ModificarUsuarioGUIJuan(IAltaUsuario altaUsuario) {
        this.altaUsuario = altaUsuario;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2));
        JLabel userLabel = new JLabel("Seleccionar Usuario:");
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel emailLabel = new JLabel("Email:");

        userComboBox = new JComboBox<>();
        nombreField = new JTextField();
        emailField = new JTextField();

        updateButton = new JButton("Actualizar");

        actualizarListaUsuarios();

        add(userLabel);
        add(userComboBox);
        add(nombreLabel);
        add(nombreField);
        add(emailLabel);
        add(emailField);
        add(new JLabel()); // Filler
        add(updateButton);

        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarUsuario();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
    }

    private void actualizarListaUsuarios() {
        userComboBox.removeAllItems();
        List<Usuario> usuarios = altaUsuario.listarUsuarios();
        for (Usuario usuario : usuarios) {
            userComboBox.addItem(usuario.getMail());
        }
    }

    private void cargarUsuario() {
        String email = (String) userComboBox.getSelectedItem();
        Usuario usuario = altaUsuario.obtenerUsuarioPorEmail(email);

        if (usuario != null) {
            nombreField.setText(usuario.getNombre());
            emailField.setText(usuario.getMail());
        }
    }

    private void actualizarUsuario() {
        String originalEmail = (String) userComboBox.getSelectedItem();
        String nuevoNombre = nombreField.getText();
        String nuevoEmail = emailField.getText();

        if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y email no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = altaUsuario.obtenerUsuarioPorEmail(originalEmail);
        if (usuario != null) {
            usuario.setNombre(nuevoNombre);
            usuario.setMail(nuevoEmail);

            try {
                //altaUsuario.actualizarUsuario(usuario);
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
                actualizarListaUsuarios();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/
}