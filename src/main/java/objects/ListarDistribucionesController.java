package objects;

import datatypes.DtDistribucion;
import interfaces.IListarDistribuciones;
import types.Barrio;

import java.util.List;

public class ListarDistribucionesController implements IListarDistribuciones {
    public ListarDistribucionesController() {
        super();
    }

    @Override
    // Retorna una lista de DTDistribucion todas las distribuciones del sistema.
    public List<DtDistribucion> obtenerListaDistribuciones() {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        List<DtDistribucion> lista = md.obtenerListaDistribuciones();
        return lista;
    }

    @Override
    // Retorna una lista de DTDistribucion filtrada por la zona pasada por parámetro.
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio) {
        ManejadorDistribucion md = ManejadorDistribucion.getInstance();
        List<DtDistribucion> lista = md.obtenerListaDistribucionesZona(barrio);
        return lista;
    }

}
