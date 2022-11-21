package com.test.test.repository;

import com.test.test.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
@Query("{'studentId': ?0}")
List<Student> getStudent(String studentId);
}
