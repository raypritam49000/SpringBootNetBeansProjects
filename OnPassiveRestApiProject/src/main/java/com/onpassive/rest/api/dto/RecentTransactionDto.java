package com.onpassive.rest.api.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentTransactionDto {
    private long transactionId;
    private String transactionType;
    private String credits;
    private String paymentType;
    private String status;
    private Date date;
}
