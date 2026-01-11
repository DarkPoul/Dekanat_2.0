package com.esvar.dekanat.modules.reports.views;

import com.esvar.dekanat.uikit.components.EmptyState;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "reports", layout = AppShellLayout.class)
@PageTitle("Звіти/Відомості")
public class ReportsView extends BaseView {
    public ReportsView() {
        setContentWidthDefault();
        addSection(new PageHeader("Звіти та відомості", "Форми та експортні відомості"));
        addSection(new EmptyState(VaadinIcon.FILE_TEXT_O, "Модуль у розробці", "Функціонал буде додано у наступних ітераціях."));
    }
}
