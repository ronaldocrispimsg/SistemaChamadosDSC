package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    
    private static final String PERSISTENCE_UNIT = "SistemaDeChamadosPU";
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return factory.createEntityManager();
    }
    
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}