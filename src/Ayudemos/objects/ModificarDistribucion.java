package Ayudemos.objects;

import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.interfaces.IModificarDistribucion;
import Ayudemos.objects.ManejadorDistribucion;

public class ModificarDistribucion implements IModificarDistribucion {

    private ManejadorDistribucion manejadorDistribucion;

    public ModificarDistribucion() {
        this.manejadorDistribucion = ManejadorDistribucion.getInstance();
    }

    @Override
    public DTDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        return null;
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

    @Override
    public Beneficiario[] obtenerBeneficiarios() {
        return new Beneficiario[0];
    }

    @Override
    public Donacion[] obtenerDonaciones() {
        return new Donacion[0];
    }
}
