package com.test.test.ui;

import com.test.test.model.Module;
import com.test.test.model.Result;
import com.test.test.service.UIService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    TextField filterText = new TextField("Kurskod");
    ComboBox<Module> moduleComboBox = new ComboBox<>("Modul...");
    ResultView resultView;

    public ListView(UIService uiService) {
        this.uiService = uiService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        resultView.requiredComboBoxFields.add(moduleComboBox);
        add(getToolbar(), getContent());
        updateList();
        updateToolBar();
    }

    private void updateToolBar() {
            if (filterText.getValue().isEmpty()){
                updateList();
                moduleComboBox.clear();

            } else {
                moduleComboBox.setItems(uiService.getModules(filterText.getValue()));
                moduleComboBox.setItemLabelGenerator(Module::getModuleCode);
                moduleComboBox.setPlaceholder(filterText.getValue());
            }
    }

    private String getModule(String module) {
        try {
          String a = module.substring(37);
          String[] arr = a.split(",");
          module = arr[0];
          return module;
        }
        catch (IndexOutOfBoundsException ignored){

        }
        return module;
    }

    private void updateList() {
            grid.setItems(uiService.findAllResults());
    }

    private void updateFilterList() {
        if (moduleComboBox.isEmpty()) {
            updateList();
        } else {
            try {
                resultView.resultComboBox.setItems(uiService.getAvailableGradesFromModule(String.valueOf((moduleComboBox.getValue()))));
                resultView.resultComboBox.setReadOnly(false);
                resultView.activeModuleName = getModuleCode(String.valueOf(moduleComboBox.getValue()));
                resultView.activeCourseCode = String.valueOf(filterText.getValue());
                grid.setItems(uiService.findAllResultsFilter(getModule((String.valueOf(moduleComboBox.getValue())))));
            }
            catch (NullPointerException ignore){
            }
        }
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
        resultView = new ResultView(Collections.emptyList(), uiService);
        resultView.setWidth("25em");
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "grade", "module", "courseNr", "date", "status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Sök på en kurskod...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        filterText.addValueChangeListener(e -> updateToolBar());
        moduleComboBox.addValueChangeListener(e -> updateFilterList());
        moduleComboBox.setWidthFull();

        HorizontalLayout toolbar = new HorizontalLayout(filterText, moduleComboBox);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private String getModuleCode(String module){
        Pattern pattern = Pattern.compile("(moduleCode=)([A-Za-z0-9 _]+)(,)");
        Matcher match = pattern.matcher(module);
        if (match.find()) {
            return match.group(2);
        }
        return "-";
    }
}


