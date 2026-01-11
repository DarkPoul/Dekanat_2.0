package com.esvar.dekanat.modules.debtors.views;

import com.esvar.dekanat.uikit.components.EmptyState;
import com.esvar.dekanat.uikit.components.PageHeader;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "debtors", layout = AppShellLayout.class)
@PageTitle("Боржники")
public class DebtorsView extends BaseView {
    public DebtorsView() {
        setContentWidthDefault();
        addSection(new PageHeader("Боржники", "Контроль академічних заборгованостей"));
        addSection(new EmptyState(VaadinIcon.WARNING, "Модуль у розробці", "Функціонал буде додано у наступних ітераціях."));
    }
}
