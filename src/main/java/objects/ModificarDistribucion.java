package objects;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import interfaces.IModificarDistribucion;

import java.util.List;
import java.util.stream.Collectors;

public class ModificarDistribucion implements IModificarDistribucion {

    private ManejadorDistribucion manejadorDistribucion;
    private ManejadorUsuario manejadorUsuario;  // Usaremos para obtener beneficiarios
    private ManejadorDonacion manejadorDonacion; // Usaremos para obtener donaciones

    public ModificarDistribucion() {
        this.manejadorDistribucion = ManejadorDistribucion.getInstance();
        this.manejadorUsuario = ManejadorUsuario.getInstance();
        this.manejadorDonacion = ManejadorDonacion.getInstance();
    }

    @Override
    public DtDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        return manejadorDistribucion.buscarDistribucion(emailBeneficiario, idDonacion);
    }

    @Override
    public void modificarDistribucion(DtDistribucion dtDistribucion) {
        DtDistribucion distribucionExistente = manejadorDistribucion.buscarDistribucion(
                dtDistribucion.getEmailBeneficiario(), dtDistribucion.getIdDonacion()
        );

        if (distribucionExistente != null) {
            manejadorDistribucion.modificarDistribucion(dtDistribucion);
        } else {
            throw new IllegalArgumentException("La distribución no existe en el sistema");
        }
    }

    @Override
    public DtBeneficiario[] obtenerBeneficiarios() {
        List<DtBeneficiario> beneficiarios = manejadorUsuario.obtenerBeneficiarios();
        return beneficiarios.toArray(new DtBeneficiario[0]);
    }

    @Override
    public DTDonacion[] obtenerDonaciones() {
        List<DTDonacion> donaciones = manejadorDonacion.obtenerDonaciones();
        return donaciones.toArray(new DTDonacion[0]);
    }

    @Override
    public List<DtDistribucion> obtenerDistribuciones() {
        return manejadorDistribucion.obtenerListaDistribuciones();  // Ya es una lista, no necesitas convertir
    }
}