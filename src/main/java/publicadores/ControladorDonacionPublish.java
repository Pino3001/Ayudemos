package publicadores;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuraciones.WebServiceConfiguracion;
import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import datatypes.soap.DtDonacionSOAP;
import excepciones.IngresoIncorrectoExeption;
import excepciones.NoEncontradoExeption;
import interfaces.Fabrica;
import interfaces.IControladorDonacion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorDonacionPublish {
    private Fabrica fabrica;
    private IControladorDonacion icon;
    private WebServiceConfiguracion configuracion;
    private Endpoint endpoint;

    public ControladorDonacionPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getAltaDonacion();
        try {
            configuracion = new WebServiceConfiguracion();
        } catch (Exception ex) {

        }
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorDonacion", this);
        System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorDonacion");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public boolean crearDonacion(DtDonacionSOAP dtSOAP){
        return icon.crearDonacion(new DTDonacion(dtSOAP));
    }

    @WebMethod
    public DtDonacionSOAP buscarDonacionID(Integer id) throws NoEncontradoExeption{
        return new DtDonacionSOAP(icon.buscarDonacionID(id));
    }

    @WebMethod
    public void editarDonacion(DtDonacionSOAP dtSOAP, Integer id) throws IngresoIncorrectoExeption{
        icon.editarDonacion(new DTDonacion(dtSOAP), id);
    }

    @WebMethod
    public List<DTAlimento> listarAlimentos(){
        return icon.listarAlimentos();
    }

    @WebMethod
    public List<DTArticulo> listarArticulos(){
        return icon.listarArticulos();
    }

    @WebMethod
    public List<DTDonacion> listarDonaciones(){
        return icon.listarDonaciones();
    }
}
