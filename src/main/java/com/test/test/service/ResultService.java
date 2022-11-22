package com.test.test.service;

import com.test.test.model.Result;
import com.test.test.repository.ResultRepository;
import com.test.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {
    @Autowired
    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;

    public ResultService(ResultRepository resultRepository, StudentRepository studentRepository) {
        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
    }


    public List <Result> getResult(String moduleNr){
        List<Result> result = resultRepository.findByModule(moduleNr);
        for (Result res : result) {
            if(res.getName() == null) {
                res.setName(studentRepository.findBySsn(res.getSsn()).getName());
            }
        }
        return result;
    }

    public Result addResult(Result result){
        result.setResultId(UUID.randomUUID().toString().split("-")[0]);
        return resultRepository.save(result);


    }

}
