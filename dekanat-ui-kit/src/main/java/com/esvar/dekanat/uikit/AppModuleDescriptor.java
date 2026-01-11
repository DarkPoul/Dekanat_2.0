package com.esvar.dekanat.uikit;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;

public interface AppModuleDescriptor {
    String getName();

    VaadinIcon getIcon();

    Class<? extends Component> getRootView();

    String getRoute();

    int getOrder();
}
