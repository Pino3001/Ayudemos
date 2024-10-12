package publicadores;

import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuraciones.WebServiceConfiguracion;
import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import datatypes.soap.DtBeneficiarioSOAP;
import datatypes.soap.DtDistribucionSOAP;
import datatypes.soap.DtDonacionSOAP;
import excepciones.IngresoIncorrectoExeption;
import interfaces.Fabrica;
import interfaces.IControladorDistribucion;
import types.Barrio;
import types.EstadoDistribucion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
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
        endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorDistribucion", this);
        System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorDistribucion");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public List<DtBeneficiario> obtenerListaDtBeneficiarios(){
        return icon.obtenerListaDtBeneficiarios();
    }

    @WebMethod
    public List<DTDonacion> obtenerListaDtDonaciones(){
        return icon.obtenerListaDtDonaciones();
    }

    @WebMethod
    public List<DtDistribucion> obtenerListaDistribuciones(){
        return icon.obtenerDistribuciones();
    }

    @WebMethod
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio){
        return icon.obtenerListaDistribucionesZona(barrio);
    }

    @WebMethod
    public List<DtDistribucion> listarDistribucionesPorEstado(EstadoDistribucion estado){
        return icon.listarDistribucionesPorEstado(estado);
    }

    @WebMethod
    public List<DtReporteZona> obtenerReporteZona(LocalDate fechaInicial, LocalDate fechaFinal){
        return icon.obtenerReporteZona(fechaInicial, fechaFinal);
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

    @WebMethod
    public List<DtDistribucion> obtenerDistribuciones(){
        return icon.obtenerDistribuciones();
    }
}
