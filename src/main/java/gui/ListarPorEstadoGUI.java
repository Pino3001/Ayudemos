package gui;

import datatypes.*;
import gui.componentes.ColorUtil;
import gui.componentes.ComponenteComboBox;
import interfaces.Fabrica;
import interfaces.IControladorDistribucion;
import interfaces.IControladorDonacion;
import interfaces.IControladorUsuario;
import types.EstadoDistribucion;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ListarPorEstadoGUI extends JFrame {
    private IControladorUsuario controladorUsuario;
    private IControladorDistribucion controladorDistribucion;
    private IControladorDonacion controladorDonacion;


    private JPanel background;
    private JPanel paneltitulo;
    private JPanel panelCombo;
    private JComboBox<String> comboBoxEstados;
    private JScrollPane scrolLista;
    private JList<JTable> JListaEstados;
    private final DefaultListModel<JTable> modeloLista;

    public ListarPorEstadoGUI(IControladorDistribucion controladorDistribucion, IControladorUsuario controladorUsuario, IControladorDonacion controladorDonacion) {
        this.controladorDistribucion = controladorDistribucion;
        this.controladorUsuario = controladorUsuario;
        this.controladorDonacion = controladorDonacion;
        modeloLista = new DefaultListModel<>();
        JListaEstados.setModel(modeloLista);
        JListaEstados.setCellRenderer(new ListarPorEstadoGUI.TableListCellRenderer());//Renderiza la lista con las tablas en su interior
        initComponents();
        comboBoxEstados.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!e.getItem().equals("TODAS")){
                    EstadoDistribucion estadoDistribucion = EstadoDistribucion.valueOf(e.getItem().toString());
                    cargarElementos(estadoDistribucion);
                }else cargarTodosElementos();
            }
        });
        cargarComboEstado();
    }

    private void cargarComboEstado() {
        comboBoxEstados.removeAllItems();
        comboBoxEstados.addItem("TODAS");
        for (EstadoDistribucion estadoDistribucion : EstadoDistribucion.values()) {
            comboBoxEstados.addItem(estadoDistribucion.toString());
        }
        comboBoxEstados.setSelectedIndex(0);
    }

    // Función para cargar los elementos en la lista
    private void cargarTodosElementos() {
        List<DtDistribucion> distribucions = controladorDistribucion.obtenerDistribuciones();
        modeloLista.clear(); // Limpiar elementos existentes
        if (distribucions.isEmpty()) {// Si no existe ninguna distribucion creo un mensaje
            agregarTablaConMensaje("No hay distribuciones en la zona");
        } else {
            for (DtDistribucion distri : distribucions) {// Recorre la lista de distribuciones y crea una tabla para cada distribucion
                JTable jTable = crearTablaTodasDistribuciones(distri);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
            }
        }
    }

    // Función para cargar los elementos en la lista
    private void cargarElementos(EstadoDistribucion estado) {
        List<DtDistribucion> distribucions = controladorDistribucion.listarDistribucionesPorEstado(estado);
        modeloLista.clear(); // Limpiar elementos existentes
        if (distribucions.isEmpty()) {// Si no existe ninguna distribucion creo un mensaje
            agregarTablaConMensaje("No hay distribuciones en la zona");
        } else {
            for (DtDistribucion distri : distribucions) {// Recorre la lista de distribuciones y crea una tabla para cada distribucion
                JTable jTable = crearTablaDistribucion(distri);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
            }
        }
    }

    //crea una tabla para las distribuciones segun el estado
    private JTable crearTablaDistribucion(DtDistribucion distribucion) {
        DtUsuario dtUsu = controladorUsuario.obtenerUsuarioPorId(distribucion.getIdUsuario());
        DtBeneficiario dtBeneficiario;
        if (dtUsu instanceof DtBeneficiario) dtBeneficiario = (DtBeneficiario) dtUsu;
        else return null;
        String[] columnNames = {"", ""};//Cabezal vacio para la tabla, no se va a utilizar pero para crear la tabla es necesario
        Object[][] data = {//Datos que tendra la tabla, izquierda el tipo de dato y derecha el dato correspondiente
                {"Nombre", dtBeneficiario.getNombre()},
                {"eMail", dtBeneficiario.getMail()},
                {"Fecha de Preparacion", distribucion.getFechaPreparacion()},
                {"Fecha de Entraga", distribucion.getFechaEntrega()},
                {"Zona", dtBeneficiario.getBarrio()},
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        jTable.setFont(new Font("Roboto light", Font.PLAIN, 14));//Fuente de la tabla
        jTable.setForeground(ColorUtil.getColor("primaryColor"));
        jTable.setPreferredScrollableViewportSize(new Dimension(450, 80));
        jTable.setFillsViewportHeight(true);
        return jTable;
    }

    //crea una tabla para mostrar todos los beneficiarios
    private JTable crearTablaTodasDistribuciones(DtDistribucion distribucion) {
        DtUsuario dtUsu = controladorUsuario.obtenerUsuarioPorId(distribucion.getIdUsuario());
        DTDonacion donacion = controladorDonacion.buscarDonacionID(distribucion.getIdDonacion());
        DtBeneficiario dtBeneficiario;
        if (dtUsu instanceof DtBeneficiario) dtBeneficiario = (DtBeneficiario) dtUsu;
        else return null;
        String[] columnNames = {"", ""};//Cabezal vacio para la tabla, no se va a utilizar pero para crear la tabla es necesario
        Object[][] data = {//Datos que tendra la tabla, izquierda el tipo de dato y derecha el dato correspondiente
                {"Nombre", dtBeneficiario.getNombre()},
                {"eMail", dtBeneficiario.getMail()},
                {"Donacion",(donacion instanceof DTArticulo)
                        ? ((DTArticulo) donacion).getDescripcion()  // Llamar a getDescripcion() si es DTArticulo
                        : ((DTAlimento) donacion).getDescripcionProductos()},// llamar a getgetDescripcionProductos si es DTALimento
                {"Fecha de Preparacion", distribucion.getFechaPreparacion()},
                {"Fecha de Entraga", distribucion.getFechaEntrega()},
                {"Zona", dtBeneficiario.getBarrio()},
                {"Estado", distribucion.getEstado()},
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        jTable.setFont(new Font("Roboto light", Font.PLAIN, 14));//Fuente de la tabla
        jTable.setForeground(ColorUtil.getColor("primaryColor"));
        jTable.setPreferredScrollableViewportSize(new Dimension(450, 80));
        jTable.setFillsViewportHeight(true);
        jTable.getColumnModel().getColumn(1).setCellRenderer(new ListarPorEstadoGUI.EstadoCellRenderer());
        return jTable;
    }

    //Si la lista es null se crea un mensaje predeterminado
    private void agregarTablaConMensaje(String mensaje) {
        String[] columnNames = {"Información"};
        Object[][] data = {{mensaje}};
        JTable jTable = new JTable(data, columnNames);
        modeloLista.addElement(jTable);
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
            setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7)); // Margen entre tablas
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
            table.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
            table.setFont(new Font("Roboto light", Font.PLAIN, 14));
            table.setForeground(ColorUtil.getColor("primaryColor"));
            table.setPreferredScrollableViewportSize(new Dimension(450, 90));
            table.setFillsViewportHeight(true);

            // Configurar el renderer de la columna "Estado"
            if (table.getColumnCount() > 1) {  // Asegura que la tabla tenga la columna "Estado"
                table.getColumnModel().getColumn(1).setCellRenderer(new ListarPorEstadoGUI.EstadoCellRenderer());
            }
            // Redibujar el componente
            revalidate();
            repaint();

            return this;
        }
    }

    // Renderer personalizado para la columna "Estado" --Pinta el fondo de la fila estado si esta existe en la tabla
    private static class EstadoCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (EstadoDistribucion.ENTREGADO.equals(value)) {
                c.setBackground(new Color(0x62FF65));
            } else if (EstadoDistribucion.PENDIENTE.equals(value)) {
                c.setBackground(new Color(0xFFF65A5A, true));
            } else if (EstadoDistribucion.EN_CAMINO.equals(value)) {
                c.setBackground(new Color(0x6868EC));
            } else {
                c.setBackground(ColorUtil.getColor("backgroundColor"));
            }

            return c;
        }
    }

    // Inicia estilos a los componentes
    private void initComponents() {
        assert background != null;
        background.setBackground(ColorUtil.getColor("backgroundColor"));
        assert paneltitulo != null;
        paneltitulo.setBackground(ColorUtil.getColor("primaryColor"));
        assert panelCombo != null;
        panelCombo.setBackground(ColorUtil.getColor("backgroundColor"));
        assert scrolLista != null;
        scrolLista.setBackground(ColorUtil.getColor("backgroundColor"));
        assert JListaEstados != null;
        JListaEstados.setBackground(ColorUtil.getColor("backgroundColor"));
        assert comboBoxEstados != null;
        new ComponenteComboBox(comboBoxEstados);
    }

    // Posiciona el componente en pantalla segun las coordenadas dadas
    public void setPosicion(int x, int y) {
        this.setLocation(x, y);
    }

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IControladorDistribucion distribucion = fabrica.getIControladorDistribucion();
        IControladorUsuario controladorUsuario = fabrica.getIControladorUsuario();
        IControladorDonacion iControladorDonacion = fabrica.getAltaDonacion();
        JFrame frame = new JFrame("ListarPorEstadoGUI");
        frame.setContentPane(new ListarPorEstadoGUI(distribucion, controladorUsuario, iControladorDonacion).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Infla el componente principal
    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(600, 700);
    }
}
