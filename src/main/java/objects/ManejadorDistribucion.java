package objects;

import datatypes.DtDistribucion;
import jakarta.persistence.EntityManager;
import persistencia.Conexion;
import types.Barrio;
import types.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ManejadorDistribucion {

    private static ManejadorDistribucion instacia = null;

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

    public Map<Barrio, List<DtDistribucion>> obtenerReporteZonas(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        // Filtramos las distribuciones por fecha.
        List<Distribucion> distribuciones = obtenerListaDistribucionesClase();
        List<Distribucion> distribucionesFiltradas = new ArrayList<Distribucion>();
        for (Distribucion d : distribuciones) {
            if (d.getFechaEntrega().isAfter(fechaInicial) && d.getFechaEntrega().isBefore(fechaFinal)) {
                distribucionesFiltradas.add(d);
            }
        }

        // Mapa para agrupar distribuciones por barrio (zona).
        Map<Barrio, List<DtDistribucion>> distribucionesPorZona = new HashMap<>();

        // Agrupamos  según el barrio las distribuciones que filtramos por fecha.
        for (Distribucion d : distribucionesFiltradas) {
            Barrio zona = d.getBeneficiario().getBarrio();
            DtDistribucion dt = new DtDistribucion(d.getFechaPreparacion(), d.getFechaEntrega(), d.getEstado(), d.getDonacion().getId(), d.getBeneficiario().getNombre(), d.getBeneficiario().getMail());

            distribucionesPorZona.computeIfAbsent(zona, k -> new ArrayList<>()).add(dt);
        }

//        // Creamos la lista de DtReporteZona a partir del mapa agrupado.
//        Map<Barrio, DtReporteZona> reporteZonas = new HashMap<>();
//
//        for (Map.Entry<Barrio, List<DtDistribucion>> entry : distribucionesPorZona.entrySet()) {
//            DtReporteZona reporteZona = new DtReporteZona(entry.getValue(), entry.getKey());
//            reporteZonas.compute(reporteZona.getZona(), reporteZona);
//        }
        return distribucionesPorZona;
    }
}




