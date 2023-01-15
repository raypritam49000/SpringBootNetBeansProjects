package com.onpassive.rest.api.controllers;

import com.onpassive.rest.api.cache.SimpleCacheManager;
import com.onpassive.rest.api.config.AppConstants;
import com.onpassive.rest.api.dto.RecentTransactionDto;
import com.onpassive.rest.api.response.ApiResponse;
import com.onpassive.rest.api.response.TransactionResponse;
import com.onpassive.rest.api.service.RecentTransactionService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class RecentTransactionController {

    @Autowired
    private RecentTransactionService transactionService;

    @Autowired
    private SimpleCacheManager cacheManager;

    @GetMapping("/reloadCache")
    public ResponseEntity<ApiResponse> reloadCache() {
        this.cacheManager.clear();
        List<RecentTransactionDto> recentTransactionDtoList = transactionService.getAllTransactions();
        recentTransactionDtoList.forEach((RecentTransactionDto recentTransactionDto) -> {
            cacheManager.add("" + recentTransactionDto.getTransactionId(), recentTransactionDto);
        });
        return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Cache Reload Successfully", Arrays.asList(), true), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTransactions() {
        try {
            if (!cacheManager.isEmpty()) {
                List<RecentTransactionDto> recentTransactionDtoList = new ArrayList<>();

                cacheManager.getCache().entrySet().forEach((entry) -> {
                    RecentTransactionDto recentTransactionDto = (RecentTransactionDto) entry.getValue();
                    recentTransactionDtoList.add(recentTransactionDto);
                });

                if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            } else {
                List<RecentTransactionDto> recentTransactionDtoList = transactionService.getAllTransactions();

                if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            if (!cacheManager.isEmpty()) {
                RecentTransactionDto recentTransactionDto = (RecentTransactionDto) cacheManager.get("" + id);
                if (recentTransactionDto != null && recentTransactionDto.getCredits() != null && !recentTransactionDto.getCredits().equals("")
                        && recentTransactionDto.getTransactionType() != null && !recentTransactionDto.getTransactionType().equals("")
                        && recentTransactionDto.getPaymentType() != null && !recentTransactionDto.getPaymentType().equals("")
                        && recentTransactionDto.getStatus() != null && !recentTransactionDto.getStatus().equals("")
                        && recentTransactionDto.getDate() != null && !recentTransactionDto.getDate().equals("")) {

                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", Arrays.asList(recentTransactionDto), true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            } else {
                RecentTransactionDto recentTransactionDto = transactionService.getTransaction(id);

                if (recentTransactionDto != null && recentTransactionDto.getCredits() != null && !recentTransactionDto.getCredits().equals("")
                        && recentTransactionDto.getTransactionType() != null && !recentTransactionDto.getTransactionType().equals("")
                        && recentTransactionDto.getPaymentType() != null && !recentTransactionDto.getPaymentType().equals("")
                        && recentTransactionDto.getStatus() != null && !recentTransactionDto.getStatus().equals("")
                        && recentTransactionDto.getDate() != null && !recentTransactionDto.getDate().equals("")) {

                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", Arrays.asList(recentTransactionDto), true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Student Detail Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody RecentTransactionDto recentTransactionDto) {
        try {

            if (recentTransactionDto != null && recentTransactionDto.getCredits() != null && !recentTransactionDto.getCredits().equals("")
                    && recentTransactionDto.getTransactionType() != null && !recentTransactionDto.getTransactionType().equals("")
                    && recentTransactionDto.getPaymentType() != null && !recentTransactionDto.getPaymentType().equals("")
                    && recentTransactionDto.getStatus() != null && !recentTransactionDto.getStatus().equals("")) {

                RecentTransactionDto addTransactionDto = transactionService.createTransaction(recentTransactionDto);
                return new ResponseEntity<>(new ApiResponse(201, "CREATED", "Transaction Created", Arrays.asList(addTransactionDto), true), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTransaction(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            Boolean isTransactionDeleted = transactionService.deleteTransaction(id);

            if (isTransactionDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transaction Detail Deleted", Arrays.asList(), true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Transaction", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable("id") long id, @RequestBody RecentTransactionDto recentTransactionDto) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            if (recentTransactionDto != null && recentTransactionDto.getCredits() != null && !recentTransactionDto.getCredits().equals("")
                    && recentTransactionDto.getTransactionType() != null && !recentTransactionDto.getTransactionType().equals("")
                    && recentTransactionDto.getPaymentType() != null && !recentTransactionDto.getPaymentType().equals("")
                    && recentTransactionDto.getStatus() != null && !recentTransactionDto.getStatus().equals("")) {

                RecentTransactionDto updateRecentTransactionDto = transactionService.updateTransaction(id, recentTransactionDto);
                return new ResponseEntity<>(new ApiResponse(203, "UPDATED", "Transaction updated", Arrays.asList(updateRecentTransactionDto), true), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getTransactionWithPagination(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        try {
            TransactionResponse transactionResponse = this.transactionService.getAllTransactionByPagination(pageNumber, pageSize, sortBy, sortDir);
            if (transactionResponse != null && transactionResponse.getContent() != null && !transactionResponse.getContent().isEmpty()
                    && transactionResponse.getContent().size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details By Pagination", Arrays.asList(transactionResponse), true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<ApiResponse> searchTransactionById(@PathVariable("keywords") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.searchById(id);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deleteAllTransactions() {
        try {
            Boolean isTransactionDeleted = transactionService.deleteAllTransactions();

            if (isTransactionDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transaction Detail Deleted", Arrays.asList(), true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Transaction", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody List<RecentTransactionDto> recentTransactionDtoList) {
        try {

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty()) {
                List<RecentTransactionDto> createTransactionDtoList = transactionService.createAllTransactions(recentTransactionDtoList);
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", createTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Transaction", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jpa/date")
    public ResponseEntity<ApiResponse> getTransactionBetweenDate(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findByDateBetween(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/native/date")
    public ResponseEntity<ApiResponse> findTransactionBetweenDate(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findAllTransactionBetweenDate(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDate")
    public ResponseEntity<ApiResponse> findTransactionByDate(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findTransactionByDate(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findDataByDate")
    public ResponseEntity<ApiResponse> getTransactionByDate(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.getTransactionByDate(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jpa/find")
    public ResponseEntity<ApiResponse> findAllTransactionWithDateRange(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findAllTransactionWithDateRange(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findDataBetweenId")
    public ResponseEntity<ApiResponse> findAllTransactionWithIdRange(@RequestParam String startIdParameter, @RequestParam String endIdParameter) {
        try {
            Long startId = Long.parseLong(startIdParameter);
            Long endId = Long.parseLong(endIdParameter);
            List<RecentTransactionDto> recentTransactionDtoList
                    = this.transactionService.findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(startId, endId);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllTransactionByDateRange")
    public ResponseEntity<ApiResponse> findAllTransactionByDateRange(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findByDateGreaterThanEqualAndDateLessThanEqual(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllTransactionByDate")
    public ResponseEntity<ApiResponse> findAllTransactionByDate(@RequestParam String startDateParameter, @RequestParam String endDateParameter) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDateParameter);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(endDateParameter);
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findAllTransactionByDate(startDate, endDate);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorting")
    public ResponseEntity<ApiResponse> getAllTransactionsWithSorting(
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        try {
            List<RecentTransactionDto> recentTransactionDtoList = this.transactionService.findAllDetailWithSorting(sortBy, sortDir);

            if (recentTransactionDtoList != null && !recentTransactionDtoList.isEmpty() && recentTransactionDtoList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Transactions Details", recentTransactionDtoList, true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Transactions Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
