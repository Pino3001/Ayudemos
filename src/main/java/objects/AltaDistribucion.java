package objects;

import datatypes.DTDonacion;
import datatypes.DtDistribucion;
import interfaces.IAltaDistribucion;
import types.Barrio;
import datatypes.DtBeneficiario;
import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.List;

// Controlador Alta Distribución.
public class AltaDistribucion implements IAltaDistribucion {
    public AltaDistribucion() {
        super();
    }

    // Crea una nueva distribución.
    @Override
    public void crearDistribucion(DtBeneficiario beneficiario,
                                  DTDonacion donacion,
                                  LocalDateTime fechaPreparacion,
                                  LocalDateTime fechaEntrega,
                                  EstadoDistribucion estado) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        ManejadorDistribucion manejadorDistribucion = ManejadorDistribucion.getInstance();
        Beneficiario beneficarioEncontrado = manejadorUsuario.obtenerBeneficiarios();
        Donacion donacionEncontrada = manejadorDonacion.buscarDonacionID();
    }

    // Retornar lista de todos los beneficiarios del sistema para cargar el combobox.
    @Override
    public List<DtBeneficiario> obtenerListaDtBeneficiarios() {
        List<DtBeneficiario> beneficiarios;
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        beneficiarios = mu.obtenerBeneficiarios();
        return beneficiarios;
    }

    // Retornar lista de todas las donaciones del sistema para cargar el combobox.
    @Override
    public List<DTDonacion> obtenerListaDtDonaciones() {
        List<DTDonacion> donaciones;
        // Obtenemos los datatypes de todas las donaciones del sistema.
        ManejadorDonacion md = ManejadorDonacion.getInstance();
        donaciones = md.obtenerDonaciones();
        return donaciones;
    }

    @Override
    // Retorna una lista de DTDistribucion todas las distribuciones del sistema.
    public List<DtDistribucion> obtenerListaDistribuciones() {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.obtenerListaDistribuciones();
    }

    @Override
    // Retorna una lista de DTDistribucion filtrada por la zona pasada por parámetro.
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio) {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.obtenerListaDistribucionesZona(barrio);
    }
}
