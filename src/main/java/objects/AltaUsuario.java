package objects;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import excepciones.EmailIncorrectoExeption;
import excepciones.FormatoFechaIExeption;
import excepciones.IngresoIncorrectoExeption;
import interfaces.IAltaUsuario;
import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                ParseException e = null;
                throw new IngresoIncorrectoExeption("El correo electrónico ya está registrado", e);
            }

            Usuario usuario = null;
            Integer id = 0;
            if (dtUsuario instanceof DtBeneficiario) {
                DtBeneficiario dtBeneficiario = (DtBeneficiario) dtUsuario;
                Date fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(dtBeneficiario.getFechaNacimiento().toString());
                usuario = new Beneficiario(
                        dtBeneficiario.getNombre(),
                        dtBeneficiario.getMail(),
                        dtBeneficiario.getDireccion(),
                        fechaNacimiento,
                        dtBeneficiario.getEstado(),
                        dtBeneficiario.getBarrio(),
                        id
                );
            } else if (dtUsuario instanceof DtRepartidor) {
                DtRepartidor dtRepartidor = (DtRepartidor) dtUsuario;
                usuario = new Repartidor(
                        dtRepartidor.getNombre(),
                        dtRepartidor.getMail(),
                        dtRepartidor.getNumeroLicencia(),
                        id
                );
            }

            if (usuario != null) {
                em.persist(usuario);
            }

            tx.commit();
        } catch (ParseException e) {
            throw new IngresoIncorrectoExeption("Error al parsear la fecha", e);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new IngresoIncorrectoExeption("Error al agregar el usuario", (ParseException) e);
        } finally {
            em.close();
        }
    }

    @Override
    public void modificarUsuario(DtUsuario dtUsuario, String email) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Usuario usuario = em.find(Usuario.class, email);
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }

            usuario.setNombre(dtUsuario.getNombre());
            usuario.setMail(dtUsuario.getMail());

            if (dtUsuario instanceof DtBeneficiario) {
                Beneficiario beneficiario = (Beneficiario) usuario;
                beneficiario.setDireccion(((DtBeneficiario) dtUsuario).getDireccion());
                beneficiario.setFechaNacimiento(((DtBeneficiario) dtUsuario).getFechaNacimiento());
                beneficiario.setEstado(((DtBeneficiario) dtUsuario).getEstado());
                beneficiario.setBarrio(((DtBeneficiario) dtUsuario).getBarrio());
            } else if (dtUsuario instanceof DtRepartidor) {
                Repartidor repartidor = (Repartidor) usuario;
                repartidor.setNumeroLicencia(((DtRepartidor) dtUsuario).getNumeroLicencia());
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
    public void eliminarUsuario(String email) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Usuario usuario = em.find(Usuario.class, email);
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
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Usuario usuarioExistente = em.find(Usuario.class, usuario.getMail());
            if (usuarioExistente == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }

            // Actualizar atributos del usuario existente
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setMail(usuario.getMail());

            // Guardar los cambios
            em.merge(usuario);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, email);
        } finally {
            em.close();
        }
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
    public void crearBeneficiario(DtBeneficiario dtBeneficiario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Beneficiario beneficiario = null;

        try {
            tx.begin();

            // Convertir DTFecha a java.util.Date
            Date fechaNacimientoDate = new SimpleDateFormat("dd/MM/yyyy").parse(
                    dtBeneficiario.getFechaNacimiento().toString()
            );

            // Crear el nuevo Beneficiario
            beneficiario = new Beneficiario(
                    dtBeneficiario.getNombre(),
                    dtBeneficiario.getMail(),
                    dtBeneficiario.getDireccion(),
                    fechaNacimientoDate,
                    dtBeneficiario.getEstado(),
                    dtBeneficiario.getBarrio()
            );

            // Persistir el beneficiario en la base de datos
            em.persist(beneficiario);

            tx.commit();
        } catch (ParseException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al parsear la fecha", e);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el beneficiario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void crearRepartidor(DtRepartidor dtRepartidor) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Crear el nuevo Repartidor a partir del DTO
            Repartidor repartidor = new Repartidor(
                    dtRepartidor.getNombre(),
                    dtRepartidor.getMail(),
                    dtRepartidor.getNumeroLicencia()
            );

            // Persistir el repartidor en la base de datos
            em.persist(repartidor);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el repartidor", e);
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
    public Usuario crearRepartidor(String nombre, String email, String numeroLicencia) {
        return null;
    }

    @Override
    public Usuario crearBeneficiario(String nombre, String email, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        return null;
    }

    @Override
    public void validarEmail(String email) throws EmailIncorrectoExeption {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (!pattern.matcher(email).matches()) {
            throw new EmailIncorrectoExeption("Formato de correo electrónico incorrecto");
        }
    }

    public Date parseFecha(String fechaStr) throws FormatoFechaIExeption {
        String[] partes = fechaStr.split("/");
        if (partes.length != 3) {
            throw new FormatoFechaIExeption("Formato de fecha inválido");
        }
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return new Date(dia, mes, anio);
    }
}