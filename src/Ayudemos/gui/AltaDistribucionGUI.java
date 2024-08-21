import javax.swing.*;
import java.awt.*;

public class AltaDistribucionGUI extends JFrame {

    public static void main(String[] args) {
        // Crear el frame
        JFrame frame = new JFrame("Alta de Distribución");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new GridBagLayout());

        // Crear componentes
        JLabel estadoLabel = new JLabel("Estado");
        JTextField estadoField = new JTextField("PENDIENTE", 10);
        estadoField.setEditable(false);

        JLabel cantidadLabel = new JLabel("Cantidad de elementos");
        JTextField cantidadField = new JTextField(10);

        JLabel beneficiarioLabel = new JLabel("Beneficiario");
        JComboBox<String> beneficiarioComboBox = new JComboBox<>(new String[]{"Beneficiario 1", "Beneficiario 2"});

        JLabel donacionesLabel = new JLabel("Donaciones");
        JComboBox<String> donacionesComboBox = new JComboBox<>(new String[]{"Donación 1", "Donación 2"});

        JLabel fechaPrepLabel = new JLabel("Fecha de preparación");
        JTextField fechaPrepField = new JTextField(10);

        JLabel fechaEntLabel = new JLabel("Fecha de entrega");
        JTextField fechaEntField = new JTextField(10);

        JButton confirmarButton = new JButton("Confirmar");

        // Configurar layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(estadoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(estadoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(cantidadLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(cantidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(beneficiarioLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(beneficiarioComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(donacionesLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(donacionesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(fechaPrepLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(fechaPrepField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(fechaEntLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(fechaEntField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        frame.add(confirmarButton, gbc);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}