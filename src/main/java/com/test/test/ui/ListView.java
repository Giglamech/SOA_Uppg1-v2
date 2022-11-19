package com.test.test.ui;

import com.test.test.model.Module;
import com.test.test.model.Result;
import com.test.test.model.Student;
import com.test.test.repository.ModuleRepository;
import com.test.test.service.UIService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;


@PageTitle("Registrering av studieresultat")
@Route("")


public class ListView extends VerticalLayout {


    private final UIService uiService;

    public Grid<Result> getGrid() {
        return grid;
    }

    public void setGrid(Grid<Result> grid) {
        this.grid = grid;
    }

    public TextField getFilterText() {
        return filterText;
    }

    public void setFilterText(TextField filterText) {
        this.filterText = filterText;
    }

    Grid<Result> grid = new Grid<>(Result.class);
        TextField filterText = new TextField();
        ResultView resultView;

        public ListView(UIService uiService) {
            this.uiService = uiService;


            addClassName("list-view");
            setSizeFull();
            configureGrid();
            configureForm();
            

            add(getToolbar(), getContent());

            updateList();


        }

    private void updateList() {
            grid.setItems(uiService.findAllResults());

    }


    private HorizontalLayout getContent() {

    HorizontalLayout content = new HorizontalLayout(grid, resultView);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1,resultView);
    content.addClassName("content");
    content.setSizeFull();
    return content;
}
    private void configureForm() {
            resultView = new ResultView(Collections.emptyList());
            resultView.setWidth("25em");
    }

    private void configureGrid() {
            grid.addClassNames("contact-grid");
            grid.setSizeFull();
            grid.setColumns("namn", "grade", "module", "courseNr", "date", "status");
            grid.getColumns().forEach(col -> col.setAutoWidth(true));


        }


    private HorizontalLayout getToolbar() {
            filterText.setPlaceholder("Filter by name...");
            filterText.setClearButtonVisible(true);
            filterText.setValueChangeMode(ValueChangeMode.LAZY);
            filterText.addValueChangeListener(e -> updateList());

            Button confirmButton = new Button("Sök");
            Button addContactButton = new Button("Add contact");
            ComboBox<Module> moduleComboBox = new ComboBox<>("Modul...");
            moduleComboBox.setItems(uiService.getModules(filterText.getValue()));
            moduleComboBox.setItemLabelGenerator(Module::getModuleCode);



            HorizontalLayout toolbar = new HorizontalLayout(filterText, moduleComboBox, addContactButton, confirmButton);
            toolbar.addClassName("toolbar");
            return toolbar;
        }
    }


