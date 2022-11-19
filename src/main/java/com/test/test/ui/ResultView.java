package com.test.test.ui;

import com.test.test.model.Module;
import com.test.test.model.Result;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ResultView extends FormLayout {
TextField name = new TextField("Student-ID");
TextField ssn = new TextField("Personnummer");
ComboBox<Result> module = new ComboBox<>("Resultat");

public ResultView(List<Result> results){
addClassName("result-form");

module.setItems(results);
module.setItemLabelGenerator(Result::getSsn);

add(
        name,
        ssn,
        module
);

}

}
