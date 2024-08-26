package Ayudemos.interfaces;



import Ayudemos.objects.Distribucion;
import Ayudemos.objects.Donacion;
import Ayudemos.types.DtDistribucion;
import Ayudemos.types.EstadoDistribucion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.objects.Beneficiario;


import java.util.ArrayList;
import java.util.List;



public interface IDistribucion {
//
//    public void altaDistribucion(Beneficiario beneficiario,
//                                 List<Donacion> donaciones,
//                                 DTFechaHora fechaPreparacion,
//                                 DTFechaHora fechaEntrega,
//                                 EstadoDistribucion estado);

//    public void modificarDistribucion(Distribucion distribucion);

    //public DtDistribucion obtenerDistribucion();

    //public ArrayList<Distribucion> listarDistribucionesPorEstado(EstadoDistribucion estado);


    public List<DtDistribucion> listarDistribucionesPorEstado (EstadoDistribucion estado);

    //public DtDistribucion obtenerDistribucion(EstadoDistribucion estado);

    //   public List<Distribucion> listarDistribucionesPorZona(int zona);


    // public List<Distribucion> distribuciones();

}
