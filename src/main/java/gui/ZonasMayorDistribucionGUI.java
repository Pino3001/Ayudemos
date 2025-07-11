package gui;

import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import datatypes.DtUsuario;
import excepciones.FormatoFechaIExeption;
import gui.componentes.*;
import interfaces.IControladorDistribucion;
import types.Barrio;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ZonasMayorDistribucionGUI extends JFrame {
    private final IControladorDistribucion iControladorDistribucion;
    private JPanel background;
    private JButton buttonCalendario;
    private JTextField textCalendarioFin;
    private JTextField textCalendarioInicio;
    private JList<JTable> listaReporte;
    private JButton buttonGenerarReporte;
    private DefaultListModel<JTable> modeloLista;

    // Formato de fecha
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public ZonasMayorDistribucionGUI(IControladorDistribucion iControladorDistribucion) {
        this.iControladorDistribucion = iControladorDistribucion;
        modeloLista = new DefaultListModel<>();
        listaReporte.setModel(modeloLista);
        listaReporte.setCellRenderer(new gui.componentes.TableListCellRenderer(null));//Renderiza la lista con las tablas en su interior
        new ComponenteTextField(textCalendarioInicio, "");
        new ComponenteTextField(textCalendarioFin, "");
        listaReporte.setBackground(ColorUtil.getColor("backgroundColor"));

        buttonGenerarReporte.addActionListener(e -> {
            // Parsear la cadena a un objeto LocalDateTime.
            try {
                if (textCalendarioInicio.getText().isEmpty() || textCalendarioFin.getText().isEmpty()) {
                    throw new FormatoFechaIExeption("Ingrese un rango de fechas valido");
                }else {
                    LocalDate fechaInicio = LocalDate.parse(textCalendarioInicio.getText(), formatter);
                    LocalDate fechaFin = LocalDate.parse(textCalendarioFin.getText(), formatter);

                    // Generamos un mapa con los datos del reporte.
                    List<DtReporteZona> reporte = iControladorDistribucion.obtenerReporteZona(fechaInicio, fechaFin);
                    // Rellenamos la lista con el resultado.
                    cargarElementos(reporte);
                }
            }catch (Exception ex){
                new AlertaGUI(true, ex.getMessage()).mostrarAlerta();
            }
        });

        // Acción del botón para el calendario, para seleccionar la fecha de entrega
        buttonCalendario.addActionListener(e -> {
            if (textCalendarioInicio == null) {
                System.out.println("textCalendarioFin es null");
                return;
            }
            // Crear una instancia del componente de calendario
            ComponenteCalendarioTupla calendario = new ComponenteCalendarioTupla();
            // Calcular la posición del calendario para que aparezca justo debajo del botón y el textField
            int x = textCalendarioInicio.getLocationOnScreen().x;
            int y = textCalendarioInicio.getLocationOnScreen().y + buttonCalendario.getHeight();

            // Mostrar el calendario y obtener la fecha seleccionada
            String[] fechasSeleccionadas = calendario.mostrarYObtenerFechasSeleccionadas(x, y);

            // Verificar si se seleccionó una fecha o si se canceló la selección
            if (fechasSeleccionadas != null && fechasSeleccionadas.length > 0) {
                // Actualizar un campo de texto con la fecha seleccionada
                textCalendarioInicio.setText(fechasSeleccionadas[0]);
                textCalendarioFin.setText(fechasSeleccionadas[1]);
            }
        });

    }

    // Función para cargar los elementos en la lista
    private void cargarElementos(List<DtReporteZona> reporte) {
        modeloLista.clear(); // Limpiar elementos existentes
        if (reporte.isEmpty()) {// Si no existe ninguna distribucion creo un mensaje
            agregarTablaConMensaje("No hay distribuciones en la zona");
        } else {
            for (DtReporteZona reporteZona: reporte) {// Recorre la lista de distribuciones y crea una tabla para cada distribucion
                JTable jTable = crearTablaDistribucion(reporteZona);
                modeloLista.addElement(jTable); // Agregar la tabla al modelo de lista
                modeloLista.addElement(new JTable(new Object[][] {{"-------------------" +
                        "--------------------------------" +
                        "--------------------------------" +
                        "--"}}, new Object[]{"--"}));
            }
        }
    }

    //crea una tabla para cada Beneficiario
    private JTable crearTablaDistribucion(DtReporteZona reporteZona) {
        String[] columnNames = {"", ""};//Cabezal vacio para la tabla, no se va a utilizar pero para crear la tabla es necesario
        Object[][] data = {//Datos que tendra la tabla, izquierda el tipo de dato y derecha el dato correspondiente
                {"Zona", reporteZona.getBarrio()},
                {"Cantidad Distribuciones", reporteZona.getTotalDistribuciones()},
                {"Cantidad Beneficiarios", reporteZona.getTotalBeneficiarios()},
        };

        JTable jTable = new JTable(data, columnNames);
        jTable.setBackground(ColorUtil.getColor("backgroundColor")); // Color de fondo de toda la tabla
        jTable.setForeground(ColorUtil.getColor("primaryColor"));
        jTable.setFillsViewportHeight(true);
        return jTable;
    }

    //Si la lista es null se crea un mensaje predeterminado
    private void agregarTablaConMensaje(String mensaje) {
        String[] columnNames = {"Información"};
        Object[][] data = {{mensaje}};
        JTable jTable = new JTable(data, columnNames);
        modeloLista.addElement(jTable);
    }

    private void createUIComponents() {
        background = new JPanel();
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(background);
    }

    public void setPosicion(int x, int y) {
        this.setLocation(x, y);
    }


}
