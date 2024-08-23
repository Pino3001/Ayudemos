import Ayudemos.gui.ListarBeneficiariosGUI;
import Ayudemos.gui.ModificarDonacionGUI;
import Ayudemos.gui.PrincipalGUI;
import Ayudemos.interfaces.Fabrica;
import Ayudemos.interfaces.IAltaUsuario;
//import Ayudemos.gui.AltaDistribucionGUI;


public class Main {
    public static void main(String[] args) {
        // Fábrica
        Fabrica fabrica = Fabrica.getInstancia();
        // Interfaces
        IAltaUsuario iAltaUsuario = fabrica.getAltaUsuario();
//        IAltaDistribucion iAltaDistribucion = fabrica.getIAltaDistribucion();


        // GUI
       // RegistroUsuarioGUI registroGUI = new RegistroUsuarioGUI(iAltaUsuario);
//        AltaDistribucionGUI altaDistribucionGUI = new AltaDistribucionGUI(iAltaDistribucion);
        //registroGUI.setVisible(true);
        //ListarBeneficiariosGUI listarBeneficiariosGUI  = new ListarBeneficiariosGUI();
       // listarBeneficiariosGUI.setVisible(true);
      PrincipalGUI principalGUI = new PrincipalGUI(iAltaUsuario);
      principalGUI.setVisible(true);
    }
}