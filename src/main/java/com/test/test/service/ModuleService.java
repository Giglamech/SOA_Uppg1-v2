package com.test.test.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.test.test.model.Module;
import com.test.test.repository.ModuleRepository;
import elemental.json.JsonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleService {
    @Autowired
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }


    public List<Module> getModules(String courseCode){
        return moduleRepository.findBycourse(courseCode);

    }

    public Module addModule(Module module){
        module.setModuleId(UUID.randomUUID().toString().split("-")[0]);
        return moduleRepository.save(module);


    }

}
