package Ayudemos.interfaces;

import Ayudemos.objects.Usuario;
import Ayudemos.types.Barrio;
import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;

import java.util.List;

public interface IAltaUsuario  {

    // Operación para agregar un usuario
    void agregarUsuario(Usuario usuario);

    // Operación para eliminar un usuario por su email
    void eliminarUsuario(String email);

    // Operación para actualizar la información de un usuario
    void actualizarUsuario(Usuario usuario);

    // Operación para obtener un usuario por su email
    Usuario obtenerUsuarioPorEmail(String email);

    // Operación para listar todos los usuarios
    List<Usuario> listarUsuarios();

    Usuario crearBeneficiario(String nombre, String email, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio);

    Usuario crearRepartidor(String nombre, String email, String numeroLicencia);

    boolean validarEmail(String email);

    DTFecha parseFecha(String fechaStr) throws Exception;

}
