package gui;

import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI extends JFrame {
    private IAltaUsuario iAltaUsuario;
    private IAltaDonacion iAltaDonacion;
    private IAltaDistribucion iAltaDistribucion;
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

    public PrincipalGUI(IAltaUsuario altaUsuario, IAltaDonacion iAltaDonacion, IAltaDistribucion iAltaDistribucion) {
        this.iAltaUsuario = altaUsuario;
        this.iAltaDonacion = iAltaDonacion;
        this.iAltaDistribucion = iAltaDistribucion;
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
                AltaUsuarioUI altaUsuarioUI = new AltaUsuarioUI(iAltaUsuario);
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
                ModificarUsuarioGUI modificarUsuarioGUI = new ModificarUsuarioGUI(iAltaUsuario);
                modificarUsuarioGUI.setVisible(true);
            }
        });
        listarBeneficiarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosGUI listarBeneficiariosGUI = new ListarBeneficiariosGUI(iAltaUsuario);
                listarBeneficiariosGUI.setVisible(true);
            }
        });
        altadistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDistribucionGUI altaDistribucionGUI = new AltaDistribucionGUI(iAltaUsuario, iAltaDonacion, iAltaDistribucion);
                altaDistribucionGUI.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100,700);
    }
}
