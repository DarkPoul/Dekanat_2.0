package com.esvar.dekanat.modules.studyplans.views;

import com.esvar.dekanat.uikit.components.EmptyState;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "study-plans/empty", layout = AppShellLayout.class)
@PageTitle("План відсутній")
public class StudyPlansEmptyView extends BaseView {
    public StudyPlansEmptyView() {
        setContentWidthDefault();

        PageHeader header = new PageHeader("Навчальний план", "План для цієї групи ще не створено");
        addSection(header);

        EmptyState emptyState = new EmptyState(
                VaadinIcon.FILE_REMOVE,
                "Для цієї групи та семестру план не створено",
                "Створіть новий план вручну або імпортуйте шаблон, щоб почати роботу."
        );
        Button createButton = new Button("Створити план", event -> Notification.show("Not implemented"));
        Button importButton = new Button("Імпортувати шаблон", event -> Notification.show("Not implemented"));
        emptyState.setActions(createButton, importButton);

        addSection(emptyState);
    }
}
