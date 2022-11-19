package com.test.test.service;

import com.test.test.model.Module;
import com.test.test.model.Result;
import com.test.test.repository.ModuleRepository;
import com.test.test.repository.ResultRepository;
import com.test.test.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UIService{

    private final ModuleRepository moduleRepository;
    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;


    public UIService(ModuleRepository moduleRepository, StudentRepository studentRepository, ResultRepository resultRepository) {
        this.moduleRepository = moduleRepository;
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    public List<Module> getModules(String courseCode){
        return moduleRepository.findBycourse(courseCode);

    }
public List<Result> findAllResults(){
        return resultRepository.findAll();
}
}
