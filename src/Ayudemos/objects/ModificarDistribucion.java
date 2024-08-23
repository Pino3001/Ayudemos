package Ayudemos.casosdeuso;

import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.objects.ManejadorDistribucion;

public class ModificarDistribucion {

    private ManejadorDistribucion manejadorDistribucion;

    public ModificarDistribucion() {
        this.manejadorDistribucion = ManejadorDistribucion.getInstance();
    }

    public void modificarDistribucion(DTDistribucion dtDistribucion) {
        // Buscar la distribución existente utilizando el DTO
        DTDistribucion distribucionExistente = manejadorDistribucion.buscarDistribucion(dtDistribucion.getEmailBeneficiario(),dtDistribucion.getIdDonacion()
        );

        // Si se encuentra la distribución
        if (distribucionExistente != null) {
            // Actualizar la distribución con los nuevos datos del DTO
            manejadorDistribucion.modificarDistribucion(dtDistribucion);
        } else {
            throw new IllegalArgumentException("La distribución no existe en el sistema");
        }
    }
}
