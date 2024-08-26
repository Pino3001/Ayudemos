package Ayudemos.gui;

import Ayudemos.types.EstadoDistribucion;
import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Donacion;
import Ayudemos.types.DateTime;
import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.interfaces.IModificarDistribucion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModificarDistribucionGUI extends JFrame {

    private IModificarDistribucion modificarDistribucionInterface;
    private JTextField fechaPreparacionField;
    private JTextField fechaEntregaField;
    private JComboBox<EstadoDistribucion> estadoComboBox;
    private JComboBox<Beneficiario> beneficiarioComboBox;
    private JComboBox<Donacion> donacionComboBox;
    private JButton guardarButton;
    private JButton cancelButton;

    public ModificarDistribucionGUI(IModificarDistribucion modificarDistribucionInterface) {
        this.modificarDistribucionInterface = modificarDistribucionInterface;
        setTitle("Modificar Distribución");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear panel principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Crear campos de fecha de preparación y entrega
        fechaPreparacionField = new JTextField(20);
        fechaEntregaField = new JTextField(20);

        // Crear combo box de estado
        estadoComboBox = new JComboBox<>(EstadoDistribucion.values());

        // Crear combo box de beneficiario
        beneficiarioComboBox = new JComboBox<>();
        for (Beneficiario beneficiario : modificarDistribucionInterface.obtenerBeneficiarios()) {
            beneficiarioComboBox.addItem(beneficiario);
        }

        // Crear combo box de donación
        donacionComboBox = new JComboBox<>();
        for (Donacion donacion : modificarDistribucionInterface.obtenerDonaciones()) {
            donacionComboBox.addItem(donacion);
        }

        // Añadir componentes al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(new JLabel("Fecha de preparación:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(fechaPreparacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Fecha de entrega:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(fechaEntregaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(estadoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(new JLabel("Beneficiario:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(beneficiarioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(new JLabel("Donación:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(donacionComboBox, gbc);

        // Crear panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        guardarButton = new JButton("Guardar cambios");
        cancelButton = new JButton("Cancelar");
        panelBotones.add(guardarButton);
        panelBotones.add(cancelButton);

        // Añadir paneles a la ventana
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarDistribucionInterface.modificarDistribucion(getDistribucionFromFields());
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private DateTime convertirStringADateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return new DateTime();
    }

    private DTDistribucion getDistribucionFromFields() {
        DateTime fechaPreparacion = convertirStringADateTime(fechaPreparacionField.getText());
        DateTime fechaEntrega = convertirStringADateTime(fechaEntregaField.getText());
        EstadoDistribucion estado = (EstadoDistribucion) estadoComboBox.getSelectedItem();
        Beneficiario beneficiario = (Beneficiario) beneficiarioComboBox.getSelectedItem();
        Donacion donacion = (Donacion) donacionComboBox.getSelectedItem();
        return new DTDistribucion(fechaPreparacion, fechaEntrega, estado, donacion.getId(), beneficiario.getNombre(), beneficiario.getMail());
    }
}