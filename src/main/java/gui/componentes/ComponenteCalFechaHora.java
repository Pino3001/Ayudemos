package gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ComponenteCalFechaHora extends JDialog {
    private JLabel textAnio;
    private JButton prevAnio;
    private JButton nextAnio;
    private JButton prevMes;
    private JLabel textMes;
    private JButton nextMes;
    private JLabel dia1;
    private JLabel dia8;
    private JLabel dia15;
    private JLabel dia22;
    private JLabel dia29;
    private JLabel dia36;
    private JLabel dia2;
    private JLabel dia3;
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
    private JLabel dia16;
    private JLabel dia17;
    private JLabel dia18;
    private JLabel dia19;
    private JLabel dia20;
    private JLabel dia21;
    private JLabel dia23;
    private JLabel dia24;
    private JLabel dia25;
    private JLabel dia26;
    private JLabel dia27;
    private JLabel dia28;
    private JLabel dia30;
    private JLabel dia31;
    private JLabel dia32;
    private JLabel dia33;
    private JLabel dia34;
    private JLabel dia35;
    private JLabel dia37;
    private JLabel dia38;
    private JLabel dia39;
    private JLabel dia40;
    private JLabel dia41;
    private JLabel dia42;
    private JButton hoyButton;
    private JButton cancelarButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JPanel background;

    private Calendar calendar;  // Instancia de Calendar para manipular fechas
    private JLabel[] botonesDias;  // Array para los botones de los días
    private String fechaSeleccionada;

    public ComponenteCalFechaHora() {
        this(null);
    }

    public ComponenteCalFechaHora(String fechaHoraInicial) {
        setContentPane(background);
        setUndecorated(true);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        calendar = new GregorianCalendar();
        comportamientoSpinner();

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
        if (fechaHoraInicial != null && !fechaHoraInicial.isEmpty()) {
            setFechaSeleccionada(fechaHoraInicial);
        } else {
            // Establecer la hora actual
            spinner1.setValue(calendar.get(Calendar.HOUR_OF_DAY));
            spinner2.setValue(calendar.get(Calendar.MINUTE));
            updateCalendar();
        }
        // Acción al presionar ESCAPE
        background.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        addControlButtonListeners();
    }


    private void setFechaSeleccionada(String fechaHora) {
        try {
            // Crear un formato de fecha que incluya la hora y minutos
            DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(fechaHora, formatoFechaHora);

            // Establecer la fecha en el calendario
            calendar.set(Calendar.YEAR, fecha.getYear());
            calendar.set(Calendar.MONTH, fecha.getMonthValue() - 1); // Calendar.MONTH es 0-based
            calendar.set(Calendar.DAY_OF_MONTH, fecha.getDayOfMonth());
            calendar.set(Calendar.HOUR_OF_DAY, fecha.getHour());
            calendar.set(Calendar.MINUTE, fecha.getMinute());

            spinner1.setValue(fecha.getHour());
            spinner2.setValue(fecha.getMinute());

            // Actualizar el calendario con la fecha establecida
            updateCalendar();

            // Resaltar el día seleccionado
            int day = fecha.getDayOfMonth();
            for (JLabel boton : botonesDias) {
                if (boton.getText().equals(String.valueOf(day)) && boton.isEnabled()) {
                    boton.setBackground(new Color(104, 218, 104));  // Color para la fecha seleccionada
                    boton.setOpaque(true);
                    break;
                }
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();  // Manejo de errores en caso de formato incorrecto
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
        Calendar today = new GregorianCalendar();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);
        spinner1.setValue(calendar.get(Calendar.HOUR_OF_DAY));
        spinner2.setValue(calendar.get(Calendar.MINUTE));

        textAnio.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        textMes.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()));

        Calendar tempCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;  // Obtener el primer día de la semana (Domingo = 1, Sábado = 7)
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);  // Número de días en el mes

        Calendar prevMonthCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        int daysInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);  // Número de días en el mes anterior

        for (JLabel boton : botonesDias) {
            boton.setText("");
            boton.setOpaque(false);
            boton.setEnabled(false);
        }

        for (int i = firstDayOfWeek - 1; i >= 0; i--) {
            botonesDias[i].setText(String.valueOf(daysInPrevMonth--));
            botonesDias[i].setEnabled(false);
        }

        for (int day = 1, buttonIndex = firstDayOfWeek; day <= daysInMonth; day++, buttonIndex++) {
            botonesDias[buttonIndex].setText(String.valueOf(day));
            botonesDias[buttonIndex].setEnabled(true);
            botonesDias[buttonIndex].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (day == todayDay && calendar.get(Calendar.MONTH) == todayMonth && calendar.get(Calendar.YEAR) == todayYear) {
                botonesDias[buttonIndex].setBackground(new Color(218, 104, 104));
                botonesDias[buttonIndex].setOpaque(true);
            }
        }

        int nextMonthDay = 1;
        for (int i = firstDayOfWeek + daysInMonth; i < botonesDias.length; i++) {
            botonesDias[i].setText(String.valueOf(nextMonthDay++));
            botonesDias[i].setEnabled(false);
        }

        background.revalidate();
        background.repaint();
    }

    private void addDayButtonListeners() {
        for (JLabel boton : botonesDias) {
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (boton.isEnabled()) {
                        int diaSeleccionado = Integer.parseInt(boton.getText());
                        int mesSeleccionado = calendar.get(Calendar.MONTH);
                        int anioSeleccionado = calendar.get(Calendar.YEAR);
                        Calendar fechaSeleccionadaCal = new GregorianCalendar(anioSeleccionado, mesSeleccionado, diaSeleccionado);
                        Date fechaSeleccionadaDate = fechaSeleccionadaCal.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        fechaSeleccionada = sdf.format(fechaSeleccionadaDate);

                        fechaSeleccionada = agregarHoraAFecha(fechaSeleccionada);
                        dispose();
                        System.out.println(fechaSeleccionada);
                    }
                }
            });
        }
    }

    private String agregarHoraAFecha(String fecha) {
        int horas = (int) spinner1.getValue();
        int minutos = (int) spinner2.getValue();
        return String.format("%s %02d:%02d", fecha, horas, minutos);
    }

    private void addControlButtonListeners() {
        hoyButton.addActionListener(e -> {
            Calendar hoy = new GregorianCalendar();
            calendar.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, hoy.get(Calendar.HOUR_OF_DAY));  // Establecer la hora actual
            calendar.set(Calendar.MINUTE, hoy.get(Calendar.MINUTE));  // Establecer los minutos actuales

            // Actualizar los valores de los spinners de hora y minuto
            spinner1.setValue(hoy.get(Calendar.HOUR_OF_DAY));
            spinner2.setValue(hoy.get(Calendar.MINUTE));

            updateCalendar();

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            fechaSeleccionada = String.format("%02d/%02d/%04d", day, month, year);
            fechaSeleccionada = agregarHoraAFecha(fechaSeleccionada);
            dispose();
            System.out.println("Fecha de hoy: " + fechaSeleccionada);
        });

        cancelarButton.addActionListener(e -> {
            fechaSeleccionada = null;
            dispose();
        });
    }

    private void comportamientoSpinner() {
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel(0, 0, 59, 1);

        spinner1.setModel(modeloSpinner);
        spinner2.setModel(modeloSpinner2);

        this.spinner1.getEditor().getComponent(0).setBackground(new Color(230, 224, 230));
        JComponent editor = spinner1.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setCaretColor(new Color(9, 35, 48));
        }
        for (Component component : spinner1.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(new Color(9, 35, 48));
                button.setForeground(new Color(255, 255, 255));
            }
        }

        this.spinner2.getEditor().getComponent(0).setBackground(new Color(230, 224, 230));
        JComponent edit = spinner2.getEditor();
        if (edit instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) edit).getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setCaretColor(new Color(9, 35, 48));
        }
        for (Component component : spinner2.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(new Color(9, 35, 48));
                button.setForeground(new Color(255, 255, 255));
            }
        }
    }

    public String getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public String mostrarYObtenerFechaHora(int x, int y) {
        pack();
        setLocation(x, y);
        setVisible(true);
        return getFechaSeleccionada();
    }

    public static void main(String[] args) {
        ComponenteCalFechaHora dialog = new ComponenteCalFechaHora("12/08/2024 23:12");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
