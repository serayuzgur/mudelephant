package io.mudelephant.athlete.handler.listener;


import io.mudelephant.athlete.annotation.DBOperation;
import io.mudelephant.athlete.resource.ServiceInfo;

import static io.mudelephant.db.EntityManagerManager.closeCurrentEntityManager;
import static io.mudelephant.db.EntityManagerManager.getOrOpenCurrentEntityManager;

public class DBExecuteListener implements ExecuteListener {

    @Override
    public void before(ServiceInfo entry) {
        DBOperation operation = entry.getMethod().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().begin();
        }
    }

    @Override
    public void success(ServiceInfo entry) {
        DBOperation operation = entry.getMethod().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().commit();
        }
        closeCurrentEntityManager();
    }

    @Override
    public void error(ServiceInfo entry, Exception e) {
        DBOperation operation = entry.getMethod().getAnnotation(DBOperation.class);
        if (operation != null) {
            if (operation.transactional())
                getOrOpenCurrentEntityManager().getTransaction().rollback();
        }
        closeCurrentEntityManager();
    }
}
