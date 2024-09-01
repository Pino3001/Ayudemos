package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    private static Conexion instancia = null;
    private static EntityManagerFactory emf;

    // Constructor privado para inicializar el EntityManagerFactory
    private Conexion() {
        emf = Persistence.createEntityManagerFactory("hibernate");
    }

    // Function para obtener la instancia del Singleton
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Function para obtener un nuevo EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Function para cerrar el EntityManagerFactory al finalizar la aplicación
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
