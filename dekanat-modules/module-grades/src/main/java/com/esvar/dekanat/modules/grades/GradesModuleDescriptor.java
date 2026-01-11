package com.esvar.dekanat.modules.grades;

import com.esvar.dekanat.modules.grades.views.GradesView;
import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.stereotype.Component;

@Component
public class GradesModuleDescriptor implements AppModuleDescriptor {
    @Override
    public String getName() {
        return "Оцінки";
    }

    @Override
    public VaadinIcon getIcon() {
        return VaadinIcon.CLIPBOARD_CHECK;
    }

    @Override
    public Class<? extends Component> getRootView() {
        return GradesView.class;
    }

    @Override
    public String getRoute() {
        return "grades";
    }

    @Override
    public int getOrder() {
        return 30;
    }
}
