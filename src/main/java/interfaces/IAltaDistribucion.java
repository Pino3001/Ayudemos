//    Cada distribución incluye una fecha de preparación y una fecha de entrega, además de un
//    Universidad de la República | Dirección General de Educación Técnico Profesional
//    estado que puede ser "PENDIENTE", "EN CAMINO" o "ENTREGADO". Una distribución puede
//    involucrar una o más donaciones, y está destinada a un beneficiario específico.

package interfaces;

import datatypes.DTDonacion;
import datatypes.DtDistribucion;
import objects.Beneficiario;
import objects.Donacion;
import types.Barrio;
import datatypes.DtBeneficiario;
import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IAltaDistribucion {
    // Crea una nueva distribución.
    void crearDistribucion(Beneficiario beneficiario,
                           Donacion donacion,
                           LocalDateTime fechaPreparacion,
                           LocalDateTime fechaEntrega,
                           EstadoDistribucion estado);

    // Retornar lista de beneficiarios para cargar el combobox.
    public List<DtBeneficiario> obtenerListaDtBeneficiarios();

    // Retornar lista de donaciones para cargar el combobox.
    public List<DTDonacion> obtenerListaDtDonaciones();

    // Retorna una lista de DTDistribucion todas las distribuciones del sistema.
    List<DtDistribucion> obtenerListaDistribuciones();

    // Retorna una lista de DTDistribucion filtrada por la zona pasada por parámetro.
    List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio);


}
