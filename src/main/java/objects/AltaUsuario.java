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
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AltaUsuario implements IAltaUsuario {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("hibernate").createEntityManager();
    }

    @Override
    public void agregarUsuario(DtUsuario dtUsuario) throws IngresoIncorrectoExeption {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // Verificar formato de email
            validarEmail(dtUsuario.getMail());
            // Verificar si el usuario ya existe
            Usuario usuarioExistente = em.find(Usuario.class, dtUsuario.getMail());
            if (usuarioExistente != null) {
                throw new IngresoIncorrectoExeption("El correo electrónico ya está registrado");
            }
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
                em.persist(usuario);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new IngresoIncorrectoExeption("Error al agregar el usuario");
        } finally {
            em.close();
        }
    }

    @Override
    public void modificarUsuario(DtUsuario dtUsuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, dtUsuario.getId());
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }

            usuario.setNombre(dtUsuario.getNombre());
            usuario.setMail(dtUsuario.getMail());

            if (usuario instanceof Beneficiario beneficiario && dtUsuario instanceof DtBeneficiario) {
                beneficiario.setDireccion(((DtBeneficiario) dtUsuario).getDireccion());
                beneficiario.setFechaNacimiento(((DtBeneficiario) dtUsuario).getFechaNacimiento());
                beneficiario.setEstado(((DtBeneficiario) dtUsuario).getEstado());
                beneficiario.setBarrio(((DtBeneficiario) dtUsuario).getBarrio());
            } else if (usuario instanceof Repartidor repartidor && dtUsuario instanceof DtRepartidor) {
                repartidor.setNumeroLicencia(((DtRepartidor) dtUsuario).getNumeroLicencia());
            } else {
                throw new IllegalArgumentException("El usuario no existe");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al modificar el usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarUsuario(String id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }
            em.remove(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public DtUsuario obtenerUsuarioPorEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Usuario usr = em.find(Usuario.class, email);
            if (usr == null) throw new IllegalArgumentException("El usuario no existe");
            else if (usr instanceof Beneficiario) {
                return new DtBeneficiario(usr.getId(),
                        usr.getNombre(),
                        usr.getMail(),
                        ((Beneficiario) usr).getDireccion(),
                        ((Beneficiario) usr).getFechaNacimiento(),
                        ((Beneficiario) usr).getEstado(),
                        ((Beneficiario) usr).getBarrio());
            } else if (usr instanceof Repartidor) {
                return new DtRepartidor(usr.getId(),
                        usr.getNombre(),
                        usr.getMail(),
                        ((Repartidor) usr).getNumeroLicencia());
            }
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public DtUsuario obtenerUsuarioPorId(String id) {
        EntityManager em = getEntityManager();
        try {
            Usuario usr = em.find(Usuario.class, id);
            if (usr == null) throw new IllegalArgumentException("El usuario no existe");
            else if (usr instanceof Beneficiario) {
                return new DtBeneficiario(usr.getId(),
                        usr.getNombre(),
                        usr.getMail(),
                        ((Beneficiario) usr).getDireccion(),
                        ((Beneficiario) usr).getFechaNacimiento(),
                        ((Beneficiario) usr).getEstado(),
                        ((Beneficiario) usr).getBarrio());
            } else if (usr instanceof Repartidor) {
                return new DtRepartidor(usr.getId(),
                        usr.getNombre(),
                        usr.getMail(),
                        ((Repartidor) usr).getNumeroLicencia());
            }
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<DtBeneficiario> listarBeneficiarios() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Beneficiario b", Beneficiario.class)
                    .getResultList()
                    .stream()
                    .map(Beneficiario::getDTBeneficiario)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public List<DtRepartidor> listarRepartidores() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Repartidor r", Repartidor.class)
                    .getResultList()
                    .stream()
                    .map(Repartidor::getDTRepartidor)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
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
}