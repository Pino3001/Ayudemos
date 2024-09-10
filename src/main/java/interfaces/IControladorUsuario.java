package interfaces;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
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
    DtUsuario obtenerUsuarioPorEmail(String email);

    DtUsuario obtenerUsuarioPorId(Integer id);

    // Operación para listar todos los usuarios
    List<DtUsuario> listarUsuarios();


    void validarEmail(String email) throws EmailIncorrectoExeption;

    LocalDate parseFecha(String fechaStr) throws FormatoFechaIExeption;

    List<DtBeneficiario> listarBeneficiarios();

    // Operación para listar todos los usuarios segun su estado: EstadoDistribucion.
    List<DtBeneficiario> listarBeneficiariosPorEstado(EstadoBeneficiario estado);

    List<DtRepartidor> listarRepartidores();

    /**
     * Lista los beneficiarios que pertenecen a una zona específica.
     *
     * @param barrio La zona o barrio por el cual se filtrarán los beneficiarios.
     * @return Una lista de `DtBeneficiario` que pertenecen al barrio especificado.
     */
    List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio);

}
