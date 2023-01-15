package com.onpassive.rest.api;

import com.onpassive.rest.api.cache.SimpleCacheManager;
import com.onpassive.rest.api.dto.RecentTransactionDto;
import com.onpassive.rest.api.repository.RecentTransactionRepository;
import com.onpassive.rest.api.service.RecentTransactionService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@EnableScheduling
@SpringBootApplication
public class OnPassiveRestApiProjectApplication implements CommandLineRunner {

    @Autowired
    private RecentTransactionService transactionService;

    @Autowired
    private SimpleCacheManager cacheManager;
    
    @Autowired
    private RecentTransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnPassiveRestApiProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<RecentTransactionDto> recentTransactionDtoList = transactionService.getAllTransactions();
        recentTransactionDtoList.forEach((RecentTransactionDto recentTransactionDto) -> {
            cacheManager.add("" + recentTransactionDto.getTransactionId(), recentTransactionDto);
        });

        Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-01-10T18:57:59.572+00:00");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-09-10T18:57:59.572+00:00");
        List<RecentTransactionDto> recentTransactionDtoList1 = transactionService.findAllTransactionWithDateRange(startDate, endDate);
        System.out.println(recentTransactionDtoList1);
        
        System.out.println(transactionRepository.findByTransactionIdGreaterThanEqualAndTransactionIdLessThanEqual(1L,100L));
        System.out.println(transactionRepository.findByDateGreaterThanEqualAndDateLessThanEqual(startDate,endDate));
        System.out.println(transactionRepository.findAllTransactionByDate(startDate,endDate));
        System.out.println(transactionRepository.search("1"));
    }

}
