package com.esvar.dekanat.modules.debtors;

import com.esvar.dekanat.modules.debtors.views.DebtorsView;
import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.stereotype.Component;

@Component
public class DebtorsModuleDescriptor implements AppModuleDescriptor {
    @Override
    public String getName() {
        return "Боржники";
    }

    @Override
    public VaadinIcon getIcon() {
        return VaadinIcon.WARNING;
    }

    @Override
    public Class<? extends Component> getRootView() {
        return DebtorsView.class;
    }

    @Override
    public String getRoute() {
        return "debtors";
    }

    @Override
    public int getOrder() {
        return 50;
    }
}
