package io.mudelephant.hibernate;

import io.mudelephant.core.Bootstrap;
import io.mudelephant.core.Module;
import io.mudelephant.db.EntityManagerManager;
import io.mudelephant.db.configuration.HasDBConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateModule<T extends HasDBConfiguration> extends Module<T> {


    private final String[] entityNames;

    public HibernateModule(Class[] classes) {
        entityNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++)
            entityNames[i] = classes[i].getName();
    }

    @Override
    public void run(Bootstrap bootstrap, T configuration) throws ClassNotFoundException {
        Properties properties = new Properties();

        properties.put(AvailableSettings.DRIVER, configuration.getDatabase().getDriver());
        properties.put(AvailableSettings.URL, configuration.getDatabase().getUrl());
        properties.put(AvailableSettings.USER, configuration.getDatabase().getUser());
        properties.put(AvailableSettings.PASS, configuration.getDatabase().getPassword());
        properties.putAll(configuration.getDatabase().getProperties());


        Configuration hConfig = new Configuration();
        for (String entity : entityNames) {
            hConfig.addAnnotatedClass(Class.forName(entity));
        }
        hConfig.addProperties(properties);
        hConfig.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "managed");
        hConfig.setProperty(AvailableSettings.USE_SQL_COMMENTS, "false");
        hConfig.setProperty(AvailableSettings.USE_GET_GENERATED_KEYS, "true");
        hConfig.setProperty(AvailableSettings.GENERATE_STATISTICS, "true");
        hConfig.setProperty(AvailableSettings.USE_REFLECTION_OPTIMIZER, "true");
        hConfig.setProperty(AvailableSettings.ORDER_UPDATES, "true");
        hConfig.setProperty(AvailableSettings.ORDER_INSERTS, "true");
        hConfig.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true");
        SessionFactory factory = hConfig.buildSessionFactory();


        EntityManagerManager.createInstance(new MudEntityManagerFactory(factory));
    }
}

