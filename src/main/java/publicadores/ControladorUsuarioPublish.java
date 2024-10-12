package publicadores;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuraciones.WebServiceConfiguracion;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.soap.DtUsuarioSOAP;
import excepciones.EmailIncorrectoExeption;
import excepciones.IngresoIncorrectoExeption;
import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import types.Barrio;
import types.EstadoBeneficiario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
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
        endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorUsuario", this);
        System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorUsuario");
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

    @WebMethod
    public List<DtUsuario> listarUsuarios(){
        return icon.listarUsuarios();
    }

    @WebMethod
    public List<DtBeneficiario> listarBeneficiarios(){
        return icon.listarBeneficiarios();
    }

    @WebMethod
    public List<DtBeneficiario> listarBeneficiariosPorEstado(EstadoBeneficiario estado){
        return icon.listarBeneficiariosPorEstado(estado);
    }

    @WebMethod
    public List<DtRepartidor> listarRepartidores(){
        return icon.listarRepartidores();
    }

    @WebMethod
    public void validarEmail(String email) throws EmailIncorrectoExeption {
        icon.validarEmail(email);
    }

    @WebMethod
    public List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio) {
        return icon.listarBeneficiariosPorZona(barrio);
    }

    @WebMethod
    public boolean existeUsuario(String email) {
        return icon.existeUsuario(email);
    }
}
