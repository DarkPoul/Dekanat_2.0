package com.esvar.dekanat.modules.studyplans;

import com.esvar.dekanat.modules.studyplans.views.StudyPlansStartView;
import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.stereotype.Component;

@Component
public class StudyPlansModuleDescriptor implements AppModuleDescriptor {
    @Override
    public String getName() {
        return "Навчальні плани";
    }

    @Override
    public VaadinIcon getIcon() {
        return VaadinIcon.BOOK;
    }

    @Override
    public Class<? extends Component> getRootView() {
        return StudyPlansStartView.class;
    }

    @Override
    public String getRoute() {
        return "study-plans";
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
