package io.mudelephant.hibernate;

import javax.persistence.Cache;
import java.io.Serializable;

/**
 * Created by serayuzgur on 18/01/16.
 */
public class MudCache implements Cache {
    private final org.hibernate.Cache cache;

    public MudCache(org.hibernate.Cache cache) {
        this.cache = cache;
    }

    @Override
    public boolean contains(Class aClass, Object o) {
        return cache.containsEntity(aClass, (Serializable) o);
    }

    @Override
    public void evict(Class aClass, Object o) {
        cache.evictEntity(aClass, (Serializable) o);

    }

    @Override
    public void evict(Class aClass) {
        cache.evictEntityRegion(aClass);
    }

    @Override
    public void evictAll() {
cache.evictAllRegions();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
