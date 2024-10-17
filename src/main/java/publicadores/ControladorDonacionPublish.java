package publicadores;

import java.util.List;


import configuraciones.WebServiceConfiguracion;
import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import datatypes.soap.DtDonacionSOAP;
import excepciones.IngresoIncorrectoExeption;
import excepciones.NoEncontradoExeption;
import interfaces.Fabrica;
import interfaces.IControladorDonacion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
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
        // Publica el servicio utilizando la configuración
        String wsIp = configuracion.getConfigOf("#WS_IP");
        String wsPort = configuracion.getConfigOf("#WS_PORT");

        // Formamos la URL del endpoint
        String url = "http://" + wsIp + ":" + wsPort + "/ControladorDonacionPublish";
        endpoint = Endpoint.publish(url, this);
        System.out.println(url); // Muestra la URL del servicio
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public boolean crearDonacion(DTAlimento dtSOAP){
        return icon.crearDonacion(dtSOAP);
    }

    @WebMethod
    public DtDonacionSOAP buscarDonacionID(Integer id) throws NoEncontradoExeption{
        return new DtDonacionSOAP(icon.buscarDonacionID(id));
    }

    @WebMethod
    public void editarDonacion(DtDonacionSOAP dtSOAP, Integer id) throws IngresoIncorrectoExeption{
        icon.editarDonacion(new DTDonacion(dtSOAP), id);
    }

//    @WebMethod
//    public List<DTAlimento> listarAlimentos(){
//        return icon.listarAlimentos();
//    }
//
//    @WebMethod
//    public List<DTArticulo> listarArticulos(){
//        return icon.listarArticulos();
//    }
//
//    @WebMethod
//    public List<DTDonacion> listarDonaciones(){
//        return icon.listarDonaciones();
//    }
}
