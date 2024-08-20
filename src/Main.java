import Ayudemos.interfaces.Fabrica;
import Ayudemos.interfaces.IAltaUsuario;
import Ayudemos.gui.RegistroUsuarioGUI;

public class Main {
    public static void main(String[] args) {
        IAltaUsuario altaUsuario = Fabrica.getInstancia().getAltaUsuario();
        RegistroUsuarioGUI registroGUI = new RegistroUsuarioGUI(altaUsuario);
        registroGUI.setVisible(true);
    }
}