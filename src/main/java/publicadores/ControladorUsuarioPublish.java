package publicadores;

import java.time.LocalDate;
import java.util.List;


import configuraciones.WebServiceConfiguracion;
import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.soap.DtBeneficiarioSOAP;
import datatypes.soap.DtRepartidorSOAP;
import datatypes.soap.DtUsuarioSOAP;
import excepciones.EmailIncorrectoExeption;
import excepciones.IngresoIncorrectoExeption;
import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import objects.Beneficiario;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;

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
    public void agregarUsuario(DtBeneficiarioSOAP dtSOAP) throws IngresoIncorrectoExeption {
        // Convertir XMLGregorianCalendar a LocalDate usando DateConverterSOAP
        LocalDate fechaNacimiento = DateConverterSOAP.toLocalDate(dtSOAP.getFechaNacimiento());

        // Crear el objeto DtBeneficiario con todos los campos convertidos
        DtBeneficiario beneficiario = new DtBeneficiario(
                dtSOAP.getId(),
                dtSOAP.getNombre(),
                dtSOAP.getMail(),
                dtSOAP.getDireccion(),
                fechaNacimiento,               // Fecha convertida a LocalDate
                dtSOAP.getEstado(),             // Estado del beneficiario
                dtSOAP.getBarrio(),             // Barrio
                dtSOAP.getContrasenia()         // Contraseña
        );

        // Llamar a la lógica de negocio con el objeto beneficiario
        icon.agregarUsuario(beneficiario);
    }


    @WebMethod
    public void agregarUsuarioRepartidor(DtRepartidorSOAP dtSOAP) throws IngresoIncorrectoExeption {
        icon.agregarUsuario(new DtUsuario(dtSOAP));
    }

    @WebMethod
    public void modificarUsuario(DtUsuarioSOAP dtSOAP, Integer id) {
        //icon.modificarUsuario (dtSOAP, id);
        if (dtSOAP instanceof DtBeneficiarioSOAP) {
            icon.modificarUsuario(new DtBeneficiario((DtBeneficiarioSOAP) dtSOAP), id);
        }else if(dtSOAP instanceof DtRepartidorSOAP){
            icon.modificarUsuario(new DtRepartidor((DtRepartidorSOAP) dtSOAP), id);
        }
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

    @WebMethod
    public boolean autenticar(String email, String password) {
        return icon.autenticar(email, password);
    }

    @WebMethod
    public DtUsuarioSOAP obtenerUsuarioPorMail(String email) {
        return icon.obtenerUsuarioPorMail(email);
    }

    @WebMethod
    public XMLGregorianCalendar convertirFecha(LocalDate fecha) {
        return DateConverterSOAP.toXMLGregorianCalendar(fecha);
    }
}
