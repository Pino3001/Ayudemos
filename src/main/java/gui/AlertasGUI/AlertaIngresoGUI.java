package gui.AlertasGUI;

import javax.swing.*;
import java.awt.event.*;

public class AlertaIngresoGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel textMensaje;
    private JLabel textAlerta;
    private String error;
    private String mensaje;

    public AlertaIngresoGUI(String error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;

        setContentPane(contentPane);
        setModal(true);
        setSize(400, 250);
        getRootPane().setDefaultButton(buttonOK);
        textMensaje.setText(mensaje);
        textAlerta.setText(error);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

/*    public static void main(String[] args) {
        AlertaIngresoGUI dialog = new AlertaIngresoGUI("Error", "no se pudo");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
