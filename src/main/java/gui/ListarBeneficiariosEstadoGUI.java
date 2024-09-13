package gui;

import datatypes.DtBeneficiario;
import interfaces.IControladorUsuario;
import types.EstadoBeneficiario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarBeneficiariosEstadoGUI extends JFrame {
    private JPanel background;
    private JPanel paneltitulo;
    private JComboBox<EstadoBeneficiario> estadoComboBox;
    private JButton listarButton;
    private JScrollPane scroll;
    private JList<JTable> listaBeneficiarios;
    private JPanel panelLista;
    private DefaultListModel<JTable> modeloLista;
    private IControladorUsuario controladorUsuario;

    public ListarBeneficiariosEstadoGUI(IControladorUsuario controladorUsuarioService) {
        this.controladorUsuario = controladorUsuarioService;
        modeloLista = new DefaultListModel<>();
        listaBeneficiarios.setModel(modeloLista);
        listaBeneficiarios.setCellRenderer(new TableListCellRenderer());

        // Configuración de la ventana usando el .form
        setContentPane(background);
        setTitle("Listar Beneficiarios por Estado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Configurar el JScrollPane para que el scroll funcione cuando sea necesario
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Cargar los estados en el comboBox
        cargarEstados();

        // Acción del botón "Listar Beneficiarios"
        listarButton.addActionListener(e -> {
            EstadoBeneficiario estadoSeleccionado = (EstadoBeneficiario) estadoComboBox.getSelectedItem();
            cargarBeneficiariosPorEstado(estadoSeleccionado);
        });
    }

    private void cargarEstados() {
        // Recorrer los valores del enum EstadoBeneficiario y agregarlos al comboBox
        for (EstadoBeneficiario estado : EstadoBeneficiario.values()) {
            estadoComboBox.addItem(estado);
        }
    }

    private void cargarBeneficiariosPorEstado(EstadoBeneficiario estadoSeleccionado) {
        List<DtBeneficiario> beneficiarios = controladorUsuario.listarBeneficiariosPorEstado(estadoSeleccionado);
        modeloLista.clear(); // Limpiar la lista de beneficiarios

        if (beneficiarios.isEmpty()) {
            agregarTablaConMensaje("No hay beneficiarios en este estado.");
        } else {
            for (DtBeneficiario beneficiario : beneficiarios) {
                JTable jTable = crearTablaBeneficiario(beneficiario);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
            }
        }
    }

    // Crea una tabla para cada beneficiario
    private JTable crearTablaBeneficiario(DtBeneficiario beneficiario) {
        String[] columnNames = {"Campo", "Valor"};
        Object[][] data = {
                {"Nombre", beneficiario.getNombre()},
                {"Dirección", beneficiario.getDireccion()},
                {"Barrio", beneficiario.getBarrio()}
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(Color.LIGHT_GRAY);
        jTable.setFont(new Font("Roboto light", Font.PLAIN, 14));
        jTable.setPreferredScrollableViewportSize(new Dimension(450, 80)); // Ajustar tamaño
        jTable.setFillsViewportHeight(true);

        return jTable;
    }

    private void agregarTablaConMensaje(String mensaje) {
        String[] columnNames = {"Información"};
        Object[][] data = {{mensaje}};
        JTable jTable = new JTable(data, columnNames);
        modeloLista.addElement(jTable);
    }

    public void setPosicion(int x, int y) {
    }

    private static class TableListCellRenderer extends JPanel implements ListCellRenderer<JTable> {
        private final JPanel panel;
        private final JTable table;

        public TableListCellRenderer() {
            this.panel = new JPanel(new BorderLayout());
            this.table = new JTable(); // Crear un JTable reutilizable
            setLayout(new BorderLayout());
            panel.add(table, BorderLayout.CENTER); // Añadir JTable al panel
            setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7)); // Margen entre tablas
            add(panel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends JTable> list, JTable value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setBackground(isSelected ? Color.GRAY : list.getBackground());
            panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());

            table.setModel(value.getModel());
            table.setShowGrid(false);
            table.setFont(new Font("Roboto light", Font.PLAIN, 14));
            table.setForeground(Color.BLACK);
            table.setPreferredScrollableViewportSize(new Dimension(450, 80));
            table.setFillsViewportHeight(true);

            revalidate();
            repaint();
            return this;
        }
    }
}
