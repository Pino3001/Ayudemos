package Ayudemos.objects;

import Ayudemos.datatypes.DtBeneficiario;
import Ayudemos.datatypes.DtRepartidor;
import Ayudemos.datatypes.DtUsuario;
import Ayudemos.excepciones.EmailIncorrectoExeption;
import Ayudemos.excepciones.FormatoFechaIExeption;
import Ayudemos.excepciones.IngresoIncorrectoExeption;
import Ayudemos.interfaces.IAltaUsuario;
import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;
import Ayudemos.types.Barrio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AltaUsuario implements IAltaUsuario {

    private Map<String, Usuario> usuarios = new HashMap<>();
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void agregarUsuario(DtUsuario dtUsuario) throws IngresoIncorrectoExeption {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        Usuario usuario = null;
        if (!manejadorUsuario.verificarMail(dtUsuario.getMail())) {
            if (dtUsuario instanceof DtBeneficiario) {
                usuario = new Beneficiario(dtUsuario.getNombre(),
                        dtUsuario.getMail(),
                        ((DtBeneficiario) dtUsuario).getDireccion(),
                        ((DtBeneficiario) dtUsuario).getFechaNacimiento(),
                        EstadoBeneficiario.ACTIVO,
                        ((DtBeneficiario) dtUsuario).getBarrio());
                manejadorUsuario.agregarUsuario(usuario);
            } else {
                usuario = new Repartidor(dtUsuario.getNombre(),
                        dtUsuario.getMail(),
                        ((DtRepartidor) dtUsuario).getNumeroLicencia());
                manejadorUsuario.agregarUsuario(usuario);
            }
        } else {
            throw new IngresoIncorrectoExeption("Formato de correo electrónico incorrecto");
        }
    }

    @Override
    public void modificarUsuario(DtUsuario dtUsuario, String eMail){

    }

    @Override
    public void eliminarUsuario(String email) {
        usuarios.remove(email);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getMail())) {
            usuarios.put(usuario.getMail(), usuario);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarios.get(email);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarios.values().stream().collect(Collectors.toList());
    }

    public Beneficiario crearBeneficiario(String nombre, String email, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        Beneficiario beneficiario = new Beneficiario(nombre, email, direccion, fechaNacimiento, estado, barrio);
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        manejadorUsuario.agregarUsuario(beneficiario);
        return beneficiario;
    }

    public Usuario crearRepartidor(String nombre, String email, String numeroLicencia) {
        return new Repartidor(nombre, email, numeroLicencia);
    }

    public void validarEmail(String email) throws EmailIncorrectoExeption {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (!pattern.matcher(email).matches()) {
            throw new EmailIncorrectoExeption("Formato de correo electrónico incorrecto");
        }
    }

    public DTFecha parseFecha(String fechaStr) throws FormatoFechaIExeption {
        String[] partes = fechaStr.split("/");
        if (partes.length != 3) {
            throw new FormatoFechaIExeption("Formato de fecha inválido");
        }
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return new DTFecha(dia, mes, anio);
    }

    //Lista los beneficiarios alojados en el sistema
    @Override
    public List<DtBeneficiario> listarBeneficiarios() {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        List<DtBeneficiario> beneficiarios = manejadorUsuario.obtenerBeneficiarios();
        return beneficiarios;
    }

    //Lista los beneficiarios alojados en el sistema
    @Override
    public List<DtRepartidor> listarRepartidores() {
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        List<DtRepartidor> repartidores = manejadorUsuario.obtenerRepartidores();
        return repartidores;
    }
}