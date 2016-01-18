package io.mudelephant.hibernate;

import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by serayuzgur on 05/11/15.
 */
public class MudEntityManager implements EntityManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MudEntityManager.class);
    private final EntityManagerFactory emf;

    private Session session;

    private ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<>();


    public MudEntityManager(Session session, EntityManagerFactory emf) {
        this.session = session;
        this.emf = emf;
    }

    @Override
    public void persist(Object o) {
        session.persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return (T) session.merge(t);
    }

    @Override
    public void remove(Object o) {
        session.delete(o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o) {
        return session.get(aClass, (Serializable) o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        return find(aClass, o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        return session.get(aClass, (Serializable) o, LockMode.valueOf(lockModeType.name()));
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        return find(aClass, o, lockModeType);
    }

    @Override
    public <T> T getReference(Class<T> aClass, Object o) {
        return find(aClass, o);
    }

    @Override
    public void flush() {
        session.flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        session.setFlushMode(FlushMode.valueOf(flushModeType.name()));
    }

    @Override
    public FlushModeType getFlushMode() {
        return FlushModeType.valueOf(session.getFlushMode().name());
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        session.lock(o, LockMode.valueOf(lockModeType.name()));
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        LOGGER.warn("This method is not implemented properly.");
        lock(o, lockModeType);

    }

    @Override
    public void refresh(Object o) {
        session.refresh(o);

    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        LOGGER.warn("This method is not implemented properly.");
        refresh(o);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        session.refresh(o, LockMode.valueOf(lockModeType.name()));

    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        LOGGER.warn("This method is not implemented properly.");
        refresh(o, lockModeType);
    }

    @Override
    public void clear() {
        session.clear();
    }

    @Override
    public void detach(Object o) {
        LOGGER.warn("This method is not implemented properly.");
    }

    @Override
    public boolean contains(Object o) {
        return session.contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return LockModeType.valueOf(session.getCurrentLockMode(o).name());
    }

    @Override
    public void setProperty(String s, Object o) {
        properties.put(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public Query createQuery(String s) {
        return null;
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return null;
    }

    @Override
    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return null;
    }

    @Override
    public Query createQuery(CriteriaDelete criteriaDelete) {
        return null;
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public Query createNamedQuery(String s) {
        return null;
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public Query createNativeQuery(String s) {
        return null;
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return null;
    }

    @Override
    public Query createNativeQuery(String s, String s1) {
        return null;
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return null;
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return null;
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return null;
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return null;
    }

    @Override
    public void joinTransaction() {

    }

    @Override
    public boolean isJoinedToTransaction() {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

    @Override
    public Object getDelegate() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public EntityTransaction getTransaction() {
        return new MudEntityTransaction(session.getTransaction());
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return this.emf;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return null;
    }

    @Override
    public Metamodel getMetamodel() {
        return null;
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return null;
    }

    @Override
    public EntityGraph<?> createEntityGraph(String s) {
        return null;
    }

    @Override
    public EntityGraph<?> getEntityGraph(String s) {
        return null;
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return null;
    }
}
