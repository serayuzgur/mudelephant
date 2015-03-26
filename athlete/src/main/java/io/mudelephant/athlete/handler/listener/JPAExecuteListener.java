package io.mudelephant.athlete.handler.listener;


import io.mudelephant.athlete.annotation.JPAOperation;
import io.mudelephant.athlete.resource.MethodEntry;

import static io.mudelephant.batoo.EntityManagerManager.closeCurrentEntityManager;
import static io.mudelephant.batoo.EntityManagerManager.getOrOpenCurrentEntityManager;

public class JPAExecuteListener implements ExecuteListener {

    @Override
    public void before(MethodEntry entry) {
        JPAOperation operation = entry.getKey().getAnnotation(JPAOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().begin();
        }
    }

    @Override
    public void success(MethodEntry entry) {
        JPAOperation operation = entry.getKey().getAnnotation(JPAOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().commit();
        }
        closeCurrentEntityManager();
    }

    @Override
    public void error(MethodEntry entry, Exception e) {
        JPAOperation operation = entry.getKey().getAnnotation(JPAOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().rollback();
        }
        closeCurrentEntityManager();
    }
}
