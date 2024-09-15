package gui;

import datatypes.DtBeneficiario;
import gui.componentes.CellRendererFactory;
import gui.componentes.ColorUtil;
import gui.componentes.ComponenteComboBox;
import gui.componentes.TableListCellRenderer;
import interfaces.IControladorUsuario;
import types.Barrio;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class ListarBeneficiariosZonaGUI extends JFrame {
    private JPanel background;
    private JPanel paneltitulo;
    private JComboBox<Barrio> barrioComboBox;
    private JButton listarButton;
    private JScrollPane scroll;
    private JList<JTable> listaBeneficiarios; // Cambiamos el tipo del JList para mostrar Strings
    private JPanel panelLista;
    private DefaultListModel<JTable> modeloLista;
    private IControladorUsuario controladorUsuario;

    public ListarBeneficiariosZonaGUI(IControladorUsuario controladorUsuarioService) {
        this.controladorUsuario = controladorUsuarioService;
        modeloLista = new DefaultListModel<>();
        listaBeneficiarios.setModel(modeloLista);
        TableCellRenderer factory = new CellRendererFactory().getEstadoBeneficiarioRenderer();
        listaBeneficiarios.setCellRenderer(new gui.componentes.TableListCellRenderer(factory));//Renderiza la lista con las tablas en su interior
        listaBeneficiarios.setBackground(ColorUtil.getColor("backgroundColor"));
        panelLista.setBackground(ColorUtil.getColor("backgroundColor"));
        new ComponenteComboBox(barrioComboBox);

        // Configuración de la ventana usando el .form
        setContentPane(background);
        setTitle("Listar Beneficiarios por Estado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 700);
        setLocationRelativeTo(null);

        // Configurar el JScrollPane para que el scroll funcione cuando sea necesario
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Cargar los estados en el comboBox
        cargarEstados();

        // Acción del botón "Listar Beneficiarios"
        listarButton.addActionListener(e -> {
            Barrio barrioSeleccionado = (Barrio) barrioComboBox.getSelectedItem();
            cargarBeneficiariosPorZona(barrioSeleccionado);
        });
    }

    private void cargarEstados() {
        // Recorrer los valores del enum EstadoBeneficiario y agregarlos al comboBox
        for (Barrio barrio : Barrio.values()) {
            barrioComboBox.addItem(barrio);
        }
    }

    private void cargarBeneficiariosPorZona(Barrio barrioSeleccionado) {
        List<DtBeneficiario> beneficiarios = controladorUsuario.listarBeneficiariosPorZona(barrioSeleccionado);
        modeloLista.clear(); // Limpiar la lista de beneficiarios

        if (beneficiarios.isEmpty()) {
            agregarTablaConMensaje("No hay beneficiarios en este estado.");
        } else {
            for (DtBeneficiario beneficiario : beneficiarios) {
                JTable jTable = crearTablaBeneficiario(beneficiario);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
                modeloLista.addElement(new JTable(new Object[][] {{"-------------------" +
                        "--------------------------------" +
                        "--------------------------------" +
                        "----------------"}}, new Object[]{"--"}));
            }
        }
    }

    // Crea una tabla para cada beneficiario
    private JTable crearTablaBeneficiario(DtBeneficiario beneficiario) {
        String[] columnNames = {"Campo", "Valor"};
        Object[][] data = {
                {"Nombre", beneficiario.getNombre()},
                {"Email", beneficiario.getMail()},
                {"Dirección", beneficiario.getDireccion()},
                {"Estado", beneficiario.getEstado()}
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
            setBorder(BorderFactory.createEmptyBorder(2, 7, 3, 7)); // Margen entre tablas
            add(panel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends JTable> list, JTable value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setBackground(isSelected ? Color.GRAY : list.getBackground());
            panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());

            table.setModel(value.getModel());
            table.setBackground(ColorUtil.getColor("backgroundColor"));
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