package com.test.test.service;

import com.test.test.model.Module;
import com.test.test.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }


    public List<Module> getModules(String courseCode){
        return moduleRepository.findBy(courseCode);

    }

    public Module addModule(Module module){
        return moduleRepository.save(module);


    }

}
