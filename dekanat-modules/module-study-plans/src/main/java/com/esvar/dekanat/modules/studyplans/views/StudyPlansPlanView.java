package com.esvar.dekanat.modules.studyplans.views;

import com.esvar.dekanat.modules.studyplans.domain.HistoryEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanParameters;
import com.esvar.dekanat.modules.studyplans.domain.SummaryMetric;
import com.esvar.dekanat.modules.studyplans.service.StudyPlanService;
import com.esvar.dekanat.uikit.components.FilterBar;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.components.SectionCard;
import com.esvar.dekanat.uikit.components.StatusChip;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "study-plans/plan", layout = AppShellLayout.class)
@PageTitle("План навчання")
public class StudyPlansPlanView extends BaseView implements BeforeEnterObserver {
    private final StudyPlanService studyPlanService;
    private final PageHeader pageHeader;
    private final Grid<StudyPlanEntry> grid;
    private final HorizontalLayout metricsLayout;
    private final FormLayout parametersForm;
    private final TextField yearField;
    private final TextField programField;
    private final TextField levelField;
    private final TextField facultyField;
    private final VerticalLayout historyLayout;
    private String group = "КН-21";
    private String semester = "1";

    public StudyPlansPlanView(StudyPlanService studyPlanService) {
        this.studyPlanService = studyPlanService;
        setContentWidthDefault();

        pageHeader = new PageHeader("Навчальний план", "");
        Checkbox emptyToggle = new Checkbox("Імітувати відсутній план");
        emptyToggle.addValueChangeListener(event -> {
            if (event.getValue()) {
                navigateToEmpty();
            }
        });
        pageHeader.addAction(emptyToggle);
        addSection(pageHeader);

        Tabs tabs = new Tabs();
        Tab disciplinesTab = new Tab("Дисципліни");
        Tab distributionTab = new Tab("Розподіл");
        Tab parametersTab = new Tab("Параметри");
        Tab historyTab = new Tab("Історія");
        tabs.add(disciplinesTab, distributionTab, parametersTab, historyTab);

        grid = buildGrid();
        Div disciplinesContent = buildDisciplines();

        metricsLayout = new HorizontalLayout();
        metricsLayout.setWidthFull();
        metricsLayout.setSpacing(true);
        Div distributionContent = new Div(metricsLayout);

        parametersForm = new FormLayout();
        yearField = new TextField("Навчальний рік");
        programField = new TextField("Освітня програма");
        levelField = new TextField("Рівень");
        facultyField = new TextField("Факультет");
        for (TextField field : List.of(yearField, programField, levelField, facultyField)) {
            field.setReadOnly(true);
        }
        parametersForm.add(yearField, programField, levelField, facultyField);
        Div parametersContent = new Div(new SectionCard(parametersForm));

        historyLayout = new VerticalLayout();
        historyLayout.setPadding(false);
        Div historyContent = new Div(new SectionCard(historyLayout));

        Map<Tab, Div> tabsToPages = new HashMap<>();
        tabsToPages.put(disciplinesTab, disciplinesContent);
        tabsToPages.put(distributionTab, distributionContent);
        tabsToPages.put(parametersTab, parametersContent);
        tabsToPages.put(historyTab, historyContent);

        Div pages = new Div(disciplinesContent, distributionContent, parametersContent, historyContent);
        tabsToPages.values().forEach(page -> page.setVisible(false));
        disciplinesContent.setVisible(true);

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Div selectedPage = tabsToPages.get(tabs.getSelectedTab());
            if (selectedPage != null) {
                selectedPage.setVisible(true);
            }
        });

        addSection(tabs);
        addSection(pages);
        refreshData();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
        group = parameters.getOrDefault("group", List.of(group)).get(0);
        semester = parameters.getOrDefault("semester", List.of(semester)).get(0);
        pageHeader.setSubtitle("Навчальний план групи " + group + " — Семестр " + semester);
        refreshData();
    }

    private Div buildDisciplines() {
        Button addButton = new Button("Додати", event -> Notification.show("Not implemented"));
        Button importButton = new Button("Імпортувати", event -> Notification.show("Not implemented"));
        Button exportButton = new Button("Експорт", event -> Notification.show("Not implemented"));
        Button printButton = new Button("Друк", event -> Notification.show("Not implemented"));
        HorizontalLayout toolbar = new HorizontalLayout(addButton, importButton, exportButton, printButton);

        FilterBar filterBar = new FilterBar();
        TextField search = new TextField();
        search.setPlaceholder("Пошук дисципліни");
        ComboBox<String> controlForm = new ComboBox<>();
        controlForm.setItems("Екзамен", "Залік");
        controlForm.setPlaceholder("Форма контролю");
        ComboBox<String> status = new ComboBox<>();
        status.setItems("Активний", "Погоджено", "Потребує рев'ю", "Чернетка");
        status.setPlaceholder("Статус");
        filterBar.add(search, controlForm, status);

        VerticalLayout layout = new VerticalLayout(toolbar, filterBar, grid);
        layout.setPadding(false);

        return new Div(new SectionCard(layout));
    }

    private Grid<StudyPlanEntry> buildGrid() {
        Grid<StudyPlanEntry> grid = new Grid<>(StudyPlanEntry.class, false);
        grid.addColumn(StudyPlanEntry::discipline).setHeader("Назва дисципліни").setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(StudyPlanEntry::credits).setHeader("Кредити");
        grid.addColumn(StudyPlanEntry::hours).setHeader("Години");
        grid.addColumn(StudyPlanEntry::controlForm).setHeader("Форма контролю");
        grid.addColumn(StudyPlanEntry::department).setHeader("Кафедра");
        grid.addComponentColumn(entry -> new StatusChip(entry.status(), entry.statusColor())).setHeader("Статус");
        grid.setHeight("320px");
        return grid;
    }

    private void refreshData() {
        grid.setItems(studyPlanService.getPlanEntries(group, semester));

        metricsLayout.removeAll();
        for (SummaryMetric metric : studyPlanService.getSummaryMetrics(group, semester)) {
            VerticalLayout metricLayout = new VerticalLayout(new Paragraph(metric.label()), new Paragraph(metric.value()));
            metricLayout.setPadding(false);
            metricsLayout.add(new SectionCard(metricLayout));
        }

        StudyPlanParameters parameters = studyPlanService.getParameters(group, semester);
        yearField.setValue(parameters.academicYear());
        programField.setValue(parameters.program());
        levelField.setValue(parameters.level());
        facultyField.setValue(parameters.faculty());

        historyLayout.removeAll();
        for (HistoryEntry entry : studyPlanService.getHistory(group, semester)) {
            Paragraph text = new Paragraph(entry.date() + " • " + entry.author() + " — " + entry.description());
            historyLayout.add(text);
        }
    }

    private void navigateToEmpty() {
        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("group", List.of(group));
        parameters.put("semester", List.of(semester));
        UI.getCurrent().navigate("study-plans/empty", new QueryParameters(parameters));
    }
}
