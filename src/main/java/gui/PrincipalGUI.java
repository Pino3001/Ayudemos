package gui;

import gui.componentes.ColorUtil;
import interfaces.*;
import persistencia.Conexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PrincipalGUI extends JFrame {
    private final IControladorUsuario iControladorUsuario;
    private final IControladorDonacion iControladorDonacion;
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

    public PrincipalGUI(IControladorUsuario altaUsuario, IControladorDonacion iControladorDonacion, IControladorDistribucion iControladorDistribucion) {
        this.iControladorUsuario = altaUsuario;
        this.iControladorDonacion = iControladorDonacion;
        setLocationRelativeTo(null);


        altaUsuarioB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altaUsuarioB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaUsuarioUI altaUsuarioUI = new AltaUsuarioUI(iControladorUsuario);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight();
                altaUsuarioUI.setPosicion(x, y);
                altaUsuarioUI.setVisible(true);
            }
        });

        modificarUsr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modificarUsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarUsuarioGUI modificarUsuarioGUI = new ModificarUsuarioGUI(iControladorUsuario);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight();
                modificarUsuarioGUI.setPosicion(x, y);
                modificarUsuarioGUI.setVisible(true);
            }
        });

        listarBeneficiarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listarBeneficiarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosGUI listarBeneficiariosGUI = new ListarBeneficiariosGUI(iControladorUsuario);
                int x = titulo.getLocationOnScreen().x - 147;
                int y = titulo.getLocationOnScreen().y - 50;
                listarBeneficiariosGUI.setPosicion(x, y);
                listarBeneficiariosGUI.setVisible(true);
            }
        });

        listarBenefiEstado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listarBenefiEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosEstadoGUI listarBeneficiariosEstadoGUI = new ListarBeneficiariosEstadoGUI(iControladorUsuario);
                int x = titulo.getLocationOnScreen().x;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight();
                listarBeneficiariosEstadoGUI.setPosicion(x, y);
                listarBeneficiariosEstadoGUI.setVisible(true);
            }
        });

        listarBenefiZona.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Agregar el ActionListener para listarBenefiZona
        listarBenefiZona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarBeneficiariosZonaGUI listarBeneficiariosZonaGUI = new ListarBeneficiariosZonaGUI(iControladorUsuario);
                int x = titulo.getLocationOnScreen().x;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight();
                listarBeneficiariosZonaGUI.setPosicion(x, y);
                listarBeneficiariosZonaGUI.setVisible(true);
            }
        });

        altadistribucion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altadistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDistribucionGUI altaDistribucionGUI = new AltaDistribucionGUI(iControladorUsuario, iControladorDonacion, iControladorDistribucion);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight() + 20;
                altaDistribucionGUI.setPosicion(x, y);
                altaDistribucionGUI.setVisible(true);
            }
        });

        modificarDistribucion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modificarDistribucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDistribucionGUI modificarDistribucionGUI = new ModificarDistribucionGUI(iControladorDistribucion, iControladorUsuario, iControladorDonacion);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight();
                modificarDistribucionGUI.setPosicion(x, y);
                modificarDistribucionGUI.setVisible(true);
            }
        });

        listarDistriXEstado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listarDistriXEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarPorEstadoGUI listarPorEstadoGUI = new ListarPorEstadoGUI(iControladorDistribucion, iControladorUsuario, iControladorDonacion);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y - 45;
                listarPorEstadoGUI.setPosicion(x, y);
                listarPorEstadoGUI.setVisible(true);
            }
        });

        listarDistriXZona.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        listarDistriXZona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarPorZonaGUI listarPorZonaGUI = new ListarPorZonaGUI(iControladorDistribucion, iControladorUsuario);
                int x = titulo.getLocationOnScreen().x - 100;
                int y = titulo.getLocationOnScreen().y - 45;
                listarPorZonaGUI.setPosicion(x, y);
                listarPorZonaGUI.setVisible(true);
            }
        });

        altaDonacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        altaDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaDonacionGUI altaDonacionGUI = new AltaDonacionGUI(iControladorDonacion);
                int x = titulo.getLocationOnScreen().x - 30;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight() + 60;
                altaDonacionGUI.setPosicion(x, y);
                altaDonacionGUI.setVisible(true);
            }
        });

        modificarDonacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modificarDonacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarDonacionGUI modificarDonacionGUI = new ModificarDonacionGUI(iControladorDonacion);
                int x = titulo.getLocationOnScreen().x - 30;
                int y = titulo.getLocationOnScreen().y + titulo.getHeight() + 50;
                modificarDonacionGUI.setPosicion(x, y);
                modificarDonacionGUI.setVisible(true);
            }
        });

        mayoresDistribuciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mayoresDistribuciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZonasMayorDistribucionGUI zonasMayorDistribucionGUI = new ZonasMayorDistribucionGUI(iControladorDistribucion);
                int x = titulo.getLocationOnScreen().x - 50;
                int y = titulo.getLocationOnScreen().y + 20;
                zonasMayorDistribucionGUI.setPosicion(x, y);
                zonasMayorDistribucionGUI.setVisible(true);
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
        principalPanel.setBackground(ColorUtil.getColor("backgroundPrincipal"));
    }

    private void createUIComponents() {
        this.principalPanel = new JPanel();
        setContentPane(principalPanel);
        setSize(1100, 700);

    }
}
