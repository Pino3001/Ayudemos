package objects;

import datatypes.DtDistribucion;
import datatypes.DtReporteZona;
import datatypes.soap.DtDistribucionSOAP;
import excepciones.IngresoIncorrectoExeption;
import jakarta.persistence.EntityManager;
import org.hibernate.query.NativeQuery;
import persistencia.Conexion;
import types.Barrio;
import types.EstadoDistribucion;

import java.time.LocalDate;
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
            return em.createQuery("SELECT d from Distribucion d ORDER BY d.id", Distribucion.class)
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
    public DtDistribucion buscarDistribucion(Integer idDistribucion) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.find(Distribucion.class, idDistribucion).getDtDistribucion();
    }

    // Modifica una distribución existente en la base de datos
    public void modificarDistribucion(DtDistribucion dtDistribucion) throws IngresoIncorrectoExeption {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        Distribucion distribucionExistente = em.find(Distribucion.class, dtDistribucion.getId());
        try {
            if (distribucionExistente == null) {
                throw new IngresoIncorrectoExeption("No existe la distribucion!");
            } else if (dtDistribucion.getEstado().equals(EstadoDistribucion.PENDIENTE) && dtDistribucion.getFechaEntrega() != null) {
                throw new IngresoIncorrectoExeption("Estado de distribucion y \n fecha de entrega no compatibles");
            } else if (!dtDistribucion.getEstado().equals(EstadoDistribucion.PENDIENTE) && dtDistribucion.getFechaEntrega() == null) {
                throw new IngresoIncorrectoExeption("Estado de distribucion y \n fecha de entrega no compatibles");
            } else {
                distribucionExistente.setFechaEntrega(dtDistribucion.getFechaEntrega());
                distribucionExistente.setEstado(dtDistribucion.getEstado());

                em.getTransaction().begin();
                em.merge(distribucionExistente);
                em.getTransaction().commit();
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
                    "GROUP BY b.barrio " +
                    "ORDER BY COUNT(d.id) DESC");

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

    public DtDistribucionSOAP[] listaDistribucionesPendientesSOAP() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        EstadoDistribucion estadoDistribucion = EstadoDistribucion.PENDIENTE;

        try {
            // Realizamos la consulta para obtener la lista de Distribuciones con estado pendiente
            List<Distribucion> distribuciones = em.createQuery(
                            "SELECT d FROM Distribucion d WHERE d.estado = 'PENDIENTE'", Distribucion.class)
                    .getResultList();

            // Convertimos la lista de Distribucion a un array de DtDistribucionSOAP
            return distribuciones.stream()
                    .map(distribucion -> {
                        // Convertimos de Distribucion a DtDistribucion
                        DtDistribucion dtDistribucion = distribucion.getDtDistribucionWeb();
                        // Luego convertimos de DtDistribucion a DtDistribucionSOAP
                        return new DtDistribucionSOAP(dtDistribucion);
                    })
                    .toArray(DtDistribucionSOAP[]::new);
        } finally {
            em.close();
        }
    }
}




