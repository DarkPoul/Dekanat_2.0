package com.esvar.dekanat.modules.studyplans.views;

import com.esvar.dekanat.modules.studyplans.service.StudyPlanService;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.components.SectionCard;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "study-plans", layout = AppShellLayout.class)
@PageTitle("Навчальні плани")
public class StudyPlansStartView extends BaseView {
    private final ComboBox<String> groupSelect;
    private final ComboBox<String> semesterSelect;

    public StudyPlansStartView(StudyPlanService studyPlanService) {
        setContentWidthDefault();

        PageHeader header = new PageHeader("Навчальні плани", "Почніть з вибору групи та семестру");
        addSection(header);

        List<String> groups = studyPlanService.getAvailableGroups();
        List<String> semesters = studyPlanService.getAvailableSemesters();

        groupSelect = new ComboBox<>("Група");
        groupSelect.setItems(groups);
        if (!groups.isEmpty()) {
            groupSelect.setValue(groups.get(0));
        }

        semesterSelect = new ComboBox<>("Семестр");
        semesterSelect.setItems(semesters);
        if (!semesters.isEmpty()) {
            semesterSelect.setValue(semesters.get(0));
        }

        Button continueButton = new Button("Продовжити", event -> navigateToPlan());

        Paragraph hint = new Paragraph("Ви зможете змінити групу або семестр пізніше на екрані плану.");

        VerticalLayout form = new VerticalLayout(groupSelect, semesterSelect, continueButton, hint);
        form.setPadding(false);

        SectionCard card = new SectionCard(form);
        addSection(card);
    }

    private void navigateToPlan() {
        if (groupSelect.getValue() == null || semesterSelect.getValue() == null) {
            Notification.show("Оберіть групу та семестр");
            return;
        }
        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("group", List.of(groupSelect.getValue()));
        parameters.put("semester", List.of(semesterSelect.getValue()));
        UI.getCurrent().navigate("study-plans/plan", new QueryParameters(parameters));
    }
}
