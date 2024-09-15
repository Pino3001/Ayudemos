package gui;

import datatypes.DtBeneficiario;
import gui.componentes.CellRendererFactory;
import gui.componentes.ColorUtil;
import gui.componentes.TableListCellRenderer;
import interfaces.IControladorUsuario;
import types.EstadoBeneficiario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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
        TableCellRenderer factory = new CellRendererFactory().getEstadoBeneficiarioRenderer();
        listBeneficiarios.setCellRenderer(new TableListCellRenderer(factory));//Renderiza la lista con las tablas en su interior
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
        return jTable;
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }
}
