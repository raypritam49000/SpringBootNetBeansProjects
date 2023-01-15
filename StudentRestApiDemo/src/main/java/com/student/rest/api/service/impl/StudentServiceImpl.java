package com.student.rest.api.service.impl;

import com.student.rest.api.dto.StudentDto;
import com.student.rest.api.entity.Student;
import com.student.rest.api.repository.StudentRepository;
import com.student.rest.api.service.StudentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = this.modelMapper.map(studentDto, Student.class);
        Student createStudent = this.studentRepository.save(student);
        return this.modelMapper.map(createStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = this.studentRepository.findAll();
        List<StudentDto> studentDtoList = studentList.stream().map(student
                -> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
        return studentDtoList;
    }

    @Override
    public StudentDto getStudent(long id) throws IllegalArgumentException {
        Optional<Student> studentOptional = this.studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            return this.modelMapper.map(studentOptional.get(), StudentDto.class);
        } else {
            throw new IllegalArgumentException("Student Detail Not Found with : " + id);
        }
    }

    @Override
    public StudentDto updateStudent(long id, StudentDto studentDto) {
        Optional<Student> studentOptional = this.studentRepository.findById(id);
        Student updateStudent = null;
        if (studentOptional.isPresent()) {
            studentOptional.get().setName(studentDto.getName());
            studentOptional.get().setCity(studentDto.getCity());
            studentOptional.get().setSalary(studentDto.getSalary());
            updateStudent = this.studentRepository.save(studentOptional.get());
        }
        return this.modelMapper.map(updateStudent, StudentDto.class);
    }

    @Override
    public boolean deleteStudent(long id) {
        Optional<Student> studentOptional = this.studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            this.studentRepository.delete(studentOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllStudent() {
        this.studentRepository.deleteAll();
        return true;
    }

}
