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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            List<Result> result = resultRepository.findByModule(filterText);
            result = addNamesToResults(result);
            return result;
        }
    }
    public List<Result> findAllResults(){
        List<Result> result = resultRepository.findAll();
        result = addNamesToResults(result);
        return result;
    }

    private List<Result> addNamesToResults(List<Result> result) {
        for (Result res : result) {
            if(res.getName() == null) {
                res.setName(studentRepository.findBySsn(res.getSsn()).getName());
            }
        }
        return result;
    }

    public List<Student> getStudent(String studentId){

        return studentRepository.getStudent(studentId);
    }

    public List<String> getAvailableGradesFromModule(String module) {
        System.out.println(module);
        Pattern pattern = Pattern.compile("(grades=\\[)([A-Za-z, ]+)(\\])");
        Matcher match = pattern.matcher(module);
        if (match.find()) {
            String grades = match.group(2);
            //grades.replace(" ", "");
            return new ArrayList<>(Arrays.asList(grades.split(",")));
        }
        return null;
    }

    public Result registerNewResult(String ssn, String courseCode, String moduleCode, String date, String grade, String status) {
        return null;
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
