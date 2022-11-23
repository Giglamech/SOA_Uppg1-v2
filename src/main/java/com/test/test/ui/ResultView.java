package com.test.test.ui;

import com.test.test.model.Result;
import com.test.test.service.UIService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultView extends FormLayout {
    TextField studentIdField = new TextField("Student-ID");
    TextField ssnField = new TextField("Personnummer");
    TextField studentNameField = new TextField("Namn");
    Button registerResultButton = new Button("Registrera nytt resultat");
    DatePicker resultDatePicker = new DatePicker("Ange registreringsdatum");
    private final UIService uiService;

    public String activeModuleName;
    public String activeCourseCode;

    ComboBox<String> resultComboBox = new ComboBox<>("Resultat");
     ComboBox<String> statusComboBox = new ComboBox<>("Status");

    public List<TextField> requiredTextFields = new ArrayList<TextField>();
    public List<ComboBox> requiredComboBoxFields = new ArrayList<ComboBox>();

    public ResultView(List<Result> results, UIService uiService) {
        this.uiService = uiService;
        addClassName("result-form");
        ssnField.setReadOnly(true);
        //nameField.addValueChangeListener(e -> updateStudentList());
        studentNameField.setReadOnly(true);
        resultComboBox.setReadOnly(true);
        studentIdField.addValueChangeListener(e -> updateStudentList());
        registerResultButton.addClickListener(e -> registerNewResult());

        List<String> possibleStatus = new ArrayList<>();
        possibleStatus.add("Utkast");
        possibleStatus.add("Klarmarkerad");
        possibleStatus.add("Attesterad");
        possibleStatus.add("Hinder");
        statusComboBox.setItems(possibleStatus);

        DatePicker.DatePickerI18n i18n = new DatePicker.DatePickerI18n();
        i18n.setDateFormat("yyyy-MM-dd");
        resultDatePicker.setI18n(i18n);
        resultDatePicker.setValue(LocalDate.from(LocalDateTime.now()));

        requiredTextFields.add(studentNameField);
        requiredTextFields.add(ssnField);
        requiredTextFields.add(studentIdField);
        requiredComboBoxFields.add(resultComboBox);
        requiredComboBoxFields.add(statusComboBox);

        add(
                getSearchButton(),
                studentIdField,
                ssnField,
                studentNameField,
                resultComboBox,
                statusComboBox,
                resultDatePicker,
                registerResultButton
        );


    }
    private void displayErrorMessage (String errorMessage) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setText(errorMessage);
        notification.setDuration(5000);
        notification.open();
    }

    private com.vaadin.flow.component.dialog.Dialog getErrorDialog(String message) {
        com.vaadin.flow.component.dialog.Dialog errorDialog = new Dialog();
        com.vaadin.flow.component.button.Button okButton = new Button("Ok", e -> errorDialog.close());
        com.vaadin.flow.component.html.Label errorMessage = new Label(message);
        errorDialog.getFooter().add(okButton);
        errorDialog.add(errorMessage);
        return errorDialog;
    }

    private void updateStudentList() {
        if (studentIdField == null || studentIdField.isEmpty()) {
            displayErrorMessage("Kunde inte hitta resultat!");
        } else {
            String studentResponse = String.valueOf((uiService.getStudent(studentIdField.getValue().toString())));
            ssnField.setValue(getSsn(studentResponse));
            studentNameField.setValue(getName(studentResponse));
        }
    }

    private void registerNewResult() {
        boolean allRequiredFieldsFilled = true;
        boolean isEmpty;
        for (TextField field : requiredTextFields) {
            isEmpty = field.isEmpty();
            if (isEmpty) {
                allRequiredFieldsFilled = false;
                break;
            }
        }
        if (allRequiredFieldsFilled) {
            for (ComboBox box : requiredComboBoxFields) {
                isEmpty = box.isEmpty();
                if (isEmpty) {
                    allRequiredFieldsFilled = false;
                    break;
                }
            }
        }
        if (resultDatePicker.isEmpty()) {
            allRequiredFieldsFilled = false;
        }
        if (allRequiredFieldsFilled) {
            String ssn = ssnField.getValue();
            String date = String.valueOf(resultDatePicker.getValue());
            String grade = resultComboBox.getValue();
            String status = statusComboBox.getValue();
            uiService.registerNewResult(ssn, activeCourseCode, activeModuleName, date, grade, status);
            displayErrorMessage("Nytt resultat har registrerats");
        } else {
            displayErrorMessage("Fält saknas för att registrera resultat");
        }
    }

    private Button getSearchButton() {
        Button searchButton = new Button("Sök");
        searchButton.addClickListener(e -> updateStudentList());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return searchButton;
    }
    private String getSsn(String ssn){
        Pattern ssnPattern = Pattern.compile("(ssn=)([0-9]+)");
        Matcher ssnMatch = ssnPattern.matcher(ssn);
        if (ssnMatch.find()) {
            return ssnMatch.group(2);
        }
        return "-";
    }
    private String getName(String name){
        Pattern pattern = Pattern.compile("(name=)([A-Za-z ]+)");
        Matcher match = pattern.matcher(name);
        if (match.find()) {
            return match.group(2);
        }
        return "-";
    }
}
