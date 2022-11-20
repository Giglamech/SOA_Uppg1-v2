package com.test.test.repository;

import com.test.test.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;


public interface ResultRepository extends MongoRepository<Result,String> {
 @Query("{'ssn': ?0}")
 List<Result> findByssn(String ssn);
 @Query("{'module': ?0}")
 List<Result> findByModule(String moduleNr);


}
