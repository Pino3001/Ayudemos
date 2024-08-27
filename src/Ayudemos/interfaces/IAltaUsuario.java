package Ayudemos.interfaces;

import Ayudemos.datatypes.DtBeneficiario;
import Ayudemos.datatypes.DtRepartidor;
import Ayudemos.datatypes.DtUsuario;
import Ayudemos.excepciones.EmailIncorrectoExeption;
import Ayudemos.excepciones.FormatoFechaIExeption;
import Ayudemos.excepciones.IngresoIncorrectoExeption;
import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Usuario;
import Ayudemos.types.Barrio;
import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;

import java.util.List;

public interface IAltaUsuario {

    // Operación para agregar un usuario
    void agregarUsuario(DtUsuario dtusuario) throws IngresoIncorrectoExeption;

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

    Beneficiario crearBeneficiario(String nombre, String email, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio);

    Usuario crearRepartidor(String nombre, String email, String numeroLicencia);

    void validarEmail(String email) throws EmailIncorrectoExeption;

    DTFecha parseFecha(String fechaStr) throws FormatoFechaIExeption;

    List<DtBeneficiario> listarBeneficiarios();

    List<DtRepartidor> listarRepartidores();
}
