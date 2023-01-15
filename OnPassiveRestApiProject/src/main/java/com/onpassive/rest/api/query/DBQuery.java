package com.onpassive.rest.api.query;

public class DBQuery {
    public static final String SEARCH_BY_ID = "select t from RecentTransaction t where t.transactionId like :key";
    public static final String FIND_TRANSACTION_BETWEEN_DATE = "SELECT * FROM PAYMENT WHERE (date BETWEEN :startDate AND :endDate)";
    public static final String FIND_TRANSACTION_BY_DATE = "SELECT * FROM PAYMENT WHERE date >= :startDate AND date <= :endDate";
    public static final String GET_TRANSACTION_BY_DATE = "select p.transaction_id, p.date,p.credits,p.payment_type,p.status,p.transaction_type from PAYMENT p where p.date between :startDate and :endDate";
    public static final String FIND_ALL_TRANSACTION_DATE_RANAGE = "FROM RecentTransaction WHERE date BETWEEN :startTime AND :endTime";
    public static final String SEARCH_KEY = "FROM RecentTransaction WHERE CONCAT(transaction_id,date,credits,payment_type,status,transaction_type) LIKE %?1%";
}
