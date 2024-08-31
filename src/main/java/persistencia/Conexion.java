package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
    private static Conexion instancia = null;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private Conexion() {
    }


    // Singleton
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
            emf = Persistence.createEntityManagerFactory("hibernate");
            em = emf.createEntityManager();
        }
        return instancia;
    }

    // Entity manager
    public EntityManager getEntityManager() {
        return this.em;
    }

    // Cerrar conexión
    public void close() {
        this.em.close();
        this.emf.close();
    }


}
