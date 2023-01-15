package com.student.rest.api.cache;

import com.student.rest.api.dto.StudentDto;
import com.student.rest.api.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CacheReload {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SimpleCacheManager cacheManager;

    //@Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
   // @Scheduled(cron = "* * * * * *")
    public void reloadCache() {
         List<StudentDto> studentList = studentService.getAllStudents();
        System.out.println(studentList);
        studentList.forEach((studentDto) -> {
            cacheManager.add(""+studentDto.getId(), studentDto);
        });
    }
}
