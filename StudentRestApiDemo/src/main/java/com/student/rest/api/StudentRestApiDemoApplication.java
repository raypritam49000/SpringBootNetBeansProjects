package com.student.rest.api;

import com.student.rest.api.cache.SimpleCacheManager;
import com.student.rest.api.dto.StudentDto;
import com.student.rest.api.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudentRestApiDemoApplication implements CommandLineRunner{
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SimpleCacheManager cacheManager;
    
    public static void main(String[] args) {
        SpringApplication.run(StudentRestApiDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<StudentDto> studentList = studentService.getAllStudents();
        System.out.println(studentList);
        studentList.forEach((studentDto) -> {
            cacheManager.add(""+studentDto.getId(), studentDto);
        });
    }

}
