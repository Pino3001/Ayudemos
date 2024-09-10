package objects;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import interfaces.IAltaUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import types.EstadoBeneficiario;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AltaUsuario implements IAltaUsuario {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void agregarUsuario(DtUsuario dtUsuario) throws IngresoIncorrectoExeption {
        try {
            // Verificar formato de email
            validarEmail(dtUsuario.getMail());
            // Creo el Usuario
            Usuario usuario = null;
            if (dtUsuario instanceof DtBeneficiario) {
                usuario = new Beneficiario(dtUsuario.getNombre(),
                        dtUsuario.getMail(),
                        ((DtBeneficiario) dtUsuario).getDireccion(),
                        ((DtBeneficiario) dtUsuario).getFechaNacimiento(),
                        ((DtBeneficiario) dtUsuario).getEstado(),
                        ((DtBeneficiario) dtUsuario).getBarrio()
                );
            } else if (dtUsuario instanceof DtRepartidor) {
                usuario = new Repartidor(dtUsuario.getNombre(),
                        dtUsuario.getMail(),
                        ((DtRepartidor) dtUsuario).getNumeroLicencia()
                );
            }
            if (usuario != null) {
                ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
                manejadorUsuario.agregarUsuario(usuario);
            } else throw new IngresoIncorrectoExeption("No se a podido crear el Usuario correctamente");

        } catch (EmailIncorrectoExeption e) {
            throw new IngresoIncorrectoExeption("Formato de eMail incorrecto");
        }
    }

    @Override
    public void modificarUsuario(DtUsuario dtUsuario, Integer id) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        try {
            Usuario usuario = manejadorUsuario.buscarUsuario(id);
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no existe");
            } else {
                manejadorUsuario.actualizarUsuario(usuario, dtUsuario);
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Error al modificar el usuario: " + dtUsuario.getNombre());
        }
    }

/*    @Override
    public void eliminarUsuario(DtUsuario dtUsuario) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();

        Usuario usuario = manejadorUsuario.buscarUsuario(dtUsuario.getId());
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        } else {
            manejadorUsuario.removeUsuario(usuario);
        }
    }*/

    @Override
    public DtUsuario obtenerUsuarioPorEmail(String email) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerUsuarioEmail(email);
    }

    @Override
    public DtUsuario obtenerUsuarioPorId(Integer id) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerUsuarioID(id);
    }

    @Override
    public List<DtUsuario> listarUsuarios() {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerUsuarios();
    }

    @Override
    public List<DtBeneficiario> listarBeneficiarios() {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerBeneficiarios();
    }

    @Override
    // Listar beneficiarios según su estado.
    public List<DtBeneficiario> listarBeneficiariosPorEstado(EstadoBeneficiario estado) {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerBeneficiariosEstado(estado);
    }

    @Override
    public List<DtRepartidor> listarRepartidores() {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        return manejadorUsuario.obtenerRepartidores();
    }

    @Override
    public void validarEmail(String email) throws EmailIncorrectoExeption {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (!pattern.matcher(email).matches()) {
            throw new EmailIncorrectoExeption("Formato de correo electrónico incorrecto");
        }
    }

    public LocalDate parseFecha(String fechaStr) throws FormatoFechaIExeption {
        String[] partes = fechaStr.split("/");
        if (partes.length != 3) {
            throw new FormatoFechaIExeption("Formato de fecha inválido");
        }
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return LocalDate.of(anio, mes, dia);
    }

    @Override
    public void existeUsuario(String email) throws EmailIncorrectoExeption{
        ManejadorUsuario mu= ManejadorUsuario.getInstance();
        if(mu.existeUsuario(email)){
            throw new EmailIncorrectoExeption("Mail ya utilizado");
        }
    }
}