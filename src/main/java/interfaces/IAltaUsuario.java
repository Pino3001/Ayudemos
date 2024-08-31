package interfaces;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import objects.Usuario;
import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IAltaUsuario {

    // Operación para agregar un usuario
    void agregarUsuario(DtUsuario dtusuario) throws IngresoIncorrectoExeption;//

    //Operacion para modificar un usuario.
    void modificarUsuario(DtUsuario dtUsuario);

    // Operación para eliminar un usuario por su email
    void eliminarUsuario(String id);

    // Operación para obtener un usuario por su email
    DtUsuario obtenerUsuarioPorEmail(String email);

    DtUsuario obtenerUsuarioPorId(String id);

    // Operación para listar todos los usuarios
    List<Usuario> listarUsuarios();

    void validarEmail(String email) throws EmailIncorrectoExeption;

    LocalDate parseFecha(String fechaStr) throws FormatoFechaIExeption;

    List<DtBeneficiario> listarBeneficiarios();

    List<DtRepartidor> listarRepartidores();

}
