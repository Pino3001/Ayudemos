package objects;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import interfaces.IModificarDistribucion;

public class ModificarDistribucion implements IModificarDistribucion {

    private ManejadorDistribucion manejadorDistribucion;

    public ModificarDistribucion() {
        this.manejadorDistribucion = ManejadorDistribucion.getInstance();
    }

    @Override
    public DtDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        return null;
    }

    public void modificarDistribucion(DtDistribucion dtDistribucion) {
        // Buscar la distribución existente utilizando el DTO
        DtDistribucion distribucionExistente = manejadorDistribucion.buscarDistribucion(dtDistribucion.getEmailBeneficiario(),dtDistribucion.getIdDonacion()
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
    public DtBeneficiario[] obtenerBeneficiarios() {
        return new DtBeneficiario[0];
    }

    @Override
    public DTDonacion[] obtenerDonaciones() {
        return new DTDonacion[0];
    }
}
