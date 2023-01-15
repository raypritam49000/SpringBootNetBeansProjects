package com.student.rest.api.service;

import com.student.rest.api.dto.StudentDto;
import java.util.List;

public interface StudentService {
    public StudentDto createStudent(StudentDto studentDto);
    public StudentDto updateStudent(long id,StudentDto studentDto);
    public List<StudentDto> getAllStudents();
    public StudentDto getStudent(long id) throws IllegalArgumentException;
    public boolean deleteStudent(long id);
    public boolean deleteAllStudent();
}
