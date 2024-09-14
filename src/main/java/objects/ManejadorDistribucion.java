package objects;

import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import jakarta.persistence.EntityManager;
import org.hibernate.query.NativeQuery;
import persistencia.Conexion;
import types.Barrio;
import types.EstadoDistribucion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    // Busca una distribución por emailBeneficiario e idDonacion en la base de datos
    public DtDistribucion buscarDistribucion(int idUsuario, int idDonacion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Distribucion d WHERE d.beneficiario.id = :idUsuario AND d.donacion.id = :idDonacion", Distribucion.class)
                    .setParameter("idUsuario", idUsuario)
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
            // Búsqueda utilizando idDonacion, emailBeneficiario y fechaPreparacion para asegurarse de que es único
            List<Distribucion> distribuciones = em.createQuery(
                            "SELECT d FROM Distribucion d WHERE d.donacion.id = :idDonacion AND d.beneficiario.id = :idUsuario AND d.fechaPreparacion = :fechaPreparacion",
                            Distribucion.class)
                    .setParameter("idDonacion", dtDistribucion.getIdDonacion())
                    .setParameter("idUsuario", dtDistribucion.getIdUsuario())
                    .setParameter("fechaPreparacion", dtDistribucion.getFechaPreparacion())
                    .getResultList();

            if (distribuciones.size() == 1) {
                // Solo modificar si existe exactamente una distribución
                Distribucion distribucionExistente = distribuciones.get(0);
                distribucionExistente.setFechaEntrega(dtDistribucion.getFechaEntrega());
                distribucionExistente.setEstado(dtDistribucion.getEstado());

                em.getTransaction().begin();
                em.merge(distribucionExistente);
                em.getTransaction().commit();
            } else if (distribuciones.isEmpty()) {
                throw new IllegalArgumentException("No se encontró ninguna distribución con los criterios especificados.");
            } else {
                throw new IllegalArgumentException("Se encontraron múltiples distribuciones con los mismos criterios. Verifique los datos.");
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;  // Re-lanzar la excepción para manejarla en niveles superiores si es necesario
        } finally {
            em.close();
        }
    }

    // Retorna una lista de todas las distribuciones del sistema.
    private List<Distribucion> obtenerListaDistribucionesClase() {
        // Conexion y Entity Manager
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        try {
            return em.createQuery("SELECT d from Distribucion d", Distribucion.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DtReporteZona> obtenerReporteZonas(LocalDate fechaInicial, LocalDate fechaFinal) {

        // Conexion y Entity Manager
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();

        try {
            // Usamos createNativeQuery para poder ejecutar una consulta 'pelada'.
            NativeQuery<Object[]> query = (NativeQuery<Object[]>) em.createNativeQuery("SELECT b.barrio, COUNT(d.id), COUNT(DISTINCT b.id) " +
                    "FROM distribucion d " +
                    "JOIN beneficiario b ON d.beneficiario_id = b.id " +
                    "WHERE d.fechaentrega BETWEEN :fechaInicial AND :fechaFinal AND d.fechaentrega IS NOT NULL " +
                    "GROUP BY b.barrio");

            // Seteamos los parámetros.
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);

            // Ejecutamos la consulta.
            List<Object[]> resultList = query.getResultList();

            // Vamos a guardar los datos en una List<>.
            List<DtReporteZona> reporteZonas = new ArrayList<>();

            // Para cada item del resultado
            for (Object[] result : resultList) {
                String barrio = (String) result[0]; // Assuming the barrio is a String
                Long totalDistribuciones = (Long) result[1]; // The count of distribuciones
                Long totalBeneficiarios = (Long) result[2]; // The count of distinct beneficiarios

                // Creamos un nuevo DtReporteZona y lo añadimos a la lista a retornar
                DtReporteZona reporte = new DtReporteZona(barrio, totalDistribuciones.intValue(), totalBeneficiarios.intValue());
                reporteZonas.add(reporte);
            }

            // Retornamos la lista.
            return reporteZonas;

        } finally {
            em.close();
        }
    }
}




