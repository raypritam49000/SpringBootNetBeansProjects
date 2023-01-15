package com.onpassive.rest.api.config;

import com.onpassive.rest.api.cache.SimpleCacheManager;
import com.onpassive.rest.api.dto.RecentTransactionDto;
import com.onpassive.rest.api.service.RecentTransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class RealodCache {

    @Autowired
    private RecentTransactionService transactionService;

    @Autowired
    private SimpleCacheManager cacheManager;

    @Scheduled(cron = "* * * * * *")
    public void reloadCache() {
        System.out.println("====>>>> : Reload Cache");
        this.cacheManager.clear();
        List<RecentTransactionDto> recentTransactionDtoList = transactionService.getAllTransactions();
        recentTransactionDtoList.forEach((RecentTransactionDto recentTransactionDto) -> {
            cacheManager.add("" + recentTransactionDto.getTransactionId(), recentTransactionDto);
        });
    }
}
