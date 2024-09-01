
import datatypes.DTDonacion;
import gui.*;
import interfaces.Fabrica;
import interfaces.IAltaDistribucion;
import interfaces.IAltaDonacion;
import interfaces.IAltaUsuario;
import java.util.List;
//import Ayudemos.gui.AltaDistribucionGUI;


public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IAltaUsuario iAltaUsuario = fabrica.getAltaUsuario();
        IAltaDonacion iAltaDonacion = fabrica.getAltaDonacion();
        IAltaDistribucion iAltaDistribucion = fabrica.getIAltaDistribucion();

        DatosPorDefecto dpf = new DatosPorDefecto();
        List<DTDonacion> dt = dpf.getAlimentosDT();
        for (DTDonacion d : dt) {
            if (iAltaDonacion.crearDonacion(d)) {
                System.out.println("Al parecer fue creado!");
            }
        }

        // GUI

        PrincipalGUI principalGUI = new PrincipalGUI(iAltaUsuario, iAltaDonacion, iAltaDistribucion);
        principalGUI.setVisible(true);
    }
}