package com.onpassive.rest.api.repository;

import com.onpassive.rest.api.entity.RecentTransaction;
import com.onpassive.rest.api.query.DBQuery;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CustomeJpaRepositoryImpl implements CustomeJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RecentTransaction> findAllTransactionByDate(Date startDate, Date endDate) {
        TypedQuery<RecentTransaction> query = entityManager.createQuery(DBQuery.FIND_ALL_TRANSACTION_DATE_RANAGE, RecentTransaction.class);
        query.setParameter("startTime", startDate);
        query.setParameter("endTime", endDate);
        List<RecentTransaction> recentTransactionList = query.getResultList();
        return recentTransactionList;
    }

}
