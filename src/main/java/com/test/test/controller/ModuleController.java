package com.test.test.controller;

import com.test.test.model.Module;
import com.test.test.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module")

public class ModuleController {
    @Autowired
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }
    @GetMapping("{module}")
    public List<Module> getModules(String courseCode) {
        return moduleService.getModules(courseCode);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Module  addModule(@RequestBody Module module){
        return moduleService.addModule(module);


    }
}
