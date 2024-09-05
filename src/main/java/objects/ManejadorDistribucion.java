package objects;

import datatypes.DtDistribucion;
import jakarta.persistence.EntityManager;
import persistencia.Conexion;
import types.Barrio;
import types.EstadoDistribucion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManejadorDistribucion {

    private static ManejadorDistribucion instancia = null;

    // Lista local de distribuciones (aunque en realidad las consultas son hacia la base de datos)
    private List<Distribucion> distribuciones = new ArrayList<>();

    private ManejadorDistribucion() {
    }

    public static ManejadorDistribucion getInstance() {
        if (instancia == null) {
            instancia = new ManejadorDistribucion();
        }
        return instancia;
    }

    // Retorna una lista de datatypes de distribuciones según el estado
    public List<DtDistribucion> buscarDistribucionesPorEstado(EstadoDistribucion estado) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Distribucion d WHERE d.estado = :estado", Distribucion.class)
                    .setParameter("estado", estado)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion) // Conversión a DtDistribucion
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Retorna una lista de datatypes de todas las distribuciones del sistema.
    public List<DtDistribucion> obtenerListaDistribuciones() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Distribucion d", Distribucion.class)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Retorna una lista de distribuciones filtradas por barrio
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Distribucion d JOIN d.beneficiario b WHERE b.barrio = :barrio", Distribucion.class)
                    .setParameter("barrio", barrio)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Crea una nueva distribución
    public void agregarDistribucion(Distribucion distribucion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(distribucion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    // Busca una distribución por emailBeneficiario e idDonacion en la base de datos
    public DtDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Distribucion d WHERE d.beneficiario.mail = :email AND d.donacion.id = :idDonacion", Distribucion.class)
                    .setParameter("email", emailBeneficiario)
                    .setParameter("idDonacion", idDonacion)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    // Modifica una distribución existente en la base de datos
    public void modificarDistribucion(DtDistribucion dtDistribucion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            em.getTransaction().begin();
            Distribucion distribucion = em.createQuery(
                            "SELECT d FROM Distribucion d WHERE d.beneficiario.mail = :email AND d.donacion.id = :idDonacion",
                            Distribucion.class)
                    .setParameter("email", dtDistribucion.getEmailBeneficiario())
                    .setParameter("idDonacion", dtDistribucion.getIdDonacion())
                    .getSingleResult();

            if (distribucion != null) {
                distribucion.setFechaPreparacion(dtDistribucion.getFechaPreparacion());
                distribucion.setFechaEntrega(dtDistribucion.getFechaEntrega());
                distribucion.setEstado(dtDistribucion.getEstado());
                em.merge(distribucion);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
