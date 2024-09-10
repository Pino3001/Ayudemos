package interfaces;

import objects.*;

public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {
    }

    public static Fabrica getInstancia() {
        if (instancia == null)
            instancia = new Fabrica();
        return instancia;
    }
    //

    // Alta distribución.
    public IControladorDistribucion getIControladorDistribucion() {
        return new ControladorDistribucion();
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }

    public IControladorDonacion getAltaDonacion() {
        return new ControladorDonacion();
    }


}