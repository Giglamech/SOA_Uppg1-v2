package com.test.test.ui;

import com.test.test.model.Result;
import com.test.test.service.UIService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.awt.*;
import java.util.List;

public class ResultView extends FormLayout {
    TextField nameField = new TextField("Student-ID");
    TextField ssnField = new TextField("Personnummer");
    TextField testField = new TextField("Test");

    private final UIService uiService;


    ComboBox<Result> module = new ComboBox<>("Resultat");

    public ResultView(List<Result> results, UIService uiService) {
        this.uiService = uiService;
        addClassName("result-form");
        ssnField.setReadOnly(true);
        //nameField.addValueChangeListener(e -> updateStudentList());


        add(
                nameField,
                getSearchButton(),
                ssnField,
                module
        );


    }
    private void displayErrorMessage () {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setText("Kunde inte hitta resultat!");
        notification.open();
    }

    private com.vaadin.flow.component.dialog.Dialog getErrorDialog() {
        com.vaadin.flow.component.dialog.Dialog errorDialog = new Dialog();
        com.vaadin.flow.component.button.Button okButton = new Button("Ok", e -> errorDialog.close());
        com.vaadin.flow.component.html.Label errorMessage = new Label("Kunde inte hitta det du sökte efter");
        errorDialog.getFooter().add(okButton);
        errorDialog.add(errorMessage);
        return errorDialog;
    }

    private void updateStudentList() {


        try {
            String temp = nameField.getValue().toString();
            testField.setValue(String.valueOf(uiService.getStudent(temp)));
            ssnField.setValue(getSsn(testField.getValue().toString()));

        } catch (StringIndexOutOfBoundsException e) {
            getErrorDialog().open();
        }


        //if (nameField == null || nameField.isEmpty()) {
        //    displayErrorMessage();
        //} else {

          //  String temp = nameField.getValue().toString();
            //testField.setValue(String.valueOf(uiService.getStudent(temp)));
            //ssnField.setValue(getSsn(testField.getValue().toString()));


        //}

    }

    private Button getSearchButton() {
        Button searchButton = new Button("Sök");
        searchButton.addClickListener(e -> updateStudentList());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return searchButton;
    }
    private String getSsn(String ssn){
        String a = ssn.substring(32);
        String[] arr = a.split(",");
        ssn = arr[0];

    return ssn;
    }
}
