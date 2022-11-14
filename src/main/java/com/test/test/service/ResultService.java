package com.test.test.service;

import com.test.test.model.Result;
import com.test.test.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    public List<Result> getResult(String ssn, String courseNr, String module, String date, String grade){
        return resultRepository.findBy(ssn, courseNr, module, date, grade);

    }

    public Result addResult(Result result){
        return resultRepository.save(result);


    }

}