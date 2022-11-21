package com.test.test.controller;


import com.test.test.model.Student;
import com.test.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
@ResponseStatus(HttpStatus.CREATED)
        public Student addStudent(@RequestBody Student student){
    return studentService.addStudent(student);


}

    @GetMapping("/{studentId}")
    public List<Student> getStudent(@PathVariable String studentId){
        return studentService.getStudent(studentId);
    }
}
