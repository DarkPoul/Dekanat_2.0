package com.esvar.dekanat.modules.grades.views;

import com.esvar.dekanat.uikit.components.EmptyState;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "grades", layout = AppShellLayout.class)
@PageTitle("Оцінки")
public class GradesView extends BaseView {
    public GradesView() {
        setContentWidthDefault();
        addSection(new PageHeader("Оцінки", "Поточні відомості успішності"));
        addSection(new EmptyState(VaadinIcon.CLIPBOARD_TEXT, "Модуль у розробці", "Функціонал буде додано у наступних ітераціях."));
    }
}
