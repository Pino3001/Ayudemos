package interfaces;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import datatypes.soap.DtUsuarioSOAP;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import types.Barrio;
import types.EstadoBeneficiario;

import java.time.LocalDate;
import java.util.List;

public interface IControladorUsuario {

    // Operación para agregar un usuario
    void agregarUsuario(DtUsuario dtusuario) throws IngresoIncorrectoExeption;//

    //Operacion para modificar un usuario.
    void modificarUsuario(DtUsuario dtUsuario, Integer id);

    // Operación para eliminar un usuario por su email
    //void eliminarUsuario(DtUsuario dtUsuario);

    // Operación para obtener un usuario por su email
//    DtUsuario obtenerUsuarioPorEmail(String email);

    DtUsuario obtenerUsuarioPorId(Integer id);

    // Operación para listar todos los usuarios
    List<DtUsuario> listarUsuarios();


    void validarEmail(String email) throws EmailIncorrectoExeption;

    LocalDate parseFecha(String fechaStr) throws FormatoFechaIExeption;

    List<DtBeneficiario> listarBeneficiarios();

    // Operación para listar todos los usuarios segun su estado: EstadoDistribucion.
    List<DtBeneficiario> listarBeneficiariosPorEstado(EstadoBeneficiario estado);

    List<DtRepartidor> listarRepartidores();

    // Operación para listar todos los usuarios segun su zona: Barrio.
    List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio);

    // Retorna true si existe el usuario, false en caso contrario.
    boolean existeUsuario(String email);

    // Autentica un usuario.
    boolean autenticar(String email, String password);

    // Devuelve un DtUsuarioSOAP de un usuario dada su dirección de correo.
    DtUsuarioSOAP obtenerUsuarioPorMail(String email);

}
