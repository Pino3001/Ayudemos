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
    public IAltaDistribucion getIAltaDistribucion() {
        return new AltaDistribucion();
    }

    public IAltaUsuario getAltaUsuario() {
        return new AltaUsuario();
    }

    public IAltaDonacion getAltaDonacion() {
        return new AltaDonacion();
    }

    public IListarBeneficiariosZona getListarBeneficiariosZona() {
        return new ListarBeneficiariosZona();
    }

    public IModificarDistribucion getModificarDistribucion() {
        return new ModificarDistribucion();
    }
}