package com.onpassive.rest.api.repository;

import com.onpassive.rest.api.entity.RecentTransaction;
import com.onpassive.rest.api.query.DBQuery;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentTransactionRepository extends JpaRepository<RecentTransaction, Long>, CustomeJpaRepository {

    @Query(DBQuery.SEARCH_BY_ID)
    public abstract List<RecentTransaction> searchById(@Param("key") long id);

    public abstract List<RecentTransaction> findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(Long startId, Long endId);

    // find Data Between date using jpql in jpa
    public abstract List<RecentTransaction> findByDateBetween(Date startDate, Date endDate);

    public abstract List<RecentTransaction> findByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate);

    @Query(DBQuery.FIND_ALL_TRANSACTION_DATE_RANAGE)
    List<RecentTransaction> findAllTransactionWithDateRange(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    // find Data Between date using native query in JPA 
    @Query(value = DBQuery.FIND_TRANSACTION_BETWEEN_DATE, nativeQuery = true)
    public abstract List<RecentTransaction> findAllTransactionBetweenDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = DBQuery.FIND_TRANSACTION_BY_DATE, nativeQuery = true)
    public abstract List<RecentTransaction> findTransactionByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = DBQuery.GET_TRANSACTION_BY_DATE, nativeQuery = true)
    public abstract List<RecentTransaction> getTransactionByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Filter 
    @Query(DBQuery.SEARCH_KEY)
    public abstract List<RecentTransaction> search(String keyword);

}
