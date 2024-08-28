import datatypes.DTAlimento;
import datatypes.DTDonacion;
import gui.*;
import interfaces.Fabrica;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import types.DTFecha;
import types.DTFechaHora;

import java.io.Console;
import java.util.List;
//import Ayudemos.gui.AltaDistribucionGUI;


public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IAltaUsuario iAltaUsuario = fabrica.getAltaUsuario();
        IAltaDonacion iAltaDonacion = fabrica.getAltaDonacion();

        DatosPorDefecto dpf = new DatosPorDefecto();
        List<DTDonacion> dt = dpf.getAlimentosDT();
        for (DTDonacion d : dt) {
            if (iAltaDonacion.crearDonacion(d)) {
                System.out.println("Al parecer fue creado!");
            }
        }

        // GUI

        PrincipalGUI principalGUI = new PrincipalGUI(iAltaUsuario, iAltaDonacion);
        principalGUI.setVisible(true);
    }
}