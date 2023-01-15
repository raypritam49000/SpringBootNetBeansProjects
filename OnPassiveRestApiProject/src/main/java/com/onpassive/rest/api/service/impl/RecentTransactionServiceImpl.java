package com.onpassive.rest.api.service.impl;

import com.onpassive.rest.api.dto.RecentTransactionDto;
import com.onpassive.rest.api.entity.RecentTransaction;
import com.onpassive.rest.api.repository.RecentTransactionRepository;
import com.onpassive.rest.api.response.TransactionResponse;
import com.onpassive.rest.api.service.RecentTransactionService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecentTransactionServiceImpl implements RecentTransactionService {

    @Autowired
    private RecentTransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecentTransactionDto createTransaction(RecentTransactionDto recentTransactionDto) {
        RecentTransaction recentTransaction = this.modelMapper.map(recentTransactionDto, RecentTransaction.class);
        recentTransaction.setDate(new Date());
        RecentTransaction createRecentTransaction = this.transactionRepository.save(recentTransaction);
        return this.modelMapper.map(createRecentTransaction, RecentTransactionDto.class);
    }

    @Override
    public RecentTransactionDto updateTransaction(Long id, RecentTransactionDto recentTransactionDto) {
        Optional<RecentTransaction> recentTransactionOptional = this.transactionRepository.findById(id);
        RecentTransaction updateRecentTransaction = null;
        if (recentTransactionOptional.isPresent()) {
            recentTransactionOptional.get().setCredits(recentTransactionDto.getCredits());
            recentTransactionOptional.get().setPaymentType(recentTransactionDto.getPaymentType());
            recentTransactionOptional.get().setTransactionType(recentTransactionDto.getTransactionType());
            recentTransactionOptional.get().setStatus(recentTransactionDto.getStatus());
            updateRecentTransaction = this.transactionRepository.save(recentTransactionOptional.get());
        }
        return this.modelMapper.map(updateRecentTransaction, RecentTransactionDto.class);
    }

    @Override
    public RecentTransactionDto getTransaction(Long id) {
        Optional<RecentTransaction> recentTransactionOptional = this.transactionRepository.findById(id);
        if (recentTransactionOptional.isPresent()) {
            return this.modelMapper.map(recentTransactionOptional.get(), RecentTransactionDto.class);
        } else {
            throw new IllegalArgumentException("Transaction Not Found with : " + id);
        }
    }

    @Override
    public List<RecentTransactionDto> getAllTransactions() {
        List<RecentTransaction> recentTransactionList = this.transactionRepository.findAll();
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public Boolean deleteTransaction(Long id) {
        Optional<RecentTransaction> recentTransactionOptional = this.transactionRepository.findById(id);
        if (recentTransactionOptional.isPresent()) {
            this.transactionRepository.delete(recentTransactionOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TransactionResponse getAllTransactionByPagination(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<RecentTransaction> pageTransaction = this.transactionRepository.findAll(pageable);
        List<RecentTransaction> allTransactions = pageTransaction.getContent();
        List<RecentTransactionDto> transactionostDtos = allTransactions.stream()
                .map((transaction) -> this.modelMapper.map(transaction, RecentTransactionDto.class))
                .collect(Collectors.toList());

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setContent(transactionostDtos);
        transactionResponse.setPageNumber(pageTransaction.getNumber());
        transactionResponse.setPageSize(pageTransaction.getSize());
        transactionResponse.setTotalElements(pageTransaction.getTotalElements());
        transactionResponse.setTotalPages(pageTransaction.getTotalPages());
        transactionResponse.setLastPage(pageTransaction.isLast());
        return transactionResponse;
    }

    @Override
    public List<RecentTransactionDto> searchById(long id) {
        List<RecentTransaction> recentTransactionList = this.transactionRepository.searchById(id);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public Boolean deleteAllTransactions() {
        this.transactionRepository.deleteAll();
        return true;
    }

    @Override
    public List<RecentTransactionDto> createAllTransactions(List<RecentTransactionDto> recentTransactionDtoList) {
        List<RecentTransaction> recentTransactionList = recentTransactionDtoList.stream()
                .map(recentTransactionDto -> this.modelMapper.map(recentTransactionDto, RecentTransaction.class)).collect(Collectors.toList());
        List<RecentTransaction> createRecentTransactionList = this.transactionRepository.saveAll(recentTransactionList);
        List<RecentTransactionDto> transactionDtoList = createRecentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return transactionDtoList;

    }

    @Override
    public List<RecentTransactionDto> findByDateBetween(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findByDateBetween(startDate, endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> findAllTransactionBetweenDate(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findAllTransactionBetweenDate(startDate, endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> findTransactionByDate(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findTransactionByDate(startDate, endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> getTransactionByDate(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.getTransactionByDate(startDate, endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> findAllTransactionWithDateRange(Date startTime, Date endTime) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findAllTransactionWithDateRange(startTime, endTime);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }
    
    @Override
     public List<RecentTransactionDto> findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(Long startId, Long endId){
          List<RecentTransaction> recentTransactionList = transactionRepository.findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(startId,endId);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
     }

    @Override
    public List<RecentTransactionDto> findByDateGreaterThanEqualAndDateLessThanEqual(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findByDateGreaterThanEqualAndDateLessThanEqual(startDate,endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> findAllTransactionByDate(Date startDate, Date endDate) {
        List<RecentTransaction> recentTransactionList = transactionRepository.findAllTransactionByDate(startDate,endDate);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

    @Override
    public List<RecentTransactionDto> findAllDetailWithSorting(String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<RecentTransaction> recentTransactionList = this.transactionRepository.findAll(sort);
        List<RecentTransactionDto> recentTransactionDtoList = recentTransactionList.stream()
                .map(recentTransaction -> this.modelMapper.map(recentTransaction, RecentTransactionDto.class)).collect(Collectors.toList());
        return recentTransactionDtoList;
    }

}
