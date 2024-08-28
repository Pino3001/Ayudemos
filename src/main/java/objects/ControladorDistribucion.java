package objects;

//import objects.Distribucion;
import interfaces.IDistribucion;
import objects.ManejadorDistribucion;
import objects.Distribucion;
import types.DtDistribucion;
import types.EstadoDistribucion;
//import types.EstadoDistribucion;
import java.util.List;

public class ControladorDistribucion implements IDistribucion {
    public ControladorDistribucion() {
        super();

    }
//    @Override
//    public void altaDistribucion(Beneficiario beneficiario,List< Donacion > donaciones,DTFechaHora fechaPreparacion,DTFechaHora fechaEntrega,EstadoDistribucion estado) {
//
//        Distribucion nuevaDist = new Distribucion(fechaPreparacion, fechaEntrega, estado, donaciones, beneficiario);
//        ManejadorDistribucion mD = ManejadorDistribucion.getInstance();
//        mD.
//    }

//    @Override
//    public void modificarDistribucion(Distribucion distribucion) {
//

    @Override
    public List<DtDistribucion> listarDistribucionesPorEstado (EstadoDistribucion estado){

        ManejadorDistribucion md = ManejadorDistribucion.getInstance();


        return md.buscarDistribucionesPorEstado(estado);
    }


//@Override
//public DtDistribucion obtenerDistribucion(){
//
//    ManejadorDistribucion md = ManejadorDistribucion.getInstance();
//    Distribucion distribucion = md.buscarDistribucionEstado();
//
//    DtDistribucion dtDistribucion = distribucion.getDtDistribucion();
//
//    return dtDistribucion;
//
//}



//List<DtDistribucion> listarDistribucionesPorEstado (EstadoDistribucion estado);

}