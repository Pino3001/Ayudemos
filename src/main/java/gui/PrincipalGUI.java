package gui;

import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import persistencia.Conexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrincipalGUI extends JFrame {
    private final IAltaUsuario iAltaUsuario;
    private final IAltaDonacion iAltaDonacion;
    private final IAltaDistribucion iAltaDistribucion;
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
        // Añadir WindowListener para cerrar conexiones al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Conexion.getInstancia().close();  // Cierra la conexión cuando la ventana se cierra
                System.exit(0);  // Asegura la terminación del programa
            }
        });
        altaDonacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altaDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDonacionGUI altaDonacionGUI = new AltaDonacionGUI(iAltaDonacion);
                altaDonacionGUI.setVisible(true);
            }

        });
        altaUsuarioB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altaUsuarioB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaUsuarioUI altaUsuarioUI = new AltaUsuarioUI(iAltaUsuario);
                altaUsuarioUI.setVisible(true);
            }
        });
        modificarDonacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modificarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDonacionGUI modificarDonacionGUI = new ModificarDonacionGUI(iAltaDonacion);
                modificarDonacionGUI.setVisible(true);
            }
        });
        modificarUsr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modificarUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarUsuarioGUI modificarUsuarioGUI = new ModificarUsuarioGUI(iAltaUsuario);
                modificarUsuarioGUI.setVisible(true);
            }
        });
        listarBeneficiarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listarBeneficiarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosGUI listarBeneficiariosGUI = new ListarBeneficiariosGUI(iAltaUsuario);
                listarBeneficiariosGUI.setVisible(true);
            }
        });
        altadistribucion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altadistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDistribucionGUI altaDistribucionGUI = new AltaDistribucionGUI(iAltaUsuario, iAltaDonacion, iAltaDistribucion);
                altaDistribucionGUI.setVisible(true);
            }
        });


        modificarDistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDistribucionGUI modificarDistribucionGUI = new ModificarDistribucionGUI(iAltaUsuario,iAltaDonacion,iAltaDistribucion);
                modificarDistribucionGUI.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100,700);
    }
}
