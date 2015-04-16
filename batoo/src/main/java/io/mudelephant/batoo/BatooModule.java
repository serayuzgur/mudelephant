package io.mudelephant.batoo;

import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.db.EntityManagerManager;
import io.mudelephant.db.configuration.HasDBConfiguration;
import org.batoo.jpa.core.BatooPersistenceProvider;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads entities and initializes Batoo
 */
public class BatooModule<T extends HasDBConfiguration> extends Module<T> {

    private final String[] entityNames;

    public BatooModule(Class[] classes) {
        entityNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++)
            entityNames[i] = classes[i].getName();
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        Map properties = new HashMap<String, String>();

        properties.put("javax.persistence.jdbc.driver", configuration.getDatabase().getDriver());
        properties.put("javax.persistence.jdbc.url", configuration.getDatabase().getUrl());
        properties.put("javax.persistence.jdbc.user", configuration.getDatabase().getUser());
        properties.put("javax.persistence.jdbc.password", configuration.getDatabase().getPassword());
        properties.put("org.batoo.jpa.ddl", configuration.getDatabase().getDdl());

        BatooPersistenceProvider provider = new BatooPersistenceProvider();
        EntityManagerFactory factory = provider.createEntityManagerFactory("Batoo", properties, entityNames);
        EntityManagerManager.createInstance(factory);
    }
}
