package objects;

import datatypes.DtBeneficiario;
import datatypes.DtRepartidor;
import datatypes.DtUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import persistencia.Conexion;
import types.EstadoBeneficiario;
import types.Barrio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManejadorUsuario {
    private static ManejadorUsuario instance = null;

    private ManejadorUsuario() {
    }

    public static ManejadorUsuario getInstance() {
        if (instance == null) {
            instance = new ManejadorUsuario();
        }
        return instance;
    }

    // Agrega donacion a la lista de usuarios existentes.
    public void agregarUsuario(Usuario usuario) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    // Modifica una instancia de usuario con los datos pasados en el dtUsuario
    public void actualizarUsuario(Usuario usuario, DtUsuario dtUsuario) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            usuario.setNombre(dtUsuario.getNombre());
            usuario.setMail(dtUsuario.getMail());
            if (usuario instanceof Beneficiario beneficiario && dtUsuario instanceof DtBeneficiario) {
                beneficiario.setDireccion(((DtBeneficiario) dtUsuario).getDireccion());
                beneficiario.setFechaNacimiento(((DtBeneficiario) dtUsuario).getFechaNacimiento());
                beneficiario.setEstado(((DtBeneficiario) dtUsuario).getEstado());
                beneficiario.setBarrio(((DtBeneficiario) dtUsuario).getBarrio());
            } else if (usuario instanceof Repartidor repartidor && dtUsuario instanceof DtRepartidor) {
                repartidor.setNumeroLicencia(((DtRepartidor) dtUsuario).getNumeroLicencia());
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Busca y devuelve un usuario con el id pasado
    public Usuario buscarUsuario(Integer id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        return em.find(Usuario.class, id);
    }


/*    public boolean verificarMail(String mail) {
        boolean existe = false;
        for(Usuario u : usuarios) {
            if(u.getMail().equals(mail)) {
                existe = true;
                break;
            }
        }
        return existe;
    }*/

    // Devuelve el usuario con el eMail pasado
    public DtUsuario obtenerUsuarioEmail(String email) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.find(Usuario.class, email).getDtUsuario();
    }

    // Devuelve el usuario con el ID pasado
    public DtUsuario obtenerUsuarioID(Integer id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.find(Usuario.class, id).getDtUsuario();
    }

    // Retorna una lista datatypes de todas los usuarios del sistema.
    public List<DtUsuario> obtenerUsuarios() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList()
                .stream()
                .map(Usuario::getDtUsuario)
                .collect(Collectors.toList());
    }

    public List<DtBeneficiario> obtenerBeneficiarios() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.createQuery("SELECT b FROM Beneficiario b", Beneficiario.class)
                .getResultList()
                .stream()
                .map(Beneficiario::getDtUsuario)
                .collect(Collectors.toList());
    }

    // Devuelve una lista de DtBeneficiario filtrada por el estado.
    public List<DtBeneficiario> obtenerBeneficiariosEstado(EstadoBeneficiario estado) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.createQuery("SELECT b FROM Beneficiario b WHERE b.estado = :estado", Beneficiario.class)
                .setParameter("estado", estado)
                .getResultList()
                .stream()
                .map(Beneficiario::getDtUsuario)
                .collect(Collectors.toList());
    }

    public List<DtBeneficiario> obtenerBeneficiariosPorZona(Barrio barrio) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.createQuery("SELECT b FROM Beneficiario b WHERE b.barrio = :barrio", Beneficiario.class)
                .setParameter("barrio", barrio)
                .getResultList()
                .stream()
                .map(Beneficiario::getDtUsuario)
                .collect(Collectors.toList());
    }

    public List<DtRepartidor> obtenerRepartidores() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.createQuery("SELECT r FROM Repartidor r", Repartidor.class)
                .getResultList()
                .stream()
                .map(Repartidor::getDtUsuario)
                .collect(Collectors.toList());
    }


    // Busca una donación por ID en la lista de usuarios y retorna la información en un dt.
//    public DTDonacion buscarBeneficiarioID(Integer id) {
//        DtBeneficiario dt = null;
//        Beneficiario beneficiario = null;
//        boolean encontrado = false;
//        int i = 0;
//
//        while (i < usuarios.size() && !encontrado) {
//            if (usuarios.get(i).getId().equals(id)) {
//                beneficiario = usuarios.get(i);
//                encontrado = true;
//            }
//            i++;
//        }
//        if (encontrado) {
//            if (usuarios instanceof Articulo) {
//                dt = new DTArticulo(donacion.getId(), donacion.getFechaIngresada(), ((Articulo) donacion).getDescripcion(), ((Articulo) donacion).getPeso(), ((Articulo) donacion).getDimensiones());
//            } else {
//                dt = new DTAlimento(donacion.getId(), donacion.getFechaIngresada(), ((Alimento) donacion).getDescripcionProductos(), ((Alimento) donacion).getCantElementos());
//            }
//            return dt;
//        }
//        return dt;
//    }
//
//    // Modifica una donación.
//    public void modificarDonacion(DTDonacion dtDonacion, Integer id) {
//        Donacion donacion = null;
//        boolean encontrado = false;
//        int i = 0;
//
//        while (i < donaciones.size() && !encontrado) {
//            if (donaciones.get(i).getId().equals(id)) {
//                donacion = donaciones.get(i);
//                encontrado = true;
//            }
//            i++;
//        }
//        if (encontrado) {
//            if (donacion instanceof Articulo) {
//                if (dtDonacion instanceof DTArticulo) {
//                    donacion.setId(dtDonacion.getId());
//                    ((Articulo) donacion).setDescripcion(((DTArticulo) dtDonacion).getDescripcion());
//                    ((Articulo) donacion).setPeso(((DTArticulo) dtDonacion).getPeso());
//                    ((Articulo) donacion).setDimensiones(((DTArticulo) dtDonacion).getDimensiones());
//                }
//                // Retornar un error si no se cumple las condiciones.
//            } else {
//                if (dtDonacion instanceof DTAlimento) {
//                    donacion.setId(dtDonacion.getId());
//                    ((Alimento) donacion).setDescripcionProductos(((DTAlimento) dtDonacion).getDescripcionProductos());
//                    ((Alimento) donacion).setCantElementos(((DTAlimento) dtDonacion).getCantElementos());
//                }
//                // Retornar el error correspondiente.
//            }
//        }
//    }

}
