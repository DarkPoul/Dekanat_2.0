package com.esvar.dekanat.modules.students.views;

import com.esvar.dekanat.uikit.components.EmptyState;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "students", layout = AppShellLayout.class)
@PageTitle("Картки студентів")
public class StudentsView extends BaseView {
    public StudentsView() {
        setContentWidthDefault();
        addSection(new PageHeader("Картки студентів", "Огляд студентських профілів"));
        addSection(new EmptyState(VaadinIcon.USER_CARD, "Модуль у розробці", "Функціонал буде додано у наступних ітераціях."));
    }
}
