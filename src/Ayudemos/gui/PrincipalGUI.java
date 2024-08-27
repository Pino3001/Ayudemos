package Ayudemos.gui;

import Ayudemos.interfaces.IAltaDonacion;
import Ayudemos.interfaces.IAltaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI extends JFrame {
    private IAltaUsuario altaUsuario;
    private IAltaDonacion iAltaDonacion;
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

    public PrincipalGUI(IAltaUsuario altaUsuario, IAltaDonacion iAltaDonacion) {
        this.altaUsuario = altaUsuario;
        this.iAltaDonacion = iAltaDonacion;
        altaDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDonacionGUI altaDonacionGUI = new AltaDonacionGUI(iAltaDonacion);
                altaDonacionGUI.setVisible(true);
            }

        });
        altaUsuarioB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaUsuarioUI altaUsuarioUI = new AltaUsuarioUI(altaUsuario);
                altaUsuarioUI.setVisible(true);
            }
        });
        modificarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDonacionGUI modificarDonacionGUI = new ModificarDonacionGUI(iAltaDonacion);
                modificarDonacionGUI.setVisible(true);
            }
        });
        modificarUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarUsuarioGUI modificarUsuarioGUI = new ModificarUsuarioGUI(altaUsuario);
                modificarUsuarioGUI.setVisible(true);
            }
        });
        listarBeneficiarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosGUI listarBeneficiariosGUI = new ListarBeneficiariosGUI(altaUsuario);
                listarBeneficiariosGUI.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100,700);
    }
}
