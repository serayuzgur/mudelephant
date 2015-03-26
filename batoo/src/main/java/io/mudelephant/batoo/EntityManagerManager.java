package io.mudelephant.batoo;

import org.batoo.jpa.core.BatooPersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

public class EntityManagerManager {

    private static EntityManagerFactory factory;

    private static ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();

    protected static void createEntityManagerFactory(String name, Map properties, String[] entityNames) {
        BatooPersistenceProvider provider = new BatooPersistenceProvider();
        factory = provider.createEntityManagerFactory("BatooEMF", properties, entityNames);
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
