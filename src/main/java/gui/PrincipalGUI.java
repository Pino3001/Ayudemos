package gui;

import interfaces.*;
import persistencia.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PrincipalGUI extends JFrame {
    private final IControladorUsuario controladorUsuario;
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

    public PrincipalGUI(IControladorUsuario altaUsuario, IAltaDonacion iAltaDonacion, IAltaDistribucion iAltaDistribucion, IModificarDistribucion iModificarDistribucion) {
        this.controladorUsuario = altaUsuario;
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
                AltaUsuarioUI altaUsuarioUI = new AltaUsuarioUI(controladorUsuario);
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
                ModificarUsuarioGUI modificarUsuarioGUI = new ModificarUsuarioGUI(controladorUsuario);
                modificarUsuarioGUI.setVisible(true);
            }
        });

        modificarDistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDistribucionGUI modificarDistribucionGUI = new ModificarDistribucionGUI(iModificarDistribucion);
                modificarDistribucionGUI.setVisible(true);
            }
        });

        listarBeneficiarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosGUI listarBeneficiariosGUI = new ListarBeneficiariosGUI(controladorUsuario);
                listarBeneficiariosGUI.setVisible(true);
            }
        });

        altadistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDistribucionGUI altaDistribucionGUI = new AltaDistribucionGUI(controladorUsuario, iAltaDonacion, iAltaDistribucion);
                altaDistribucionGUI.setVisible(true);
            }
        });

        // Agregar el ActionListener para listarBenefiZona
        listarBenefiZona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosZonaGUI listarBeneficiariosZonaGUI = new ListarBeneficiariosZonaGUI(controladorUsuario);
                listarBeneficiariosZonaGUI.setVisible(true);
            }
        });

        // Añadir WindowListener para cerrar conexiones al cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Conexion.getInstancia().close();  // Cierra la conexión cuando la ventana se cierra
                System.exit(0);  // Asegura la terminación del programa
            }
        });
    }

    private void createUIComponents() {
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100, 700);
    }
}
