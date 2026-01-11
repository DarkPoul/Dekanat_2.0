package com.esvar.dekanat.uikit.layout;

import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class AppShellLayout extends AppLayout implements RouterLayout {
    public AppShellLayout(List<AppModuleDescriptor> modules) {
        addToNavbar(buildTopBar());
        addToDrawer(buildSideNav(modules));
        setPrimarySection(Section.DRAWER);
    }

    private HorizontalLayout buildTopBar() {
        H1 title = new H1("Dekanat 2.0");
        title.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        TextField search = new TextField();
        search.setPlaceholder("Пошук...");
        search.setWidth("300px");

        Button help = new Button("Help", VaadinIcon.QUESTION_CIRCLE_O.create(),
                event -> com.vaadin.flow.component.notification.Notification.show("Help: у розробці"));

        HorizontalLayout bar = new HorizontalLayout(title, new Div(), search, help);
        bar.setWidthFull();
        bar.setAlignItems(Alignment.CENTER);
        bar.expand(bar.getComponentAt(1));
        bar.addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.SMALL);
        return bar;
    }

    private SideNav buildSideNav(List<AppModuleDescriptor> modules) {
        SideNav nav = new SideNav();
        modules.stream()
                .sorted(Comparator.comparingInt(AppModuleDescriptor::getOrder))
                .forEach(module -> {
                    SideNavItem item = new SideNavItem(module.getName(), module.getRoute(), module.getIcon().create());
                    item.setHighlightCondition(HighlightConditions.sameLocation());
                    nav.addItem(item);
                });
        return nav;
    }
}
