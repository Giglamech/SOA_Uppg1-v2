package com.test.test.service;



import com.test.test.model.Student;
import com.test.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
        student.setUniqueId(UUID.randomUUID().toString().split("-")[0]);
        return studentRepository.save(student);

    }
    public List<Student> getStudent(String studentId){
        return studentRepository.getStudent(studentId);
    }

    public Student getStudentBySsn(String ssn) {
        return studentRepository.findBySsn(ssn);
    }
}
