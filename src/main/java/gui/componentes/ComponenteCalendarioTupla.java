package gui.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class ComponenteCalendarioTupla extends JDialog {
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

    private JLabel[] botonesDias;
    private Calendar calendar;
    private String fechaInicio;
    private String fechaFin;
    private boolean primeraSeleccion = true;
    private Set<String> fechasSeleccionadas = new HashSet<>();


    public ComponenteCalendarioTupla() {
        setContentPane(contentPane);
        setUndecorated(true);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        calendar = new GregorianCalendar();
        // Cargar el panel con los botones de los dias del calendario
        botonesDias = new JLabel[]{
                dia1, dia2, dia3, dia4, dia5, dia6, dia7,
                dia8, dia9, dia10, dia11, dia12, dia13, dia14,
                dia15, dia16, dia17, dia18, dia19, dia20, dia21,
                dia22, dia23, dia24, dia25, dia26, dia27, dia28,
                dia29, dia30, dia31, dia32, dia33, dia34, dia35,
                dia36, dia37, dia38, dia39, dia40, dia41, dia42
        };

        prevMes.addActionListener(e -> changeMonth(-1));
        nextMes.addActionListener(e -> changeMonth(1));
        prevAnio.addActionListener(e -> changeYear(-1));
        nextAnio.addActionListener(e -> changeYear(1));

        // Acción al presionar ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        addDayButtonListeners();
        updateCalendar();
        addControlButtonListeners();
    }

    private void changeMonth(int delta) {
        calendar.add(Calendar.MONTH, delta);
        updateCalendar();
    }

    private void changeYear(int delta) {
        calendar.add(Calendar.YEAR, delta);
        updateCalendar();
    }

    private void updateCalendar() {
        Calendar today = new GregorianCalendar();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);

        textAnio.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        textMes.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()));

        Calendar tempCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar prevMonthCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        int daysInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (JLabel boton : botonesDias) {
            boton.setText("");
            boton.setOpaque(false);
            boton.setEnabled(false);
            boton.setBackground(ColorUtil.getColor("backgroundColor"));
            boton.setForeground(ColorUtil.getColor("primaryColor"));
        }

        for (int i = firstDayOfWeek - 1; i >= 0; i--) {
            botonesDias[i].setText(String.valueOf(daysInPrevMonth--));
            botonesDias[i].setEnabled(false);
        }

        for (int day = 1, buttonIndex = firstDayOfWeek; day <= daysInMonth; day++, buttonIndex++) {
            botonesDias[buttonIndex].setText(String.valueOf(day));
            botonesDias[buttonIndex].setEnabled(true);
            botonesDias[buttonIndex].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            String fechaKey = String.format("%02d/%02d/%04d", day, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
            if (fechasSeleccionadas.contains(fechaKey)) {
                botonesDias[buttonIndex].setBackground(ColorUtil.getColor("primaryColor"));
                botonesDias[buttonIndex].setForeground(ColorUtil.getColor("textButtonColor"));
                botonesDias[buttonIndex].setOpaque(true);
            } else if (day == todayDay && calendar.get(Calendar.MONTH) == todayMonth && calendar.get(Calendar.YEAR) == todayYear) {
                botonesDias[buttonIndex].setBackground(new Color(218, 104, 104));
                botonesDias[buttonIndex].setOpaque(true);
            }
        }

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
                        String fechaSeleccionada = String.format("%02d/%02d/%04d", Integer.parseInt(dia), calendar.get(Calendar.MONTH) + 1, Integer.parseInt(anio));

                        Calendar fechaSeleccionadaCal = new GregorianCalendar(
                                Integer.parseInt(anio),
                                Integer.parseInt(mes) - 1,
                                Integer.parseInt(dia)
                        );

                        if (primeraSeleccion) {
                            fechaInicio = fechaSeleccionada;
                            fechasSeleccionadas.add(fechaSeleccionada);
                            primeraSeleccion = false;
                            boton.setBackground(ColorUtil.getColor("primaryColor"));
                            boton.setForeground(ColorUtil.getColor("textButtonColor"));
                            boton.setOpaque(true);
                        } else {
                            String[] fechaInicioParts = fechaInicio.split("/");
                            Calendar fechaInicioCal = new GregorianCalendar(
                                    Integer.parseInt(fechaInicioParts[2]),
                                    Integer.parseInt(fechaInicioParts[1]) - 1,
                                    Integer.parseInt(fechaInicioParts[0])
                            );

                            if (fechaSeleccionadaCal.before(fechaInicioCal)) {
                                new AlertaGUI(true, "La fecha seleccionada debe ser mayor\n o igual a la primera fecha.").mostrarAlerta();
                            } else {
                                fechaFin = fechaSeleccionada;
                                fechasSeleccionadas.add(fechaSeleccionada);
                                dispose();
                            }
                        }
                    }
                }
            });
        }
    }

    private void addControlButtonListeners() {
        hoyButton.addActionListener(e -> {
            Calendar today = new GregorianCalendar();
            String todayStr = String.format("%02d/%02d/%04d", today.get(Calendar.DAY_OF_MONTH),
                    today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR));
            fechaInicio = todayStr;
            fechaFin = todayStr;
            fechasSeleccionadas.add(todayStr);
            dispose();
        });

        cancelarButton.addActionListener(e -> {
            fechaInicio = null;
            fechaFin = null;
            fechasSeleccionadas.clear();
            dispose();
        });
    }

    public String[] getFechasSeleccionadas() {
        return new String[]{fechaInicio, fechaFin};
    }

    public void mostrarCalendario(int x, int y) {
        this.pack();
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public String[] mostrarYObtenerFechasSeleccionadas(int x, int y) {
        mostrarCalendario(x, y);
        return getFechasSeleccionadas();
    }

    public static void main(String[] args) {
        ComponenteCalendarioTupla calendario = new ComponenteCalendarioTupla();
        calendario.pack();
        calendario.setLocationRelativeTo(null);
        calendario.setVisible(true);
        String[] fechasSeleccionadas = calendario.getFechasSeleccionadas();
        System.out.println("Fechas seleccionadas: " + fechasSeleccionadas[0] + " a " + fechasSeleccionadas[1]);
    }
}
