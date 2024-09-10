package gui;

import interfaces.IAltaDistribucion;

import javax.swing.*;

public class ZonasMayorDistribucionGUI extends JFrame {
    private final IAltaDistribucion iAltaDistribucion;
    private JPanel background;
    private JTextField textFechaPrepara;
    private JButton buttonCalendarioPrepara;
    private JList listaReporte;
    private JButton buttonAceptarDonacion;

    public ZonasMayorDistribucionGUI(IAltaDistribucion iAltaDistribucion) {
        this.iAltaDistribucion = iAltaDistribucion;
    }
    private void createUIComponents() {
    }
}
