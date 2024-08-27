package Ayudemos.Logica;

//import Ayudemos.objects.Distribucion;
import Ayudemos.interfaces.IDistribucion;
import Ayudemos.objects.ManejadorDistribucion;
import Ayudemos.objects.Distribucion;
import Ayudemos.types.DtDistribucion;
import Ayudemos.types.EstadoDistribucion;
//import Ayudemos.types.EstadoDistribucion;
import java.util.ArrayList;
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