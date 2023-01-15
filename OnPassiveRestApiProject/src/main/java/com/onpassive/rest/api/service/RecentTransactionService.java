package com.onpassive.rest.api.service;

import com.onpassive.rest.api.dto.RecentTransactionDto;
import com.onpassive.rest.api.response.TransactionResponse;
import java.util.Date;
import java.util.List;


public interface RecentTransactionService {
    public RecentTransactionDto createTransaction(RecentTransactionDto recentTransactionDto);
    public RecentTransactionDto updateTransaction(Long id,RecentTransactionDto recentTransactionDto);
    public RecentTransactionDto getTransaction(Long id);
    public List<RecentTransactionDto> getAllTransactions();
    public Boolean deleteTransaction(Long id);
    public TransactionResponse getAllTransactionByPagination(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    public abstract List<RecentTransactionDto> searchById(long id);
    public abstract Boolean deleteAllTransactions();
    public List<RecentTransactionDto> createAllTransactions(List<RecentTransactionDto> recentTransactionDtoList);
    public abstract List<RecentTransactionDto> findByDateBetween(Date startDate, Date endDate);
    public abstract List<RecentTransactionDto> findAllTransactionBetweenDate(Date startDate,Date endDate);
    public abstract List<RecentTransactionDto> findTransactionByDate(Date startDate,Date endDate);
    public abstract List<RecentTransactionDto> getTransactionByDate(Date startDate,Date endDate);
    public abstract List<RecentTransactionDto> findAllTransactionWithDateRange(Date startTime,Date endTime);
    public List<RecentTransactionDto> findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(Long startId, Long endId);
    public abstract List<RecentTransactionDto> findByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate);
    public List<RecentTransactionDto> findAllTransactionByDate(Date startDate, Date endDate);
    public List<RecentTransactionDto> findAllDetailWithSorting(String sortBy, String sortDir);
}
