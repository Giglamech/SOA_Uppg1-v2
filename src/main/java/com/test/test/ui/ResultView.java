package com.test.test.ui;

import com.test.test.model.Result;
import com.test.test.service.UIService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultView extends FormLayout {
    TextField studentIdField = new TextField("Student-ID");
    TextField ssnField = new TextField("Personnummer");
    TextField testField = new TextField("Test");
    TextField studentNameField = new TextField("Namn");
    Button registerResult = new Button("Registrera nytt resultat");
    private final UIService uiService;


    ComboBox<Result> resultComboBox = new ComboBox<>("Resultat");
    ComboBox<String> statusComboBox = new ComboBox<>("Status");


    public ResultView(List<Result> results, UIService uiService) {
        this.uiService = uiService;
        addClassName("result-form");
        ssnField.setReadOnly(true);
        studentNameField.setReadOnly(true);
        studentIdField.addValueChangeListener(e -> updateStudentList());
        List<String> possibleStatus = new ArrayList<>();
        possibleStatus.add("Utkast");
        possibleStatus.add("Klarmarkerad");
        possibleStatus.add("Attesterad");
        possibleStatus.add("Hinder");
        statusComboBox.setItems(possibleStatus);

        add(
                studentIdField,
                ssnField,
                studentNameField,
                resultComboBox,
                statusComboBox,
                registerResult
        );


    }
    private void displayErrorMessage () {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setText("Kunde inte hitta resultat!");
        notification.open();
    }

    private void updateStudentList() {
        if (studentIdField == null || studentIdField.isEmpty()) {
            displayErrorMessage();
        } else {

            String studentResponse = String.valueOf((uiService.getStudent(studentIdField.getValue().toString())));
            ssnField.setValue(getSsn(studentResponse));
            studentNameField.setValue(getName(studentResponse));


            //String idValue = studentIdField.getValue().toString();
            //ssnField.setValue(getSsn(String.valueOf(uiService.getStudent(idValue))));



        }




    }
    private String getSsn(String ssn){
        Pattern ssnPattern = Pattern.compile("(ssn=)([0-9]+)");
        Matcher ssnMatch = ssnPattern.matcher(ssn);
        if (ssnMatch.find()) {
            return ssnMatch.group(2);
        }
        return "NULL";
    }
    private String getName(String name){
        Pattern pattern = Pattern.compile("(name=)([A-Za-z ]+)");
        Matcher match = pattern.matcher(name);
        if (match.find()) {
            return match.group(2);
        }
        return "NULL";
    }
}
