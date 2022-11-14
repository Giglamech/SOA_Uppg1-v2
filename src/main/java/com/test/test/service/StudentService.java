package com.test.test.service;



import com.test.test.model.Student;
import com.test.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);

    }
    public Student getStudent(String studentId){

        return studentRepository.findById(studentId).get();
    }

}
