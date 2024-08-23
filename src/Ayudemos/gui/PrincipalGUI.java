package Ayudemos.gui;

import Ayudemos.interfaces.IAltaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI extends JFrame {
    private IAltaUsuario altaUsuario;
    private JPanel principalPanel;
    private JPanel tituloPanel;
    private JPanel botoneraPanel;
    private JLabel titulo;
    private JButton altaUsuarioB;
    private JButton modificarUsr;
    private JButton listarBeneficiarios;
    private JButton listarBenefiZona;
    private JButton listarBenefiEstado;
    private JButton altadistribucion;
    private JLabel titulodistribucion;
    private JLabel tituloDistribucion;
    private JButton modificarDistribucion;
    private JButton listarDistriXZona;
    private JButton listarDistriXEstado;
    private JButton altaDonacion;
    private JButton modificarDonacion;
    private JLabel tituloReportes;
    private JLabel tituloDonaciones;
    private JButton mayoresDistribuciones;

    public PrincipalGUI(IAltaUsuario altaUsuario) {
        this.altaUsuario = altaUsuario;
        altaDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDonacionGUI altaDonacionGUI = new AltaDonacionGUI();
                altaDonacionGUI.setVisible(true);
            }

        });
        altaUsuarioB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroUsuarioGUI registroUsuarioGUI = new RegistroUsuarioGUI(altaUsuario);
                registroUsuarioGUI.setVisible(true);
            }
        });
        modificarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDonacionGUI modificarDonacionGUI = new ModificarDonacionGUI();
                modificarDonacionGUI.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100,700);
    }
}
