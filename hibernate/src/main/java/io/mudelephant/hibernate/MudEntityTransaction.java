package io.mudelephant.hibernate;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.EntityTransaction;

/**
 * Created by serayuzgur on 18/01/16.
 */
public class MudEntityTransaction implements EntityTransaction {
    private Transaction transaction;

    public MudEntityTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void begin() {
        transaction.begin();
    }

    @Override
    public void commit() {
        transaction.commit();
    }

    @Override
    public void rollback() {
        transaction.rollback();
    }

    @Override
    public void setRollbackOnly() {
        transaction.markRollbackOnly();
    }

    @Override
    public boolean getRollbackOnly() {
        return transaction.getStatus().equals(TransactionStatus.MARKED_ROLLBACK);
    }

    @Override
    public boolean isActive() {
        return transaction.getStatus().equals(TransactionStatus.ACTIVE);
    }
}
