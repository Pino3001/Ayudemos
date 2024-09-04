package gui;

import datatypes.DtBeneficiario;
import interfaces.IListarBeneficiariosZona;
import types.Barrio;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarBeneficiariosZonaGUI extends JFrame {
    private JComboBox<Barrio> barrioComboBox;
    private JTextArea resultadoArea;
    private IListarBeneficiariosZona listarBeneficiariosZonaService;

    public ListarBeneficiariosZonaGUI(IListarBeneficiariosZona listarBeneficiariosZonaService) {
        this.listarBeneficiariosZonaService = listarBeneficiariosZonaService;
        initUI();
    }

    private void initUI() {
        setTitle("Listar Beneficiarios por Zona");
        setLayout(new BorderLayout());

        // Configurar el comportamiento al cerrar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel superior con ComboBox y botón
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel barrioLabel = new JLabel("Selecciona un barrio:");
        barrioComboBox = new JComboBox<>(Barrio.values());
        JButton listarButton = new JButton("Listar Beneficiarios");

        panel.add(barrioLabel);
        panel.add(barrioComboBox);
        panel.add(listarButton);

        add(panel, BorderLayout.NORTH);

        // Área de texto para mostrar los resultados
        resultadoArea = new JTextArea(15, 30);
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        // Acción del botón
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Barrio barrioSeleccionado = (Barrio) barrioComboBox.getSelectedItem();
                List<DtBeneficiario> beneficiarios = listarBeneficiariosZonaService.listarBeneficiariosPorZona(barrioSeleccionado);

                StringBuilder resultado = new StringBuilder();
                for (DtBeneficiario b : beneficiarios) {
                    resultado.append("Nombre: ").append(b.getNombre()).append("\n");
                    //resultado.append("Email: ").append(b.getMail()).append("\n");
                    resultado.append("Dirección: ").append(b.getDireccion()).append("\n");
                    resultado.append("Estado: ").append(b.getEstado()).append("\n\n");
                }

                resultadoArea.setText(resultado.toString());
            }
        });

        // Configuraciones de la ventana
        setSize(500, 500);
        setLocationRelativeTo(null);
    }
}
