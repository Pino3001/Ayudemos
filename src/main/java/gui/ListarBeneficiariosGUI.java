package gui;

import datatypes.DtBeneficiario;
import gui.componentes.ColorUtil;
import interfaces.IControladorUsuario;
import types.EstadoBeneficiario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class ListarBeneficiariosGUI extends JFrame {
    private JPanel background;
    private JPanel panelLista;
    private JPanel paneltitulo;
    private JScrollPane scroll;
    private JList<JTable> listBeneficiarios;
    private final DefaultListModel<JTable> modeloLista;
    private IControladorUsuario controladorUsuario;

    public ListarBeneficiariosGUI(IControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
        modeloLista = new DefaultListModel<>();
        listBeneficiarios.setModel(modeloLista);
        listBeneficiarios.setCellRenderer(new TableListCellRenderer());//Renderiza la lista con las tablas en su interior
        aplicarEstilos();
        cargarElementos();
    }

    private void aplicarEstilos(){
        // Establecer colores de fondo
        background.setBackground(ColorUtil.getColor("backgroundColor"));
        panelLista.setBackground(ColorUtil.getColor("backgroundColor"));
        paneltitulo.setBackground(ColorUtil.getColor("primaryColor"));
        scroll.setBackground(ColorUtil.getColor("backgroundColor"));

        listBeneficiarios.setBackground(ColorUtil.getColor("backgroundColor"));
    }

    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(600, 700);
    }

    // Función para cargar los elementos en la lista
    private void cargarElementos() {
        List<DtBeneficiario> beneficiarios = controladorUsuario.listarBeneficiarios();
        modeloLista.clear(); // Limpiar elementos existentes
        if (beneficiarios == null) {
            agregarTablaConMensaje("No hay beneficiarios disponibles");
        } else {
            for (DtBeneficiario beneficiario : beneficiarios) {
                JTable jTable = crearTablaParaBeneficiario(beneficiario);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
                modeloLista.addElement(new JTable(new Object[][] {{"-----------------------" +
                        "------------------------------------" +
                        "------------------------------------" +
                        "--------------"}}, new Object[]{"--"}));
            }
        }
    }

    //Si la lista es null se crea un mensaje predeterminado
    private void agregarTablaConMensaje(String mensaje) {
        String[] columnNames = {"Información"};
        Object[][] data = {{mensaje}};
        JTable jTable = new JTable(data, columnNames);
        jTable.setShowGrid(false);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        jTable.setFont(new Font("Roboto light", Font.PLAIN, 14));
        jTable.setForeground(ColorUtil.getColor("primaryColor"));
        jTable.setPreferredScrollableViewportSize(new Dimension(450, 80));
        jTable.setFillsViewportHeight(true);
        modeloLista.addElement(jTable);
    }

    //crea una tabla para cada Beneficiario
    private JTable crearTablaParaBeneficiario(DtBeneficiario beneficiario) {
        String[] columnNames = {"Campo", "Valor"};
        Object[][] data = {
                {"Nombre", beneficiario.getNombre()},
                {"Fecha de Nacimiento", beneficiario.getFechaNacimiento()},
                {"eMail", beneficiario.getMail()},
                {"Direccion", beneficiario.getDireccion()},
                {"Barrio", beneficiario.getBarrio()},
                {"Estado", beneficiario.getEstado()}
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        jTable.setFont(new Font("Roboto light", Font.PLAIN, 14));
        jTable.setForeground(ColorUtil.getColor("primaryColor"));
        jTable.setPreferredScrollableViewportSize(new Dimension(450, 80));
        jTable.setFillsViewportHeight(true);
        jTable.getColumnModel().getColumn(1).setCellRenderer(new EstadoCellRenderer());//Pintar El estado con colores

        return jTable;
    }

    // Renderer personalizado para JTable dentro de JList
    private static class TableListCellRenderer extends JPanel implements ListCellRenderer<JTable> {
        private final JPanel panel;
        private final JTable table;

        public TableListCellRenderer() {
            this.panel = new JPanel(new BorderLayout());
            this.table = new JTable(); // Crear un JTable reutilizable

            // Configuración inicial del panel
            setLayout(new BorderLayout());
            panel.add(table, BorderLayout.CENTER); // Añadir JTable al panel
            setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10)); // Margen entre tablas
            add(panel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends JTable> list, JTable value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            // Configurar selección y fondo
            setBackground(isSelected ? ColorUtil.getColor("backgroundColor") : list.getBackground()); // No se muestra ningun borde al seleccionar
            panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());

            // Actualizar contenido del JTable
            table.setModel(value.getModel()); // Actualizar solo el modelo de datos
            table.setShowGrid(false);
            table.setRowHeight(18);
            table.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
            table.setFont(new Font("Roboto light", Font.PLAIN, 14));
            table.setForeground(ColorUtil.getColor("primaryColor"));
            table.setPreferredScrollableViewportSize(new Dimension(450, 90));
            table.setFillsViewportHeight(true);

            // Configurar el renderer de la columna "Estado"
            if (table.getColumnCount() > 1) {  // Asegura que la tabla tenga la columna "Estado"
                table.getColumnModel().getColumn(1).setCellRenderer(new EstadoCellRenderer());
            }

            // Redibujar el componente
            revalidate();
            repaint();

            return this;
        }
    }

    // Renderer personalizado para la columna "Estado"
    private static class EstadoCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (EstadoBeneficiario.ACTIVO.equals(value)) {
                c.setBackground(new Color(0x62FF65));
            } else if (EstadoBeneficiario.SUSPENDIDO.equals(value)) {
                c.setBackground(new Color(0xFFF65A5A, true));
            } else {
                c.setBackground(ColorUtil.getColor("backgroundColor"));
            }

            return c;
        }
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }
}
