package Ayudemos.objects;

import Ayudemos.datatypes.DtBeneficiario;
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
    public void agregarUsuario(Usuario usuario) {
        if (!usuarios.containsKey(usuario.getMail())) {
            if (validarEmail(usuario.getMail())) {
                usuarios.put(usuario.getMail(), usuario);
            } else {
                throw new IllegalArgumentException("Formato de correo electrónico incorrecto");
            }
        } else {
            throw new IllegalArgumentException("Ya existe un usuario con el email " + usuario.getMail());
        }
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

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    public DTFecha parseFecha(String fechaStr) throws Exception {
        String[] partes = fechaStr.split("/");
        if (partes.length != 3) {
            throw new Exception("Formato de fecha inválido");
        }
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return new DTFecha(dia, mes, anio);
    }

    public List<DtBeneficiario> listarBeneficiarios(){
        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
        List<DtBeneficiario> beneficiarios = manejadorUsuario.obtenerBeneficiarios();
        return beneficiarios;
    }
}