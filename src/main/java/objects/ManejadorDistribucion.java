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

    private static ManejadorDistribucion instacia = null;

    private List<Distribucion> distribuciones = new ArrayList<>();

    private ManejadorDistribucion() {
    }

    public static ManejadorDistribucion getInstance() {
        if (instacia == null)
            instacia = new ManejadorDistribucion();
        return instacia;

    }

    // Retorna una lista de datatypes de distribuciones segun el estado
    public List<DtDistribucion> buscarDistribucionesPorEstado(EstadoDistribucion estado) {
        // Conexion y Entity Manager
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d from Distribucion d WHERE d.estado = :estado ", Distribucion.class)
                    .setParameter("estado", estado)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }

    }

    // Retorna una lista de datatypes de todas las distribuciones del sistema.
    public List<DtDistribucion> obtenerListaDistribuciones() {
        // Conexion y Entity Manager
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            return em.createQuery("SELECT d from Distribucion d", Distribucion.class)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Retorna una lista de datatypes de todas las distribuciones del sistema filtradas por zona.
    public List<DtDistribucion> obtenerListaDistribucionesZona(Barrio barrio) {
        // Conexion y Entity Manager
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d from Distribucion d JOIN Beneficiario b ON d.beneficiario.id = b.id WHERE b.barrio = :barrio", Distribucion.class)
                    .setParameter("barrio", barrio)
                    .getResultList()
                    .stream()
                    .map(Distribucion::getDtDistribucion)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    // Crea una nueva distribución.
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

    public DtDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion) {
        for (Distribucion distribucion : distribuciones) {
            if (distribucion.getBeneficiario().getMail().equals(emailBeneficiario)
                    && distribucion.getDonacion().getId() == idDonacion) {
                return new DtDistribucion(distribucion.getFechaPreparacion(),distribucion.getFechaEntrega(),distribucion.getEstado(),distribucion.getDonacion().getId(),distribucion.getBeneficiario().getNombre(), distribucion.getBeneficiario().getMail());
            }
        }
        return null;
    }

    // Modifica una distribución existente
    public void modificarDistribucion(DtDistribucion dtDistribucion) {
        for (Distribucion distribucion : distribuciones) {
            if (distribucion.getBeneficiario().getMail().equals(dtDistribucion.getEmailBeneficiario()) && distribucion.getDonacion().getId() == dtDistribucion.getIdDonacion()) {
                distribucion.setFechaPreparacion(dtDistribucion.getFechaPreparacion());
                distribucion.setFechaEntrega(dtDistribucion.getFechaEntrega());
                distribucion.setEstado(dtDistribucion.getEstado());
            }
        }
    }

}




