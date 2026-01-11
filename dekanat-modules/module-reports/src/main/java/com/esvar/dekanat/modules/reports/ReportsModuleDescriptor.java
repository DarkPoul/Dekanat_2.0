package com.esvar.dekanat.modules.reports;

import com.esvar.dekanat.modules.reports.views.ReportsView;
import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;


@org.springframework.stereotype.Component
public class ReportsModuleDescriptor implements AppModuleDescriptor {
    @Override
    public String getName() {
        return "Звіти/Відомості";
    }

    @Override
    public VaadinIcon getIcon() {
        return VaadinIcon.FILE_TEXT;
    }

    @Override
    public Class<? extends Component> getRootView() {
        return ReportsView.class;
    }

    @Override
    public String getRoute() {
        return "reports";
    }

    @Override
    public int getOrder() {
        return 40;
    }
}
