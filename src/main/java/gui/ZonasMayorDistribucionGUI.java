package gui;

import interfaces.IControladorDistribucion;

import javax.swing.*;

public class ZonasMayorDistribucionGUI extends JFrame {
    private final IControladorDistribucion iControladorDistribucion;
    private JPanel background;
    private JTextField textFechaPrepara;
    private JButton buttonCalendarioPrepara;
    private JList listaReporte;
    private JButton buttonAceptarDonacion;

    public ZonasMayorDistribucionGUI(IControladorDistribucion iControladorDistribucion) {
        this.iControladorDistribucion = iControladorDistribucion;
    }

    private void createUIComponents() {
    }
}
