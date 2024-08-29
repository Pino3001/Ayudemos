package interfaces;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import objects.Usuario;
import types.Barrio;
import types.EstadoBeneficiario;

import java.util.Date;
import java.util.List;

public interface IAltaUsuario {

    // Operación para agregar un usuario
    void agregarUsuario(DtUsuario dtusuario) throws IngresoIncorrectoExeption;//

    //Operacion para modificar un usuario.
    public void modificarUsuario(DtUsuario dtUsuario, String eMail);

    // Operación para eliminar un usuario por su email
    void eliminarUsuario(String email);

    // Operación para actualizar la información de un usuario
    void actualizarUsuario(Usuario usuario);

    // Operación para obtener un usuario por su email
    Usuario obtenerUsuarioPorEmail(String email);

    // Operación para listar todos los usuarios
    List<Usuario> listarUsuarios();

    void crearBeneficiario(DtBeneficiario dtBeneficiario);

    void crearRepartidor(DtRepartidor dtRepartidor);

    void validarEmail(String email) throws EmailIncorrectoExeption;

    Date parseFecha(String fechaStr) throws FormatoFechaIExeption;

    List<DtBeneficiario> listarBeneficiarios();

    List<DtRepartidor> listarRepartidores();

    Usuario crearRepartidor(String nombre, String email, String numeroLicencia);

    Usuario crearBeneficiario(String nombre, String email, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio);
}
