package io.mudelephant.athlete.handler.listener;


import io.mudelephant.athlete.annotation.DBOperation;
import io.mudelephant.athlete.resource.MethodEntry;

import static io.mudelephant.db.EntityManagerManager.closeCurrentEntityManager;
import static io.mudelephant.db.EntityManagerManager.getOrOpenCurrentEntityManager;

public class DBExecuteListener implements ExecuteListener {

    @Override
    public void before(MethodEntry entry) {
        DBOperation operation = entry.getKey().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().begin();
        }
    }

    @Override
    public void success(MethodEntry entry) {
        DBOperation operation = entry.getKey().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().commit();
        }
        closeCurrentEntityManager();
    }

    @Override
    public void error(MethodEntry entry, Exception e) {
        DBOperation operation = entry.getKey().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().rollback();
        }
        closeCurrentEntityManager();
    }
}
