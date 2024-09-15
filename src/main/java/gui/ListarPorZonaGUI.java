package gui;

import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtUsuario;
import gui.componentes.CellRendererFactory;
import gui.componentes.ColorUtil;
import gui.componentes.ComponenteComboBox;
import interfaces.Fabrica;
import interfaces.IControladorDistribucion;
import interfaces.IControladorDonacion;
import interfaces.IControladorUsuario;
import types.Barrio;
import types.EstadoBeneficiario;
import types.EstadoDistribucion;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ListarPorZonaGUI extends JFrame{
    private IControladorDistribucion controlador;
    private IControladorUsuario controladorUsuario;

    private JPanel background;
    private JPanel paneltitulo;
    private JPanel panelCombo;
    private JComboBox<Barrio> comboBoxZonas;
    private JScrollPane scrolLista;
    private JList<JTable> JListaZonas;
    private final DefaultListModel<JTable> modeloLista;

    public ListarPorZonaGUI(IControladorDistribucion controlador, IControladorUsuario controladorUsuario) {
        this.controlador = controlador;
        this.controladorUsuario = controladorUsuario;
        modeloLista = new DefaultListModel<>();
        JListaZonas.setModel(modeloLista);
        TableCellRenderer factory = new CellRendererFactory().getEstadoDistribucionRenderer();
        JListaZonas.setCellRenderer(new gui.componentes.TableListCellRenderer(factory));//Renderiza la lista con las tablas en su interior
        initComponents();
        comboBoxZonas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Barrio barrio = (Barrio) e.getItem();
                cargarElementos(barrio);
            }
        });
        cargarComboBarrio();

    }

    // Función para cargar los elementos en la lista
    private void cargarElementos(Barrio barrio) {
        List<DtDistribucion> distribucions = controlador.obtenerListaDistribucionesZona(barrio);
        modeloLista.clear(); // Limpiar elementos existentes
        if (distribucions.isEmpty()) {// Si no existe ninguna distribucion creo un mensaje
            agregarTablaConMensaje("No hay distribuciones en la zona");
        } else {
            for (DtDistribucion distri : distribucions) {// Recorre la lista de distribuciones y crea una tabla para cada distribucion
                JTable jTable = crearTablaDistribucion(distri);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
                modeloLista.addElement(new JTable(new Object[][] {{"-----------------------" +
                        "------------------------------------" +
                        "------------------------------------" +
                        "--------------"}}, new Object[]{"--"}));
            }
        }
    }

    //crea una tabla para cada Beneficiario
    private JTable crearTablaDistribucion(DtDistribucion distribucion) {
        DtUsuario dtUsu = controladorUsuario.obtenerUsuarioPorId(distribucion.getIdUsuario());
        String[] columnNames = {"", ""};//Cabezal vacio para la tabla, no se va a utilizar pero para crear la tabla es necesario
        Object[][] data = {//Datos que tendra la tabla, izquierda el tipo de dato y derecha el dato correspondiente
                {"Nombre", dtUsu.getNombre()},
                {"eMail", dtUsu.getMail()},
                {"Fecha de Preparacion", distribucion.getFechaPreparacion()},
                {"Fecha de Entraga", (distribucion.getFechaEntrega() == null) ? "No ha sido entregado" : distribucion.getFechaEntrega()},
                {"Estado", distribucion.getEstado()},
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        return jTable;
    }

    //Si la lista es null se crea un mensaje predeterminado
    private void agregarTablaConMensaje(String mensaje) {
        String[] columnNames = {"Información"};
        Object[][] data = {{mensaje}};
        JTable jTable = new JTable(data, columnNames);
        modeloLista.addElement(jTable);
    }

    private void cargarComboBarrio(){
        comboBoxZonas.removeAllItems();
        for (Barrio barrio : Barrio.values()){
            comboBoxZonas.addItem(barrio);
        }
        comboBoxZonas.setSelectedIndex(0);
    }

    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(600,700);
    }

    private void initComponents() {
        assert background != null;
        background.setBackground(ColorUtil.getColor("backgroundColor"));
        assert paneltitulo != null;
        paneltitulo.setBackground(ColorUtil.getColor("primaryColor"));
        assert panelCombo != null;
        panelCombo.setBackground(ColorUtil.getColor("backgroundColor"));
        assert scrolLista != null;
        scrolLista.setBackground(ColorUtil.getColor("backgroundColor"));
        assert JListaZonas != null;
        JListaZonas.setBackground(ColorUtil.getColor("backgroundColor"));
        assert comboBoxZonas != null;
        new ComponenteComboBox(comboBoxZonas);
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IControladorDistribucion distribucion = fabrica.getIControladorDistribucion();
        IControladorUsuario controladorUsuario = fabrica.getIControladorUsuario();
        JFrame frame = new JFrame("ListarPorZonaGUI");
        frame.setContentPane(new ListarPorZonaGUI(distribucion,controladorUsuario).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
