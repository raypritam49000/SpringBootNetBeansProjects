package com.onpassive.rest.api.repository;

import com.onpassive.rest.api.entity.RecentTransaction;
import java.util.Date;
import java.util.List;

public interface CustomeJpaRepository {
    public abstract List<RecentTransaction> findAllTransactionByDate(Date startDate,Date endDate);
}
