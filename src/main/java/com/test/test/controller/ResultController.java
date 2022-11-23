package com.test.test.controller;

import com.test.test.model.Result;
import com.test.test.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")

public class ResultController {
    @Autowired
    private final ResultService resultservice;

    public ResultController(ResultService resultservice) {
        this.resultservice = resultservice;
    }

    @GetMapping("{moduleNr}")
    public List <Result> getResult(@PathVariable String moduleNr){
        return resultservice.getResult(moduleNr);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result  addResult(@RequestBody Result result){
        return resultservice.addResult(result);


    }
}
