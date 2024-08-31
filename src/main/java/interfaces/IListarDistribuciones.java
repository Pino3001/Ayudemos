package interfaces;

import datatypes.DtDistribucion;
import types.Barrio;

import java.util.List;

public interface IListarDistribuciones {
    // Retorna una lista de DTDistribucion todas las distribuciones del sistema.
    List<DtDistribucion> obtenerListaDistribuciones();

    // Retorna una lista de DTDistribucion filtrada por la zona pasada por parámetro.
    List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio);

}