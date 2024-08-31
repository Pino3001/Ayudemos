package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    private static Conexion instancia = null;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    // Constructor privado
    private Conexion() {
        // Inicializa el EntityManagerFactory y el EntityManager
        emf = Persistence.createEntityManagerFactory("hibernate");
        em = emf.createEntityManager();
    }

    // Singleton con sincronización
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Obtener EntityManager
    public EntityManager getEntityManager() {
        return em;
    }

    // Cerrar conexión
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
