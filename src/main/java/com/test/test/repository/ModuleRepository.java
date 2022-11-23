package com.test.test.repository;

import com.test.test.model.Module;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ModuleRepository extends MongoRepository<Module,String> {
    @Query("{'courseCode': ?0}")
    List<Module> findBycourse(String courseCode);


}
