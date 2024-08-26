import Ayudemos.DatosPorDefecto;
import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTDonacion;
import Ayudemos.gui.*;
import Ayudemos.interfaces.Fabrica;
import Ayudemos.interfaces.IAltaDonacion;
import Ayudemos.interfaces.IAltaUsuario;
import Ayudemos.interfaces.IModificarDistribucion;
import Ayudemos.types.DTFecha;
import Ayudemos.types.DTFechaHora;

import java.io.Console;
import java.util.List;
//import Ayudemos.gui.AltaDistribucionGUI;


public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IAltaUsuario iAltaUsuario = fabrica.getAltaUsuario();
        IAltaDonacion iAltaDonacion =fabrica.getAltaDonacion();
        IModificarDistribucion iModificarDistribucion = fabrica.getModificarDistribucion();

        DatosPorDefecto dpf = new DatosPorDefecto();
        List<DTDonacion> dt = dpf.getAlimentosDT();
        for(DTDonacion d : dt){
            if(iAltaDonacion.crearDonacion(d)){
                System.out.println("Al parecer fue creado!");
            }
        }

        // GUI

      PrincipalGUI principalGUI = new PrincipalGUI(iAltaUsuario, iAltaDonacion, iModificarDistribucion);
      principalGUI.setVisible(true);
    }
}