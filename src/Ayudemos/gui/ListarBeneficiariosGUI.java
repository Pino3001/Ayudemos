package Ayudemos.gui;

import javax.swing.*;

public class ListarBeneficiariosGUI extends JFrame {
    private JPanel background;
    private JPanel paneltitulo;
    private JPanel panelLista;
    private JList listBeneficiarios;
    private JScrollPane scroll;

    public ListarBeneficiariosGUI()  {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.background = new JPanel();
        setContentPane(background);
        setSize(700,500);
    }
}
