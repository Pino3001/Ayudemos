package Ayudemos.interfaces;

import Ayudemos.objects.AltaDistribucion;

public class Fabrica {
    private static Fabrica instancia = null;

    private Fabrica() {
    }

    public static Fabrica getInstancia() {
        if (instancia == null)
            instancia = new Fabrica();
        return instancia;
    }

    // Alta distribución.
    public IAltaDistribucion getIAltaDistribucion() {
        return new AltaDistribucion();
    }
}