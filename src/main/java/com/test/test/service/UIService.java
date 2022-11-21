package com.test.test.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.test.test.model.Module;
import com.test.test.model.Result;
import com.test.test.model.Student;
import com.test.test.repository.ModuleRepository;
import com.test.test.repository.ResultRepository;
import com.test.test.repository.StudentRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UIService {

    private final ModuleRepository moduleRepository;
    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;


    public UIService(ModuleRepository moduleRepository, StudentRepository studentRepository, ResultRepository resultRepository) {
        this.moduleRepository = moduleRepository;
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    public List<Module> getModules(String courseCode) {
        return moduleRepository.findBycourse(courseCode);

    }

    public List<Result> findAllResultsFilter(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return resultRepository.findAll();
        } else {
           return resultRepository.findByModule(filterText);
        }


    }
public List<Result> findAllResults(){
        return resultRepository.findAll();
}

    public List<Student> getStudent(String studentId){

        return studentRepository.getStudent(studentId);
    }
    public com.vaadin.flow.component.dialog.Dialog getErrorDialog() {
        com.vaadin.flow.component.dialog.Dialog errorDialog = new Dialog();
        com.vaadin.flow.component.button.Button okButton = new Button("Ok", e -> errorDialog.close());
        com.vaadin.flow.component.html.Label errorMessage = new Label("Kunde inte hitta det du sökte efter");
        errorDialog.setCloseOnOutsideClick(false);
        errorDialog.getFooter().add(okButton);
        errorDialog.add(errorMessage);
        return errorDialog;
    }
}
