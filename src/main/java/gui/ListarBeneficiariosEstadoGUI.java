package gui;

import datatypes.DtBeneficiario;
import gui.componentes.CellRendererFactory;
import gui.componentes.ColorUtil;
import gui.componentes.ComponenteComboBox;
import gui.componentes.TableListCellRenderer;
import interfaces.IControladorUsuario;
import types.EstadoBeneficiario;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
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
    private final DefaultListModel<JTable> modeloLista;
    private final IControladorUsuario controladorUsuario;

    public ListarBeneficiariosEstadoGUI(IControladorUsuario controladorUsuarioService) {
        this.controladorUsuario = controladorUsuarioService;
        modeloLista = new DefaultListModel<>();
        listaBeneficiarios.setModel(modeloLista);
        listaBeneficiarios.setCellRenderer(new gui.componentes.TableListCellRenderer(null));//Renderiza la lista con las tablas en su interior
        listaBeneficiarios.setBackground(ColorUtil.getColor("backgroundColor"));
        panelLista.setBackground(ColorUtil.getColor("backgroundColor"));
        new ComponenteComboBox(estadoComboBox);


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

}
