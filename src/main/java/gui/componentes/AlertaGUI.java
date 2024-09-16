package gui.componentes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class AlertaGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOk;
    private JTextArea textoMensaje;
    private JLabel textoAlerta;
    private JPanel panelTitulo;
    private Point initialClick;

    public AlertaGUI(boolean error, String alertaData) {
        setContentPane(contentPane);
        setModal(true);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);  // Mostrar la alerta en el centro de la pantalla
        // Configurar la alerta según el tipo de mensaje (error o éxito)
        if (error) {
            textoAlerta.setText("¡ERROR!");
            textoAlerta.setForeground(Color.WHITE);
            textoMensaje.setText(alertaData);
            textoMensaje.setForeground(ColorUtil.getColor("errorColor"));
            panelTitulo.setBackground(ColorUtil.getColor("errorColor"));
            buttonOk.setBackground(ColorUtil.getColor("errorColor"));
            buttonOk.setForeground(Color.WHITE);
        } else {
            textoAlerta.setText("¡LISTO!");
            textoMensaje.setText(alertaData);
        }


        // Acción al presionar ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Acción al presionar el botón OK
        buttonOk.addActionListener(e -> onCancel());

        // Hacer que el diálogo se pueda mover arrastrándolo
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        contentPane.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Obtener la nueva ubicación del diálogo
                int x = e.getXOnScreen() - initialClick.x;
                int y = e.getYOnScreen() - initialClick.y;

                // Mover el diálogo a la nueva ubicación
                setLocation(x, y);
            }
        });
    }

    private void onCancel() {
        dispose();
    }

    public void mostrarAlerta() {

        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    public static void main(String[] args) {
        AlertaGUI dialog = new AlertaGUI(true, "Ocurrió un problema al insertar");
        dialog.mostrarAlerta();
        System.exit(0);
    }
}
