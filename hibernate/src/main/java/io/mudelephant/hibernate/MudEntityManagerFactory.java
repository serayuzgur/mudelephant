package io.mudelephant.hibernate;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.Map;

/**
 * Created by serayuzgur on 05/11/15.
 */
public class MudEntityManagerFactory implements EntityManagerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(MudEntityManagerFactory.class);


    private SessionFactory factory;

    public MudEntityManagerFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public EntityManager createEntityManager() {
        return new MudEntityManager(factory.openSession(),this);
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        LOGGER.warn("This method is not implemented properly.");
        return new MudEntityManager(factory.openSession(),this);
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType) {
        LOGGER.warn("This method is not implemented properly.");
        return new MudEntityManager(factory.openSession(),this);    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
        LOGGER.warn("This method is not implemented properly.");
        return new MudEntityManager(factory.openSession(),this);    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        LOGGER.warn("This method is not implemented properly.");
        return null;
    }

    @Override
    public Metamodel getMetamodel() {
        LOGGER.warn("This method is not implemented properly.");

        return null;
    }

    @Override
    public boolean isOpen() {
        return !factory.isClosed();
    }

    @Override
    public void close() {
        factory.close();
    }

    @Override
    public Map<String, Object> getProperties() {
        return null;
    }

    @Override
    public Cache getCache() {
        return new MudCache(factory.getCache());
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        LOGGER.warn("This method is not implemented properly.");
        return null;
    }

    @Override
    public void addNamedQuery(String s, Query query) {

    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> void addNamedEntityGraph(String s, EntityGraph<T> entityGraph) {

    }
}
