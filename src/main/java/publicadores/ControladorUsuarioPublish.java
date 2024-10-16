package publicadores;

import java.util.List;


import configuraciones.WebServiceConfiguracion;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.soap.DtUsuarioSOAP;
import excepciones.EmailIncorrectoExeption;
import excepciones.IngresoIncorrectoExeption;
import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import types.Barrio;
import types.EstadoBeneficiario;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorUsuarioPublish {
    private Fabrica fabrica;
    private IControladorUsuario icon;
    private WebServiceConfiguracion configuracion;
    private Endpoint endpoint;

    public ControladorUsuarioPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getIControladorUsuario();
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
        String url = "http://" + wsIp + ":" + wsPort + "/ControladorUsuarioPublish";
        endpoint = Endpoint.publish(url, this);
        System.out.println(url); // Muestra la URL del servicio
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void agregarUsuario(DtUsuarioSOAP dtSOAP) throws IngresoIncorrectoExeption {
        icon.agregarUsuario(new DtUsuario(dtSOAP));
    }

    @WebMethod
    public void modificarUsuario(DtUsuarioSOAP dtSOAP, Integer id) {
        icon.modificarUsuario(new DtUsuario(dtSOAP), id);
    }

    @WebMethod
    public DtUsuarioSOAP obtenerUsuarioPorId(Integer id) {
        return new DtUsuarioSOAP(icon.obtenerUsuarioPorId(id));
    }
//
//    @WebMethod
//    public List<DtUsuario> listarUsuarios(){
//        return icon.listarUsuarios();
//    }
//
//    @WebMethod
//    public List<DtBeneficiario> listarBeneficiarios(){
//        return icon.listarBeneficiarios();
//    }
//
//    @WebMethod
//    public List<DtBeneficiario> listarBeneficiariosPorEstado(EstadoBeneficiario estado){
//        return icon.listarBeneficiariosPorEstado(estado);
//    }
//
//    @WebMethod
//    public List<DtRepartidor> listarRepartidores(){
//        return icon.listarRepartidores();
//    }

    @WebMethod
    public void validarEmail(String email) throws EmailIncorrectoExeption {
        icon.validarEmail(email);
    }

//    @WebMethod
//    public List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio) {
//        return icon.listarBeneficiariosPorZona(barrio);
//    }

    @WebMethod
    public boolean existeUsuario(String email) {
        return icon.existeUsuario(email);
    }
}
