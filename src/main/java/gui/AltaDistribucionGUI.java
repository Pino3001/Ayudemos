package gui;

import gui.componentes.ComponenteComboBox;
import gui.componentes.ComponenteTextField;

import javax.swing.*;

public class AltaDistribucionGUI extends JFrame {

    private JButton button1;
    private JButton button2;
    private JComboBox comboBeneficiario;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel background;
    private JComboBox comboDonacion;
    private JComboBox comboRepartidor;
    private JComboBox comboEstadoDistri;

    public AltaDistribucionGUI() {
        new ComponenteTextField(textField1,"");
        new ComponenteTextField(textField2,"");
        new ComponenteComboBox(comboDonacion);
        new ComponenteComboBox(comboRepartidor);
        new ComponenteComboBox(comboEstadoDistri);
        new ComponenteComboBox(comboBeneficiario);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AltaDistribucionGUI");
        frame.setContentPane(new AltaDistribucionGUI().background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 500);
    }
}
