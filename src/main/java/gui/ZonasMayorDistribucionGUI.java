package gui;

import datatypes.DtDistribucion;
import gui.componentes.ComponenteCalendarioTupla;
import gui.componentes.ComponenteTextField;
import interfaces.IControladorDistribucion;
import types.Barrio;

import javax.swing.*;
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
    private JList listaReporte;
    private JButton buttonGenerarReporte;

    // Formato de fecha
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public ZonasMayorDistribucionGUI(IControladorDistribucion iControladorDistribucion) {
        this.iControladorDistribucion = iControladorDistribucion;

        new ComponenteTextField(textCalendarioInicio, "");
        new ComponenteTextField(textCalendarioFin, "");

        buttonGenerarReporte.addActionListener(e -> {
            // Parsear la cadena a un objeto LocalDateTime.
            LocalDate fechaInicio = LocalDate.parse(textCalendarioInicio.getText(), formatter);
            LocalDate fechaFin = LocalDate.parse(textCalendarioFin.getText(), formatter);

            // Generamos un mapa con los datos del reporte.
            Map<Barrio, List<DtDistribucion>> reporte = iControladorDistribucion.obtenerReporteZona(fechaInicio, fechaFin);
            int i = 0;
            // Convertir la lista de beneficiarios a un array de Strings para mostrar en el JList
            String[] stringsZonas = new String[reporte.size()];
            for (Map.Entry<Barrio, List<DtDistribucion>> entry : reporte.entrySet()) {
                Barrio barrio = entry.getKey();
                List<DtDistribucion> distribuciones = entry.getValue();
                int cantDistribuciones = distribuciones.size();
                // "Escribimos" el nombre de la zona de la iteración en la posicion "i" del array.
                // Luego le concatenaremos mas.
                stringsZonas[i] = reporte.keySet().toArray()[i].toString();

                int cantBeneficiarios = 0;
                int lastIdUsuario = 0;

                for (DtDistribucion distribucion : distribuciones) {
                    if (distribucion.getIdUsuario() != lastIdUsuario) {
                        cantBeneficiarios++;
                    }
                    lastIdUsuario = distribucion.getIdUsuario();
                }
                stringsZonas[i] = stringsZonas[i] + ", " + cantDistribuciones + " distribuciones, " + cantBeneficiarios + " beneficiarios";
                i++;
            }

            listaReporte.setListData(stringsZonas);
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

    private void createUIComponents() {
        background = new JPanel();
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(background);
    }

    public void setPosicion(int x, int y) {
        this.setLocation(x, y);
    }


}
