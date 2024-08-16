package Ayudemos.objects;

import Ayudemos.interfaces.IAltaDistribucion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

import java.util.List;

public class AltaDistribucion implements IAltaDistribucion {
    public AltaDistribucion() {
        super();
    }

    @Override
    public void crearDistribucion(Beneficiario beneficiario,
                                  List<Donacion> donaciones,
                                  DTFechaHora fechaPreparacion,
                                  DTFechaHora fechaEntrega,
                                  EstadoDistribucion estado) {
        Distribucion nuevaDist = new Distribucion(fechaPreparacion, fechaEntrega, estado, donaciones, beneficiario);

        // troll

    }
}
