package publicadores;

import configuraciones.WebServiceConfiguracion;
import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.soap.DtBeneficiarioSOAP;
import datatypes.soap.DtDistribucionSOAP;
import datatypes.soap.DtDonacionSOAP;
import excepciones.IngresoIncorrectoExeption;
import interfaces.Fabrica;
import interfaces.IControladorDistribucion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import types.Barrio;
import types.EstadoDistribucion;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorDistribucionPublish {

    private Fabrica fabrica;
    private IControladorDistribucion icon;
    private WebServiceConfiguracion configuracion;
    private Endpoint endpoint;

    public ControladorDistribucionPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getIControladorDistribucion();
        try {
            configuracion = new WebServiceConfiguracion();
        } catch (Exception ex) {

        }
    }

    @WebMethod(exclude = true)
    public void publicar() {
        // Publica el servicio utilizando la configuración
        String wsIp = configuracion.getConfigOf("#WS_IP");
        String wsPort = configuracion.getConfigOf("#WS_PORT");

        // Formamos la URL del endpoint
        String url = "http://" + wsIp + ":" + wsPort + "/ControladorDistribucionPublish";
        endpoint = Endpoint.publish(url, this);
        System.out.println(url); // Muestra la URL del servicio
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    // Obtener la lista de distribuciones PENDIENTE
    @WebMethod
        public DtDistribucionSOAP[] listaDistribucionesPendientesSOAP(){
        return icon.listaDistribucionesPendientesSOAP();
    }
//    @WebMethod
//    public List<DtBeneficiario> obtenerListaDtBeneficiarios(){
//        return icon.obtenerListaDtBeneficiarios();
//    }
//
//    @WebMethod
//    public List<DTDonacion> obtenerListaDtDonaciones(){
//        return icon.obtenerListaDtDonaciones();
//    }
//
//    @WebMethod
//    public List<DtDistribucion> obtenerListaDistribuciones(){
//        return icon.obtenerDistribuciones();
//    }
//
//    @WebMethod
//    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio){
//        return icon.obtenerListaDistribucionesZona(barrio);
//    }
//
//    @WebMethod
//    public List<DtDistribucion> listarDistribucionesPorEstado(EstadoDistribucion estado){
//        return icon.listarDistribucionesPorEstado(estado);
//    }
//
//    @WebMethod
//    public List<DtReporteZona> obtenerReporteZona(LocalDate fechaInicial, LocalDate fechaFinal){
//        return icon.obtenerReporteZona(fechaInicial, fechaFinal);
//    }
//@WebMethod
//public List<DtDistribucion> obtenerDistribuciones(){
//    return icon.obtenerDistribuciones();
//}
    @WebMethod
    public void cambiarEstado(DtDistribucionSOAP dtDistribucionSOAP) {
        icon.cambiarEstadoDistri(dtDistribucionSOAP);
    }

    @WebMethod
    public DtDistribucionSOAP buscarDistribucion(int idDistribucion) {
            DtDistribucion dt =  icon.buscarDistribucion(idDistribucion);
            return new DtDistribucionSOAP(dt);
    }

    @WebMethod
    public void modificarDistribucion(DtDistribucionSOAP dtDistribucionSOAP) throws IngresoIncorrectoExeption{
        DtDistribucion dt = new DtDistribucion(dtDistribucionSOAP);
        icon.modificarDistribucion(dt);
    }

    @WebMethod
    public DtBeneficiarioSOAP[] obtenerBeneficiarios() {
        DtBeneficiario[] dtBeneficiarios = icon.obtenerBeneficiarios();
        DtBeneficiarioSOAP[] ret = new DtBeneficiarioSOAP[dtBeneficiarios.length];

        int i = 0;
        for(DtBeneficiario b : dtBeneficiarios) {
            ret[i]= new DtBeneficiarioSOAP(b);
            i++;
        }
        return ret;
    }

    @WebMethod
    public DtDonacionSOAP[] obtenerDonaciones(){
        DTDonacion[] dtDonaciones = icon.obtenerDonaciones();
        DtDonacionSOAP[] ret = new DtDonacionSOAP[dtDonaciones.length];

        int i = 0;
        for(DTDonacion b : dtDonaciones) {
            ret[i]= new DtDonacionSOAP(b);
            i++;
        }
        return ret;
    }

    // Obtener la lista de distribuciones PENDIENTE
    @WebMethod
    public DtDistribucionSOAP[] pendientesPorZona(Barrio barrio){
        List<DtDistribucion> dtDistribucions = icon.listaDistribucionesZonaPendiente(barrio);
        DtDistribucionSOAP[] ret = new DtDistribucionSOAP[dtDistribucions.size()];
        for (int i = 0; i < dtDistribucions.size(); i++){
            ret[i] = new DtDistribucionSOAP(dtDistribucions.get(i));
        }
        return ret;
    }

    @WebMethod
    public DtDistribucionSOAP[] listaDistribucionesBeneficiarioSOAP(Integer id){
        return icon.listaDistribucionesBeneficiarioSOAP(id);
    }

    // Obtener la lista de distribuciones por BENEFICIARIO y ESTADO
    @WebMethod
    public DtDistribucionSOAP[] listaDistribucionesPorEstado(Integer id, EstadoDistribucion estado){
        List<DtDistribucion> dtDistribucions = icon.listaDistribucionesPorEstado(id,estado);
        DtDistribucionSOAP[] ret = new DtDistribucionSOAP[dtDistribucions.size()];
        for (int i = 0; i < dtDistribucions.size(); i++){
            ret[i] = new DtDistribucionSOAP(dtDistribucions.get(i));
        }
        return ret;
    }
}
