package com.student.rest.api.controllers;

import com.student.rest.api.cache.SimpleCacheManager;
import com.student.rest.api.dto.StudentDto;
import com.student.rest.api.response.ApiResponse;
import com.student.rest.api.service.StudentService;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SimpleCacheManager cacheManager;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllStudents() {
        try {
            if (!cacheManager.isEmpty()) {
                List<StudentDto> studentDtoList = new ArrayList<>();

                cacheManager.getCache().entrySet().forEach((entry) -> {
                    StudentDto studentDto = (StudentDto) entry.getValue();
                    studentDtoList.add(studentDto);
                });

                if (studentDtoList != null && !studentDtoList.isEmpty() && studentDtoList.size() > 0) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Details", studentDtoList, true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Student Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            } else {
                List<StudentDto> studentDtoList = studentService.getAllStudents();

                if (studentDtoList != null && !studentDtoList.isEmpty() && studentDtoList.size() > 0) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Details", studentDtoList, true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Student Details Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            if (!cacheManager.isEmpty()) {
                StudentDto studentDto = (StudentDto) cacheManager.get("" + id);
                if (studentDto != null && studentDto.getName() != null && !studentDto.getName().equals("")
                        && studentDto.getCity() != null && !studentDto.getCity().equals("")
                        && studentDto.getSalary() != null && !studentDto.getCity().equals("")) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Details", Arrays.asList(studentDto), true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Student Detail Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            } else {
                StudentDto studentDto = studentService.getStudent(id);
                if (studentDto != null && studentDto.getName() != null && !studentDto.getName().equals("")
                        && studentDto.getCity() != null && !studentDto.getCity().equals("")
                        && studentDto.getSalary() != null && !studentDto.getCity().equals("")) {
                    return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Details", Arrays.asList(studentDto), true), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Student Detail Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
                }
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponse(500, "NOT_FOUND", "Student Detail Not Found", Arrays.asList(), false), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createStudent(@RequestBody StudentDto studentDto) {
        try {

            if (studentDto != null && studentDto.getName() != null && !studentDto.getName().equals("")
                    && studentDto.getCity() != null && !studentDto.getCity().equals("")
                    && studentDto.getSalary() != null && !studentDto.getCity().equals("")) {

                StudentDto createStudent = studentService.createStudent(studentDto);
                return new ResponseEntity<>(new ApiResponse(201, "CREATED", "Student CREATED", Arrays.asList(createStudent), true), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reloadCache")
    public ResponseEntity<ApiResponse> reloadCache() {
        this.cacheManager.clear();
        List<StudentDto> studentList = studentService.getAllStudents();
        studentList.forEach((studentDto) -> {
            cacheManager.add("" + studentDto.getId(), studentDto);
        });
        return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Cache Reload Successfully", Arrays.asList(), true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

            Boolean isStudentDeleted = studentService.deleteStudent(id);

            if (isStudentDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Detail Deleted", Arrays.asList(), true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Student", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse> deleteAllStudents() {
        try {

            Boolean isStudentDeleted = studentService.deleteAllStudent();

            if (isStudentDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Student Detail Deleted", Arrays.asList(), true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Student", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudents(@PathVariable("id") long id,@RequestBody StudentDto studentDto){
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }
            
            if (studentDto != null && studentDto.getName() != null && !studentDto.getName().equals("")
                    && studentDto.getCity() != null && !studentDto.getCity().equals("")
                    && studentDto.getSalary() != null && !studentDto.getCity().equals("")) {
            
            StudentDto updateStudent = studentService.updateStudent(id, studentDto);
               return new ResponseEntity<>(new ApiResponse(203, "UPDATED", "Student updated", Arrays.asList(updateStudent), true), HttpStatus.CREATED);
            }
            else{
                 return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", Arrays.asList(), false), HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
             return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
