package com.esvar.dekanat.uikit.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class EmptyState extends Div {
    private final VerticalLayout layout;

    public EmptyState(VaadinIcon icon, String title, String description) {
        Icon iconComponent = icon.create();
        iconComponent.setSize("48px");
        Span titleSpan = new Span(title);
        titleSpan.getStyle().set("font-weight", "600");
        Paragraph descriptionParagraph = new Paragraph(description);
        descriptionParagraph.getStyle().set("max-width", "480px");

        layout = new VerticalLayout(iconComponent, titleSpan, descriptionParagraph);
        layout.setSpacing(true);
        layout.setPadding(true);
        layout.setAlignItems(Alignment.CENTER);

        add(layout);
        setWidthFull();
        getStyle()
                .set("border", "1px dashed var(--lumo-contrast-20pct)")
                .set("border-radius", "var(--lumo-border-radius-l)")
                .set("padding", "var(--lumo-space-l)")
                .set("text-align", "center");
    }

    public void setActions(Component... actions) {
        HorizontalLayout actionLayout = new HorizontalLayout(actions);
        actionLayout.setSpacing(true);
        actionLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.add(actionLayout);
    }
}
