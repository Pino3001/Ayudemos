//package datatypes;
//
//import types.Barrio;
//
//import java.util.List;
//// IGNORAR (al final no haría falta este Datatype)
//// La idea para generar el reporte es crear una lista List<DTReporteZona> con varios de estos datatypes:
//// Cada datatype contiene una List<DTDistribucion>, la zona a la que pertenecen las distribuciones y
//// la cantidad de distribuciones calculadas a partir de la lista.
//// El manejador de distribuciones tendrá una operación para generar un reporte como se pide en el último caso de uso extra.
//public class DtReporteZona {
//    private final List<DtDistribucion> distribuciones;
//    private final Barrio zona;
//    private final Integer cantidadDistribuciones;
//
//    public DtReporteZona(List<DtDistribucion> distribuciones, Barrio zona) {
//        this.distribuciones = distribuciones;
//        this.zona = zona;
//        this.cantidadDistribuciones = distribuciones.size();
//    }
//
//    public List<DtDistribucion> getDistribuciones() {
//        return distribuciones;
//    }
//
//    public Barrio getZona() {
//        return zona;
//    }
//
//    public Integer getCantidadDistribuciones() {
//        return cantidadDistribuciones;
//    }
//}
//
//
