package objects;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import persistencia.Conexion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManejadorDonacion {
    private static ManejadorDonacion instance = null;

    private ManejadorDonacion() {
    }

    // Singleton
    public static ManejadorDonacion getInstance() {
        if (instance == null) {
            instance = new ManejadorDonacion();
        }
        return instance;
    }

    // Retorna una lista datatypes de todas las donaciones del sistema.
    public List<DTDonacion> obtenerDonaciones() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Donacion d", Donacion.class)
                    .getResultList()
                    .stream()
                    .map(Donacion::getDTDonacion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Agrega donacion a la lista de donaciones existentes.
    public void agregarDonacion(Donacion donacion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(donacion);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }


    // Busca una donación por ID en la lista de donaciones y retorna la información en un dt.
    // TODO: Imlpementar Exepciones y ver si se puede mejorar
    public DTDonacion buscarDonacionID(Integer id) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            Donacion donacion = em.find(Donacion.class, id);
            if (donacion == null) throw new IllegalArgumentException("La Donacion no existe");
            else if (donacion instanceof Alimento) {
                return new DTAlimento(donacion.getId(),
                        donacion.getFechaIngresada(),
                        ((Alimento) donacion).getDescripcionProductos(),
                        ((Alimento) donacion).getCantElementos());
            } else if (donacion instanceof Articulo) {
                return new DTArticulo(donacion.getId(), donacion.getFechaIngresada(), ((Articulo) donacion).getDescripcion(), ((Articulo) donacion).getPeso(), ((Articulo) donacion).getDimensiones());
            }
        } finally {
            em.close();
        }
        return null;
    }

    // Modifica una donación.
    public void modificarDonacion(DTDonacion dtDonacion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Donacion d = em.find(Donacion.class, dtDonacion.getId());
            if (d == null) {
                throw new IllegalArgumentException("La Donacion no existe");
            }

            // Actualiza los atributos de la donación
            d.setFechaIngresada(dtDonacion.getFechaIngresada());

            // Verifica y actualiza las subclases de donación
            if (d instanceof Alimento alimento && dtDonacion instanceof DTAlimento) {
                alimento.setDescripcionProductos(((DTAlimento) dtDonacion).getDescripcionProductos());
                alimento.setCantElementos(((DTAlimento) dtDonacion).getCantElementos());
            } else if (d instanceof Articulo articulo && dtDonacion instanceof DTArticulo) {
                articulo.setDescripcion(((DTArticulo) dtDonacion).getDescripcion());
                articulo.setPeso(((DTArticulo) dtDonacion).getPeso());
                articulo.setDimensiones(((DTArticulo) dtDonacion).getDimensiones());
            } else {
                throw new IllegalArgumentException("El tipo de Donacion o los datos no son válidos");
            }

            // Confirma la transacción
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al modificar la donación", e);
        } finally {
            em.close();
        }
    }

    // Obtiene una lista de DTAlimentos
    public List<DTAlimento> listarAlimentosManejador() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT alimento FROM Alimento alimento", Alimento.class)
                    .getResultList()
                    .stream()
                    .map(Alimento::getDTDonacion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Obtiene una lista de DTArticulos
    public List<DTArticulo> listarArticulosManejador() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT articulo FROM Articulo articulo", Articulo.class)
                    .getResultList()
                    .stream()
                    .map(Articulo::getDTDonacion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

}
