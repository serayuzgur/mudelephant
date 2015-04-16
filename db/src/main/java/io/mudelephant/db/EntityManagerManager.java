package io.mudelephant.db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

public class EntityManagerManager {


    private static EntityManagerManager instance;
    private static PersistenceProvider provider;
    private static EntityManagerFactory factory;

    private static ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();

    public static EntityManagerManager createInstance(EntityManagerFactory factory) {
        EntityManagerManager.factory = factory;
        instance = new EntityManagerManager();
        return instance;
    }

    private EntityManagerManager() {
    }


    public static EntityManager getOrOpenCurrentEntityManager() {
        if (currentEntityManager.get() == null || !currentEntityManager.get().isOpen())
            currentEntityManager.set(factory.createEntityManager());
        return currentEntityManager.get();
    }

    public static void closeCurrentEntityManager() {
        if (currentEntityManager.get() != null)
            currentEntityManager.get().close();
        currentEntityManager.set(null);
    }
}
