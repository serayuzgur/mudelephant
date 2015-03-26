package io.mudelephant.batoo;

import io.mudelephant.batoo.configuration.HasBatooConfiguration;
import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads entities and initializes Batoo
 */
public class BatooModule<T extends HasBatooConfiguration> extends Module<T> {

    private final String[] entityNames;

    public BatooModule(Class[] classes) {
        entityNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++)
            entityNames[i] = classes[i].getName();
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) {
        Map properties = new HashMap();

        properties.put("javax.persistence.jdbc.driver", configuration.getBatoo().getDriver());
        properties.put("javax.persistence.jdbc.url", configuration.getBatoo().getUrl());
        properties.put("javax.persistence.jdbc.user", configuration.getBatoo().getUser());
        properties.put("javax.persistence.jdbc.password", configuration.getBatoo().getPassword());
        properties.put("org.batoo.jpa.ddl", configuration.getBatoo().getDdl());
        EntityManagerManager.createEntityManagerFactory("Batoo",properties,entityNames);
    }
}
