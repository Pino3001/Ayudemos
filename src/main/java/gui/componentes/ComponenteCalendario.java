package gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ComponenteCalendario extends JDialog {
    private JPanel contentPane;
    private JButton prevMes;
    private JButton prevAnio;
    private JButton nextAnio;
    private JButton nextMes;
    private JLabel textAnio;
    private JLabel textMes;
    private JButton hoyButton;
    private JButton cancelarButton;
    private JLabel dia1;
    private JLabel dia2;
    private JLabel dia3;
    private JLabel dia8;
    private JLabel dia4;
    private JLabel dia5;
    private JLabel dia6;
    private JLabel dia7;
    private JLabel dia9;
    private JLabel dia10;
    private JLabel dia12;
    private JLabel dia13;
    private JLabel dia11;
    private JLabel dia14;
    private JLabel dia15;
    private JLabel dia16;
    private JLabel dia17;
    private JLabel dia18;
    private JLabel dia19;
    private JLabel dia20;
    private JLabel dia21;
    private JLabel dia22;
    private JLabel dia23;
    private JLabel dia24;
    private JLabel dia25;
    private JLabel dia26;
    private JLabel dia27;
    private JLabel dia28;
    private JLabel dia29;
    private JLabel dia30;
    private JLabel dia31;
    private JLabel dia32;
    private JLabel dia33;
    private JLabel dia34;
    private JLabel dia35;
    private JLabel dia36;
    private JLabel dia37;
    private JLabel dia38;
    private JLabel dia39;
    private JLabel dia40;
    private JLabel dia41;
    private JLabel dia42;

    private Calendar calendar;  // Instancia de Calendar para manipular fechas
    private JLabel[] botonesDias;  // Array para los botones de los días
    private String fechaSeleccionada;

    public ComponenteCalendario() {
        this(null);  // Llamar al constructor con fecha nula (sin fecha seleccionada previamente)
    }

    public ComponenteCalendario(String fechaInicial) {
        setContentPane(contentPane);
        setUndecorated(true);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Inicialización de la lógica del calendario
        calendar = new GregorianCalendar();

        botonesDias = new JLabel[]{
                dia1, dia2, dia3, dia4, dia5, dia6, dia7,
                dia8, dia9, dia10, dia11, dia12, dia13, dia14,
                dia15, dia16, dia17, dia18, dia19, dia20, dia21,
                dia22, dia23, dia24, dia25, dia26, dia27, dia28,
                dia29, dia30, dia31, dia32, dia33, dia34, dia35,
                dia36, dia37, dia38, dia39, dia40, dia41, dia42
        };

        // Configuración de la navegación de meses y años
        prevMes.addActionListener(e -> changeMonth(-1));
        nextMes.addActionListener(e -> changeMonth(1));
        prevAnio.addActionListener(e -> changeYear(-1));
        nextAnio.addActionListener(e -> changeYear(1));

        // Añadir ActionListener a cada botón de día
        addDayButtonListeners();

        // Mostrar el mes y año inicial
        updateCalendar();

        addControlButtonListeners();

        // Si se pasa una fecha inicial, configurarla en el calendario
        if (fechaInicial != null) {
            setFechaSeleccionada(fechaInicial);
        }
        // Acción al presionar ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setFechaSeleccionada(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Calendar fechaSeleccionada = Calendar.getInstance();
            fechaSeleccionada.setTime(formatoFecha.parse(fecha));

            // Cambiar el calendario a la fecha seleccionada
            calendar.set(Calendar.YEAR, fechaSeleccionada.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, fechaSeleccionada.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            updateCalendar();

            // Resaltar el día seleccionado
            int day = fechaSeleccionada.get(Calendar.DAY_OF_MONTH);
            for (JLabel boton : botonesDias) {
                if (boton.getText().equals(String.valueOf(day)) && boton.isEnabled()) {
                    boton.setBackground(new Color(104, 218, 104));  // Color para la fecha seleccionada
                    boton.setOpaque(true);
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void changeMonth(int delta) {
        calendar.add(Calendar.MONTH, delta);  // Cambiar el mes según delta (+1 o -1)
        updateCalendar();
    }

    private void changeYear(int delta) {
        calendar.add(Calendar.YEAR, delta);  // Cambiar el año según delta (+1 o -1)
        updateCalendar();
    }

    private void updateCalendar() {
        // Obtener el día y el mes actuales
        Calendar today = new GregorianCalendar();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);
        // Actualizar el año y el mes en los labels
        textAnio.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        textMes.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()));

        // Calcular el primer día del mes y el número de días en el mes actual
        Calendar tempCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;  // Obtener el primer día de la semana (Domingo = 1, Sábado = 7)
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);  // Número de días en el mes

        // Calcular el número de días del mes anterior que deben mostrarse
        Calendar prevMonthCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        int daysInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);  // Número de días en el mes anterior

        // Limpiar todos los botones de los días
        for (JLabel boton : botonesDias) {
            boton.setText("");
            boton.setOpaque(false);
            boton.setEnabled(false);
        }

        // Mostrar los días del mes anterior
        for (int i = firstDayOfWeek - 1; i >= 0; i--) {
            botonesDias[i].setText(String.valueOf(daysInPrevMonth--));
            botonesDias[i].setEnabled(false);
        }

        // Mostrar los días del mes actual
        for (int day = 1, buttonIndex = firstDayOfWeek; day <= daysInMonth; day++, buttonIndex++) {
            botonesDias[buttonIndex].setText(String.valueOf(day));
            botonesDias[buttonIndex].setEnabled(true);
            botonesDias[buttonIndex].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Verificar si el día actual es el mismo que el día de hoy y si el mes también coincide
            if (day == todayDay && calendar.get(Calendar.MONTH) == todayMonth && calendar.get(Calendar.YEAR) == todayYear) {
                botonesDias[buttonIndex].setBackground(new Color(218, 104, 104)); // Ejemplo: fondo rojo
                botonesDias[buttonIndex].setOpaque(true);
            }
        }
        // Mostrar los días del mes siguiente
        for (int i = daysInMonth + firstDayOfWeek, day = 1; i < botonesDias.length; i++, day++) {
            botonesDias[i].setText(String.valueOf(day));
            botonesDias[i].setEnabled(false);
        }
    }

    private void addDayButtonListeners() {
        for (JLabel boton : botonesDias) {
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (boton.isEnabled()) {
                        String dia = boton.getText();
                        String mes = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
                        String anio = String.valueOf(calendar.get(Calendar.YEAR));
                        fechaSeleccionada = String.format("%02d/%02d/%04d", Integer.parseInt(dia), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
                        dispose();  // Cerrar el diálogo cuando se selecciona una fecha
                    }
                }
            });
        }
    }

    private void addControlButtonListeners() {
        // Acción para el botón "Hoy"
        hoyButton.addActionListener(e -> {
            // Crear una nueva instancia de Calendar con la fecha actual
            Calendar today = new GregorianCalendar();

            // Actualizar la instancia de `calendar` con la nueva fecha
            calendar.set(Calendar.YEAR, today.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, today.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));

            // Mostrar el mes y año actuales en el calendario
            updateCalendar();

            // Resaltar el día actual
            int day = today.get(Calendar.DAY_OF_MONTH);
            for (JLabel boton : botonesDias) {
                if (boton.getText().equals(String.valueOf(day)) && boton.isEnabled()) {
                    boton.setBackground(new Color(104, 218, 104));  // Color para la fecha seleccionada
                    boton.setOpaque(true);
                    break;
                }
            }

            // Establecer la fecha seleccionada
            int month = calendar.get(Calendar.MONTH) + 1;  // Los meses en Calendar comienzan desde 0
            int year = calendar.get(Calendar.YEAR);
            fechaSeleccionada = String.format("%02d/%02d/%04d", day, month, year);


            // Cerrar el diálogo
            dispose();
            System.out.println("Fecha de hoy: " + fechaSeleccionada);  // Puedes eliminar este print si no lo necesitas
        });

        // Acción para el botón "Cancelar"
        cancelarButton.addActionListener(e -> {
            // Cerrar el modal de diálogo
            fechaSeleccionada = null;
            dispose();
        });
    }

    public String getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    // Método para mostrar el calendario
    public void mostrarCalendario(int x, int y) {
        this.pack();
        this.setLocation(x, y);
        this.setVisible(true);  // Mostrar el modal
    }

    // Método estático para mostrar el componente y obtener la fecha seleccionada
    public String mostrarYObtenerFechaSeleccionada(int x, int y) {
        mostrarCalendario(x, y);  // Mostrar el calendario
        return getFechaSeleccionada();  // Devolver la fecha seleccionada
    }

    public static void main(String[] args) {
        ComponenteCalendario calendario = new ComponenteCalendario("12/3/2024");
        calendario.pack();
        calendario.setLocationRelativeTo(null);
        calendario.setVisible(true);
        String fechaSeleccionada = calendario.getFechaSeleccionada();
        System.out.println("Fecha seleccionada: " + fechaSeleccionada);
    }
}
