package com.esvar.dekanat.modules.students;

import com.esvar.dekanat.modules.students.views.StudentsView;
import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.stereotype.Component;

@Component
public class StudentsModuleDescriptor implements AppModuleDescriptor {
    @Override
    public String getName() {
        return "Картки студентів";
    }

    @Override
    public VaadinIcon getIcon() {
        return VaadinIcon.USERS;
    }

    @Override
    public Class<? extends Component> getRootView() {
        return StudentsView.class;
    }

    @Override
    public String getRoute() {
        return "students";
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
