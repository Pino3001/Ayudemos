package interfaces;

import datatypes.DTDonacion;
import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import datatypes.soap.DtDistribucionSOAP;
import excepciones.IngresoIncorrectoExeption;
import types.Barrio;
import datatypes.DtBeneficiario;
import types.EstadoDistribucion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IControladorDistribucion {
    // Crea una nueva distribución.
    void crearDistribucion(DtBeneficiario beneficiario,
                           DTDonacion donacion,
                           LocalDateTime fechaPreparacion,
                           LocalDateTime fechaEntrega,
                           EstadoDistribucion estado) throws IngresoIncorrectoExeption;

    // Retornar lista de beneficiarios para cargar el combobox.
    public List<DtBeneficiario> obtenerListaDtBeneficiarios();

    // Retornar lista de donaciones para cargar el combobox.
    public List<DTDonacion> obtenerListaDtDonaciones();

    // Retorna una lista de DTDistribucion todas las distribuciones del sistema.
    List<DtDistribucion> obtenerListaDistribuciones();

    // Retorna una lista de DTDistribucion filtrada por la zona pasada por parámetro.
    List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio);

    List<DtDistribucion> listarDistribucionesPorEstado(EstadoDistribucion estado);

    // Retorna una lista de DTReporteZona filtrada por un rango de fechas.
    List<DtReporteZona> obtenerReporteZona(LocalDate fechaInicial, LocalDate fechaFinal);

    DtDistribucion buscarDistribucion(Integer idDistribucion);

    void modificarDistribucion(DtDistribucion dtDistribucion) throws IngresoIncorrectoExeption;

    DtBeneficiario[] obtenerBeneficiarios();

    DTDonacion[] obtenerDonaciones();

    List<DtDistribucion> obtenerDistribuciones(); // Nuevo metodo para obtener distribuciones

    // Funciones para SOAP
    DtDistribucionSOAP[] listaDistribucionesPendientesSOAP();

    void cambiarEstadoDistri(DtDistribucionSOAP dtDistribucionSOAP);

    List<DtDistribucion> listaDistribucionesZonaPendiente(Barrio barrio);

    List<DtDistribucion> listaDistribucionesPorEstado(Integer id, EstadoDistribucion estadoDistribucion);
}
