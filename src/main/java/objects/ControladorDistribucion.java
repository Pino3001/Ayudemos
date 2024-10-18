package objects;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import datatypes.soap.DtDistribucionSOAP;
import excepciones.IngresoIncorrectoExeption;
import interfaces.IControladorDistribucion;
import types.Barrio;
import types.EstadoDistribucion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// Controlador Alta Distribución.
public class ControladorDistribucion implements IControladorDistribucion {
    public ControladorDistribucion() {
        super();
    }

    // Crea una nueva distribución.
    @Override
    public void crearDistribucion(DtBeneficiario beneficiario,
                                  DTDonacion donacion,
                                  LocalDateTime fechaPreparacion,
                                  LocalDateTime fechaEntrega,
                                  EstadoDistribucion estado) throws IngresoIncorrectoExeption {
        // Manejadores.
        System.out.println("Viene como fecha de entrega: "+ fechaEntrega +"y el estado es" + estado);
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        ManejadorDonacion manejadorDonacion = ManejadorDonacion.getInstance();
        ManejadorDistribucion manejadorDistribucion = ManejadorDistribucion.getInstance();
        if (estado.equals(EstadoDistribucion.PENDIENTE) && fechaEntrega != null) {
            throw new IngresoIncorrectoExeption("Estado de distribucion y fecha\n de entrega no compatibles");
        } else if (!estado.equals(EstadoDistribucion.PENDIENTE) && fechaEntrega == null) {
            throw new IngresoIncorrectoExeption("Estado de distribucion y fecha\n de entrega no compatibles");
        }else {
            // Buscamos las instanacias de clase la donacion y beneficiario para mandarlas por parámetro al manejador de distribuciones.
            Beneficiario beneficarioEncontrado = (Beneficiario) manejadorUsuario.buscarUsuario(beneficiario.getId());
            Donacion donacionEncontrada = manejadorDonacion.buscarDonacion(donacion.getId());
            if (beneficarioEncontrado == null) {
                throw new IngresoIncorrectoExeption("No existe el Beneficiario!");
            }else if (donacionEncontrada == null) {
                throw new IngresoIncorrectoExeption("No existe la Donacion!");
            }else {
                // Creamos la instancia de la nueva distribución.
                Distribucion nuevaDistribucion = new Distribucion(fechaPreparacion, fechaEntrega, estado, donacionEncontrada, beneficarioEncontrado);
                // Llamamos al manejador de distribuciones para agregar la distribución con los datos recibidos.
                manejadorDistribucion.agregarDistribucion(nuevaDistribucion);
            }
        }
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
        return md.buscarDistribucionesPorEstado(estado);
    }


    // Retorna una lista de DTReporteZona filtrada por un rango de fechas.
    public List<DtReporteZona> obtenerReporteZona(LocalDate fechaInicial, LocalDate fechaFinal) {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.obtenerReporteZonas(fechaInicial, fechaFinal);
    }


    @Override
    public DtDistribucion buscarDistribucion(Integer idDistribucion) {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.buscarDistribucion(idDistribucion);
    }

    @Override
    public void modificarDistribucion(DtDistribucion dtDistribucion) throws IngresoIncorrectoExeption {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        md.modificarDistribucion(dtDistribucion);
    }

    @Override
    public DtBeneficiario[] obtenerBeneficiarios() {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        List<DtBeneficiario> beneficiarios = mu.obtenerBeneficiarios();
        return beneficiarios.toArray(new DtBeneficiario[0]);
    }

    @Override
    public DTDonacion[] obtenerDonaciones() {
        ManejadorDonacion md = ManejadorDonacion.getInstance();
        List<DTDonacion> donaciones = md.obtenerDonaciones();
        return donaciones.toArray(new DTDonacion[0]);
    }

    @Override
    public List<DtDistribucion> obtenerDistribuciones() {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.obtenerListaDistribuciones();  // Ya es una lista, no necesitas convertir
    }

    // Funciones para SOAP
    public DtDistribucionSOAP[] listaDistribucionesPendientesSOAP(){
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        return md.listaDistribucionesPendientesSOAP();
    }


}
