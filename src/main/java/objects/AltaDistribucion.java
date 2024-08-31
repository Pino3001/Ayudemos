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
        // Manejadores.
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        ManejadorDistribucion manejadorDistribucion = ManejadorDistribucion.getInstance();
        // Buscamos las instanacias de clase la donacion y beneficiario para mandarlas por parámetro al manejador de distribuciones.
        Beneficiario beneficarioEncontrado = (Beneficiario) manejadorUsuario.buscarUsuario(beneficiario.getId());
        Donacion donacionEncontrada = manejadorDonacion.buscarDonacion(donacion.getId());

        // Creamos la instancia de la nueva distribución.
        Distribucion nuevaDistribucion = new Distribucion(fechaPreparacion, fechaEntrega, estado, donacionEncontrada, beneficarioEncontrado);
        // Llamamos al manejador de distribuciones para agregar la distribución con los datos recibidos.
        manejadorDistribucion.agregarDistribucion(nuevaDistribucion);
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

    @Override
    // Retorna una lista de DtDistribución filtrada por el estado pasado por parámetro.
    public List<DtDistribucion> listarDistribucionesPorEstado(EstadoDistribucion estado) {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        List<DtDistribucion> lista = md.buscarDistribucionesPorEstado(estado);
        return lista;
    }

}
