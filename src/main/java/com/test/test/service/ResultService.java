package com.test.test.service;

import com.test.test.model.Result;
import com.test.test.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {
    @Autowired
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    public List <Result> getResult(String ssn){
    return resultRepository.findByssn(ssn);

    }

    public Result addResult(Result result){
        result.setResultId(UUID.randomUUID().toString().split("-")[0]);
        return resultRepository.save(result);


    }

}
