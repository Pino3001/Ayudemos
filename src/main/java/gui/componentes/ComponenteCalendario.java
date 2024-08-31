package gui.componentes;

import javax.swing.*;
import java.awt.*;
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
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JButton boton10;
    private JButton boton11;
    private JButton boton12;
    private JButton boton13;
    private JButton boton14;
    private JButton boton15;
    private JButton boton16;
    private JButton boton17;
    private JButton boton18;
    private JButton boton19;
    private JButton boton20;
    private JButton boton21;
    private JButton boton22;
    private JButton boton23;
    private JButton boton24;
    private JButton boton25;
    private JButton boton26;
    private JButton boton27;
    private JButton boton28;
    private JButton boton29;
    private JButton boton30;
    private JButton boton31;
    private JButton boton32;
    private JButton boton33;
    private JButton boton34;
    private JButton boton35;
    private JButton boton36;
    private JButton boton37;
    private JButton boton38;
    private JPanel panelBotondias;
    private JButton boton39;
    private JButton boton40;
    private JButton boton41;
    private JButton boton42;
    private JLabel textDiaDomingo;
    private JLabel textDiaLunes;
    private JLabel textDiaMartes;
    private JLabel textDiaMiercoles;
    private JLabel textdiaJueves;
    private JLabel textDiaViernes;
    private JLabel textdiaSabado;
    private JButton hoyButton;
    private JButton cancelarButton;

    private Calendar calendar;  // Instancia de Calendar para manipular fechas
    private JButton[] botonesDias;  // Array para los botones de los días
    private String fechaSeleccionada;

    public ComponenteCalendario() {
        setContentPane(contentPane);
        setUndecorated(true);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Inicialización de la lógica del calendario
        calendar = new GregorianCalendar();
        botonesDias = new JButton[]{
                boton1, boton2, boton3, boton4, boton5, boton6, boton7,
                boton8, boton9, boton10, boton11, boton12, boton13, boton14,
                boton15, boton16, boton17, boton18, boton19, boton20, boton21,
                boton22, boton23, boton24, boton25, boton26, boton27, boton28,
                boton29, boton30, boton31, boton32, boton33, boton34, boton35,
                boton36, boton37, boton38, boton39, boton40, boton41, boton42
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
        for (JButton boton : botonesDias) {
            boton.setText("");
            boton.setEnabled(false);
        }

        // Mostrar los días del mes anterior
        for (int i = firstDayOfWeek - 1; i >= 0; i--) {
            botonesDias[i].setText(String.valueOf(daysInPrevMonth--));
            botonesDias[i].setEnabled(false);  // Puedes dejar estos botones deshabilitados o habilitarlos para otras funcionalidades
        }

        // Mostrar los días del mes actual
        for (int day = 1, buttonIndex = firstDayOfWeek; day <= daysInMonth; day++, buttonIndex++) {
            botonesDias[buttonIndex].setText(String.valueOf(day));
            botonesDias[buttonIndex].setEnabled(true);
        }

        // Mostrar los días del mes siguiente
        int nextMonthDay = 1;
        for (int i = firstDayOfWeek + daysInMonth; i < botonesDias.length; i++) {
            botonesDias[i].setText(String.valueOf(nextMonthDay++));
            botonesDias[i].setEnabled(false);  // Puedes dejar estos botones deshabilitados o habilitarlos para otras funcionalidades
        }

        // Actualizar la interfaz gráfica
        panelBotondias.revalidate();
        panelBotondias.repaint();
    }

    private void addDayButtonListeners() {
        // Agregar ActionListener a cada botón de día
        for (JButton boton : botonesDias) {
            boton.addActionListener(e -> {
                if (!boton.getText().isEmpty() && boton.isEnabled()) {
                    // Restaurar el color de fondo y texto de todos los botones antes de cambiar el color del botón seleccionado
                    for (JButton b : botonesDias) {
                        b.setBackground(null);  // Fondo predeterminado
                        b.setForeground(new Color(9, 35, 48));  // Texto en negro predeterminado
                    }

                    // Cambiar el color de fondo y texto del botón seleccionado
                    boton.setBackground(boton.getForeground());
                    boton.setForeground(Color.WHITE);  // Cambiar el color del texto a blanco para que sea visible
                    boton.setBorderPainted(false);

                    // Obtener el día, mes y año seleccionados
                    int day = Integer.parseInt(boton.getText());
                    int month = calendar.get(Calendar.MONTH) + 1;  // Los meses en Calendar comienzan desde 0
                    int year = calendar.get(Calendar.YEAR);

                    // Mostrar o devolver la fecha seleccionada
                    fechaSeleccionada = String.format("%02d/%02d/%04d", day, month, year);
                    dispose();
                    System.out.println("Fecha seleccionada: " + day + "/" + month + "/" + year);
                }
            });
        }
    }

    private void addControlButtonListeners() {
        // Acción para el botón "Hoy"
        hoyButton.addActionListener(e -> {
            // Crear una nueva instancia de Calendar con la fecha actual
            Calendar hoy = new GregorianCalendar();

            // Actualizar la instancia de `calendar` con la nueva fecha
            calendar.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));

            // Mostrar el mes y año actuales en el calendario
            updateCalendar();

            // Establecer la fecha seleccionada
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;  // Los meses en Calendar comienzan desde 0
            int year = calendar.get(Calendar.YEAR);
            fechaSeleccionada = String.format("%02d/%02d/%04d", day, month, year);
            dispose();
            System.out.println("Fecha de hoy: " + fechaSeleccionada);
        });

        // Acción para el botón "Cancelar"
        cancelarButton.addActionListener(e -> {
            // Cerrar el modal de diálogo
            fechaSeleccionada = null;
            dispose();
        });
    }

    // Método para obtener la fecha seleccionada
    public String getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    // Método estático para mostrar el componente y obtener la fecha seleccionada
    public String mostrarYObtenerFechaSeleccionada(int x, int y) {
        ComponenteCalendario dialog = new ComponenteCalendario();
        dialog.pack();
        dialog.setLocation(x,y);
        dialog.setVisible(true);  // Mostrar el modal
        return dialog.getFechaSeleccionada();  // Devolver la fecha seleccionada
    }

    public static void main(String[] args) {
        ComponenteCalendario dialog = new ComponenteCalendario();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
