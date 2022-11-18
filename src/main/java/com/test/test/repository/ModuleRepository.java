package com.test.test.repository;

import com.test.test.model.Module;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;


public interface ModuleRepository extends MongoRepository<Module,String> {
    @Query("{courseCode: ?0}")
    List<Module> findBy(String courseCode);


}
